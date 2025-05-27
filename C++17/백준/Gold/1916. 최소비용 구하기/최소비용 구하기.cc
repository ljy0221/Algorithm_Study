#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

const int INF = INT_MAX;

struct Edge {
    int to, cost;
    Edge(int to, int cost) : to(to), cost(cost) {}
};

// 우선순위 큐를 위한 구조체 (거리가 작은 것이 우선순위가 높음)
struct State {
    int dist, node;
    State(int dist, int node) : dist(dist), node(node) {}
    
    // 거리가 작은 것이 우선순위가 높도록 비교 연산자 오버로딩
    bool operator>(const State& other) const {
        return dist > other.dist;
    }
};

vector<int> dijkstra(int start, int n, const vector<vector<Edge>>& graph) {
    vector<int> dist(n + 1, INF);
    priority_queue<State, vector<State>, greater<State>> pq;
    
    // 시작점 초기화
    dist[start] = 0;
    pq.push(State(0, start));
    
    while (!pq.empty()) {
        State current = pq.top();
        pq.pop();
        
        int curr_dist = current.dist;
        int curr_node = current.node;
        
        // 이미 처리된 노드라면 건너뛰기
        if (curr_dist > dist[curr_node]) {
            continue;
        }
        
        // 현재 노드와 연결된 모든 인접 노드 확인
        for (const Edge& edge : graph[curr_node]) {
            int next_node = edge.to;
            int next_dist = curr_dist + edge.cost;
            
            // 더 짧은 경로를 발견한 경우
            if (next_dist < dist[next_node]) {
                dist[next_node] = next_dist;
                pq.push(State(next_dist, next_node));
            }
        }
    }
    
    return dist;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N, M;
    cin >> N >> M;
    
    // 그래프를 인접 리스트로 표현
    vector<vector<Edge>> graph(N + 1);
    
    // 버스 정보 입력
    for (int i = 0; i < M; i++) {
        int from, to, cost;
        cin >> from >> to >> cost;
        graph[from].push_back(Edge(to, cost));
    }
    
    // 시작점과 도착점 입력
    int start, end;
    cin >> start >> end;
    
    // 다익스트라 알고리즘 실행
    vector<int> dist = dijkstra(start, N, graph);
    
    // 결과 출력
    cout << dist[end] << endl;
    
    return 0;
}
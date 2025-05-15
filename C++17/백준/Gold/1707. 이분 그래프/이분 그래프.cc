#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int K, V, E;
vector<vector<int>> graph;
vector<int> group;

bool bfs(int start) {
    queue<int> q;
    q.push(start);
    group[start] = 0;  // 첫 번째 그룹으로 배정
    
    while (!q.empty()) {
        int node = q.front();
        q.pop();
        
        for (int neighbor : graph[node]) {
            if (group[neighbor] == -1) {
                // 현재 노드와 다른 그룹으로 배정
                group[neighbor] = 1 - group[node];  // 0이면 1로, 1이면 0으로
                q.push(neighbor);
            } else if (group[neighbor] == group[node]) {
                // 인접한 노드가 같은 그룹이면 이분 그래프가 아님
                return false;
            }
        }
    }
    return true;
}

bool isBipartite() {
    for (int i = 1; i <= V; i++) {
        if (group[i] == -1) {  // 아직 그룹이 배정되지 않은 노드
            if (!bfs(i)) {
                return false;
            }
        }
    }
    return true;
}

int main() {
    // input
    cin >> K;

    for(int i = 0; i< K; i++) {
        cin >> V >> E;
        graph.assign(V+1, vector<int>());
        group.assign(V+1, -1);
        
        for(int j = 0; j < E; j++) {
            int u, v;
            cin >> u >> v;

            graph[u].push_back(v);
            graph[v].push_back(u); 
        }
        
        // process & output
        if(isBipartite()) {
            cout << "YES" << '\n';
        } else {
            cout << "NO" << '\n';
        }
    }
    return 0;
}
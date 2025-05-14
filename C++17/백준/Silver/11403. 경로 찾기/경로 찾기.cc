#include <iostream>
#include <vector>
using namespace std;

int N;
vector<vector<int>> graph;

bool dfs(int current, int target, vector<bool>& visited, int steps) {
    // 목표에 도달했고 최소 1보 이상 걸었다면 성공
    if(current == target && steps > 0) return true;
    
    // 방문 처리
    visited[current] = true;
    
    // 인접한 모든 노드 탐색
    for(int next = 0; next < N; next++) {
        if(graph[current][next] == 1) {
            // 목표 노드이고 1보 이상 걸었다면 바로 성공
            if(next == target && steps + 1 > 0) return true;
            // 아직 방문하지 않은 노드라면 계속 탐색
            if(!visited[next] && dfs(next, target, visited, steps + 1)) {
                return true;
            }
        }
    }
    
    return false;
}

int main() {
    cin >> N;
    graph.resize(N, vector<int>(N));
    
    // 인접 행렬 입력
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cin >> graph[i][j];
        }
    }
    
    // 모든 정점 쌍에 대해 경로 존재 여부 확인
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            vector<bool> visited(N, false);
            cout << (dfs(i, j, visited, 0) ? 1 : 0);
            if(j < N-1) cout << " ";
        }
        cout << '\n';
    }
    
    return 0;
}
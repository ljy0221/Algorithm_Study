#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<pair<int, int>>> adj;
vector<bool> visited;
int maxDist;
int farthestNode;

void dfs(int node, int dist) {
    visited[node] = true;
    
    if (dist > maxDist) {
        maxDist = dist;
        farthestNode = node;
    }
    
    for (auto& edge : adj[node]) {
        int nextNode = edge.first;
        int weight = edge.second;
        
        if (!visited[nextNode]) {
            dfs(nextNode, dist + weight);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int V;
    cin >> V;
    
    adj.resize(V + 1);
    visited.resize(V + 1);
    
    // 입력 처리
    for (int i = 1; i <= V; i++) {
        int node;
        cin >> node;
        
        int connected, weight;
        while (true) {
            cin >> connected;
            if (connected == -1) break;
            cin >> weight;
            adj[node].push_back({connected, weight});
        }
    }
    
    // 1단계: 임의의 점(1번)에서 가장 먼 점 찾기
    maxDist = 0;
    farthestNode = 1;
    fill(visited.begin(), visited.end(), false);
    dfs(1, 0);
    
    // 2단계: 1단계에서 찾은 점에서 가장 먼 점까지의 거리가 지름
    int startNode = farthestNode;
    maxDist = 0;
    fill(visited.begin(), visited.end(), false);
    dfs(startNode, 0);
    
    cout << maxDist << '\n';
    
    return 0;
}
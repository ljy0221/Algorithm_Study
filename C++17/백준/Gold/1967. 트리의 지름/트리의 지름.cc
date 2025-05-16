#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<pair<int, int>>> tree;
vector<bool> visited;
int n, diameter = 0;

int dfs(int start) {
    visited[start] = true;

    vector<int> distances;

    for(auto& node : tree[start]) {
        int child = node.first;
        int weight = node.second;

        if(!visited[child]) {
            int distance = dfs(child) + weight;
            distances.push_back(distance);
        }
    }

    if(distances.empty()) {
        return 0;
    }
    // 자식 거리들을 내림차순 정렬
    sort(distances.rbegin(), distances.rend());
    
    // 현재 노드를 지나는 지름 계산
    if (distances.size() >= 2) {
        // 가장 긴 두 경로를 연결
        diameter = max(diameter, distances[0] + distances[1]);
    } else {
        // 자식이 하나만 있는 경우
        diameter = max(diameter, distances[0]);
    }
    
    // 현재 노드에서 아래쪽으로 가는 최장 거리 반환
    return distances[0];
}


int main() {
    // input
    cin >> n;
    tree.resize(n+1);
    visited.resize(n+1);
    
    for(int i = 0; i < n-1; i++) {
        int u, v, w;
        cin >> u >> v >> w;

        tree[u].push_back({v, w});
        tree[v].push_back({u, w});
    }

    // process
    dfs(1);

    // output
    cout << diameter << '\n';
    
    return 0;
}
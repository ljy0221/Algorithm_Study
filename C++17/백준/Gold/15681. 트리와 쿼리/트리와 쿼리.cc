#include <iostream>
#include <vector>
using namespace std;

vector<vector<int>> adj;
vector<int> subtree_size;
vector<bool> visited;

void dfs(int v) {
    visited[v] = true;
    subtree_size[v] = 1;  // 자기 자신
    
    for(int child : adj[v]) {
        if(!visited[child]) {
            dfs(child);
            subtree_size[v] += subtree_size[child];
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N, R, Q;
    cin >> N >> R >> Q;
    
    adj.resize(N + 1);
    subtree_size.resize(N + 1, 0);
    visited.resize(N + 1, false);
    
    // 트리 입력
    for(int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    
    // 루트 R에서 DFS로 서브트리 크기 계산
    dfs(R);
    
    // 쿼리 처리
    for(int i = 0; i < Q; i++) {
        int u;
        cin >> u;
        cout << subtree_size[u] << '\n';
    }
    
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<int>> adj;
vector<vector<int>> dp;
vector<bool> visited;

void dfs(int v) {
    visited[v] = true;
    dp[v][0] = 0;  // v가 얼리 아답터가 아닌 경우
    dp[v][1] = 1;  // v가 얼리 아답터인 경우 (자기 자신 포함)
    
    for(int child : adj[v]) {
        if(!visited[child]) {
            dfs(child);
            
            // v가 얼리 아답터가 아니면 모든 자식이 얼리 아답터여야 함
            dp[v][0] += dp[child][1];
            
            // v가 얼리 아답터면 자식은 얼리 아답터여도 되고 아니어도 됨
            dp[v][1] += min(dp[child][0], dp[child][1]);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N;
    cin >> N;
    
    adj.resize(N + 1);
    dp.resize(N + 1, vector<int>(2, 0));
    visited.resize(N + 1, false);
    
    for(int i = 0; i < N - 1; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    
    // 1번 노드를 루트로 하여 DFS 수행
    dfs(1);
    
    // 루트에서 얼리 아답터인 경우와 아닌 경우 중 최솟값
    cout << min(dp[1][0], dp[1][1]) << endl;
    
    return 0;
}
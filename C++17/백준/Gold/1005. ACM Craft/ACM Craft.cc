#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int T, N, K, W;
vector<int> build_time;
vector<vector<int>> tree;
vector<int> dp;

int dfs(int target) {
    if(dp[target] != -1) {
        return dp[target];
    }
    
    int maxTime = 0;
    for(int prev : tree[target]) {
        maxTime = max(maxTime, dfs(prev));
    }
    
    dp[target] = maxTime + build_time[target];
    return dp[target];
}

int main() {
    cin >> T;
    
    while(T--) {
        cin >> N >> K;
        
        // 건물 번호는 1부터 N까지
        build_time.resize(N + 1);
        for(int j = 1; j <= N; j++) {
            cin >> build_time[j];
        }
        
        // 그래프 초기화
        tree.clear();
        tree.resize(N + 1);
        
        for(int j = 0; j < K; j++) {
            int u, v;
            cin >> u >> v;
            tree[v].push_back(u);  // v를 짓기 위해 u가 필요함
        }
        
        cin >> W;
        
        // DP 배열 초기화
        dp.assign(N + 1, -1);
        
        // 결과 출력
        cout << dfs(W) << '\n';
    }
    
    return 0;
}
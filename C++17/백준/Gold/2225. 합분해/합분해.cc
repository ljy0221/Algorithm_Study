#include <iostream>
#include <vector>
using namespace std;

const int MOD = 1000000000;

int main() {
    int N, K;
    cin >> N >> K;
    
    // dp[k][s] = k개의 수를 사용해서 합이 s가 되는 경우의 수
    vector<vector<long long>> dp(K + 1, vector<long long>(N + 1, 0));
    
    // 기저 조건: 0개의 수로 합 0을 만드는 경우의 수는 1
    dp[0][0] = 1;
    
    // 최적화된 점화식: dp[k][s] = dp[k-1][s] + dp[k][s-1]
    for (int k = 1; k <= K; k++) {
        for (int s = 0; s <= N; s++) {
            // k-1개로 합 s를 만든 후 0을 추가
            dp[k][s] = dp[k-1][s];
            
            // k개로 합 s-1을 만든 후 어떤 수에 1을 더함
            if (s >= 1) {
                dp[k][s] = (dp[k][s] + dp[k][s-1]) % MOD;
            }
        }
    }
    
    cout << dp[K][N] << endl;
    
    return 0;
}
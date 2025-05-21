#include <iostream>
#include <vector>
#include <climits>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T;
    cin >> T;

    while(T--) {
        // input
        int K;
        cin >> K;
        vector<int> files (K);
        for(int i = 0; i < K; i++) {
            cin >> files[i];
        }

        vector<vector<int>> dp (K, vector<int> (K, 0)); 
        vector<vector<int>> sum (K, vector<int> (K, 0));

        // 인접한 파일 두개의 합
        for(int i = 0; i < K; i++) {
            sum[i][i] = files[i];
            for(int j = i+1; j < K; j++) {
                sum[i][j] = sum[i][j-1]+files[j];
            }
        }
        
        for(int len = 2; len <= K; len++) {
            for(int i = 0; i <= K - len; i++) {
                int j = i + len - 1;

                dp[i][j] = INT_MAX;

                for(int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k+1][j] + sum[i][j];
                    if(cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }
        
        cout << dp[0][K-1] << '\n';
    }
    
    return 0;
}
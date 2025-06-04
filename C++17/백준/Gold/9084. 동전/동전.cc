#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int T, N, M;
    cin >> T;
    while(T--) {
        cin >> N;
        vector<int> coins(N);
        for(int i = 0; i < N; i++) {
            cin >> coins[i];
        }

        cin >> M;

        vector<int> dp(M+1, 0);

        //base
        dp[0] = 1;

        for(int coin : coins) {
            for(int remain = coin; remain <= M; remain++) {
                dp[remain] += dp[remain - coin];
            }
        }
        
        cout << dp[M] << '\n';
    }

    
    return 0;
}
#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n, k;
    cin >> n >> k;
    
    vector<int> coins(n);
    for (int i = 0; i < n; i++) {
        cin >> coins[i];
    }
    
    // dp[i]: i원을 만들 수 있는 경우의 수
    vector<long long> dp(k + 1, 0);
    dp[0] = 1; // 0원을 만드는 경우의 수는 1가지 (아무 동전도 사용하지 않는 경우)
    
    // 각 동전에 대해
    for (int i = 0; i < n; i++) {
        // 해당 동전으로 만들 수 있는 금액을 갱신
        for (int j = coins[i]; j <= k; j++) {
            dp[j] += dp[j - coins[i]];
        }
    }
    
    cout << dp[k] << endl;
    
    return 0;
}
#include <iostream>
#include <vector>
#include <climits>
using namespace std;

int main() {
    int n, k;
    cin >> n >> k;
    
    // Boolean 배열로 동전 종류 저장 (중복 자동 제거)
    vector<bool> hasCoin(k + 1, false);
    for(int i = 0; i < n; i++) {
        int coin;
        cin >> coin;
        if(coin <= k) {  // k보다 큰 동전은 사용할 수 없음
            hasCoin[coin] = true;
        }
    }
    
    // dp[i] = i원을 만드는데 필요한 최소 동전 개수
    vector<int> dp(k + 1, INT_MAX);
    dp[0] = 0;  // 0원을 만드는데 필요한 동전 개수는 0개
    
    for(int amount = 1; amount <= k; amount++) {
        for(int coin = 1; coin <= amount; coin++) {
            if(hasCoin[coin] && dp[amount - coin] != INT_MAX) {
                dp[amount] = min(dp[amount], dp[amount - coin] + 1);
            }
        }
    }
    
    cout << (dp[k] == INT_MAX ? -1 : dp[k]) << '\n';
    return 0;
}
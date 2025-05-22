#include <iostream>
using namespace std;

int main() {
    int N;
    cin >> N;
    
    // N이 홀수이면 불가능
    if (N % 2 == 1) {
        cout << 0 << endl;
        return 0;
    }
    
    // dp[i] = 3×i 크기의 벽을 채우는 경우의 수
    int dp[31] = {0};
    
    // 기저 조건
    dp[0] = 1;  // 아무것도 채우지 않는 경우
    dp[2] = 3;  // 3×2를 채우는 경우의 수
    
    // 점화식: dp[i] = 4 × dp[i-2] - dp[i-4]
    for (int i = 4; i <= N; i += 2) {
        dp[i] = 4 * dp[i-2] - dp[i-4];
    }
    
    cout << dp[N] << endl;
    
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int N;
    cin >> N;
    
    vector<int> terms(N);
    vector<int> payments(N);
    vector<int> dp(N+1, 0); // i일부터 마지막 날까지 최대 이익
    
    for(int i = 0; i < N; i++) {
        cin >> terms[i] >> payments[i];
    }    
    
    for(int i = N-1; i >= 0; i--) {
        if(i + terms[i] > N) {  // 상담이 퇴사일을 넘어감
            dp[i] = dp[i+1];
        } else {
            dp[i] = max(dp[i+1], payments[i] + dp[i + terms[i]]);
        }
    }
    
    cout << dp[0] << '\n';
    return 0;
}
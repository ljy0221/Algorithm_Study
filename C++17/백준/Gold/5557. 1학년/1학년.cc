#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N;
    cin >> N;

    vector<int> numbers(N);
    vector<vector<long long>> dp(N, vector<long long> (21, 0));

    for(int i = 0; i < N; i++) {
        cin >> numbers[i];
    }
    
    dp[0][numbers[0]] = 1;

    for(int i = 0; i < N-2; i++) {
        for(int j = 0; j <= 20; j++) {
            if(dp[i][j] > 0) {
                // + 사용
                if(j + numbers[i+1] <= 20) {
                    dp[i+1][j+numbers[i+1]] += dp[i][j];
                }

                // - 사용
                if(j - numbers[i+1] >= 0) {
                    dp[i+1][j-numbers[i+1]] += dp[i][j];
                }
            }
        }
    }

     cout << dp[N - 2][numbers[N - 1]] << endl;
    
    return 0;
}
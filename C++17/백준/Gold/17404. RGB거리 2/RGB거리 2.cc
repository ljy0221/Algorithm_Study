#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N;
    cin >> N;
    vector<vector<int>> costs(N, vector<int>(3));
    vector<vector<int>> dp(N, vector<int> (3));
    
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < 3; j++) {
            cin >> costs[i][j];
        }
    }

    const int INF = 1e9;
    int answer = INF;
    
    // 1번 집의 색깔을 각각 고정해서 계산
    for(int firstColor = 0; firstColor < 3; firstColor++) {
        vector<vector<int>> dp(N, vector<int>(3, INF));
        
        // 1번 집을 firstColor로 고정
        dp[0][firstColor] = costs[0][firstColor];
        
        // 2번째 집부터 DP 계산
        for(int i = 1; i < N; i++) {
            for(int color = 0; color < 3; color++) {
                for(int prevColor = 0; prevColor < 3; prevColor++) {
                    if(color != prevColor) {  // 인접한 집은 다른 색
                        dp[i][color] = min(dp[i][color], dp[i-1][prevColor] + costs[i][color]);
                    }
                }
            }
        }
        
        // N번째 집에서 1번 집과 다른 색깔만 고려
        for(int lastColor = 0; lastColor < 3; lastColor++) {
            if(lastColor != firstColor) {  // 1번 집과 다른 색
                answer = min(answer, dp[N-1][lastColor]);
            }
        }
    }

    cout << answer << '\n';
    
    return 0;
}
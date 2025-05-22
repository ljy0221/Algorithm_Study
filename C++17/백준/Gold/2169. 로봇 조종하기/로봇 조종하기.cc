#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

int main() {
    int N, M;
    cin >> N >> M;
    
    vector<vector<int>> mars(N, vector<int>(M));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> mars[i][j];
        }
    }
    
    vector<vector<int>> dp(N, vector<int>(M, INT_MIN));
    
    // 첫 번째 행 초기화 - 오른쪽으로만 이동 가능
    dp[0][0] = mars[0][0];
    for (int j = 1; j < M; j++) {
        dp[0][j] = dp[0][j-1] + mars[0][j];
    }
    
    // 각 행 처리
    for (int i = 1; i < N; i++) {
        // 왼쪽에서 오른쪽으로 처리
        vector<int> leftToRight(M, INT_MIN);
        
        // 첫 번째 열: 위에서만 올 수 있음
        leftToRight[0] = dp[i-1][0] + mars[i][0];
        
        // 나머지 열들
        for (int j = 1; j < M; j++) {
            // 위에서 오는 경우
            if (dp[i-1][j] != INT_MIN) {
                leftToRight[j] = max(leftToRight[j], dp[i-1][j] + mars[i][j]);
            }
            // 왼쪽에서 오는 경우
            if (leftToRight[j-1] != INT_MIN) {
                leftToRight[j] = max(leftToRight[j], leftToRight[j-1] + mars[i][j]);
            }
        }
        
        // 오른쪽에서 왼쪽으로 처리
        vector<int> rightToLeft(M, INT_MIN);
        
        // 마지막 열: 위에서만 올 수 있음
        rightToLeft[M-1] = dp[i-1][M-1] + mars[i][M-1];
        
        // 나머지 열들 (오른쪽에서 왼쪽으로)
        for (int j = M-2; j >= 0; j--) {
            // 위에서 오는 경우
            if (dp[i-1][j] != INT_MIN) {
                rightToLeft[j] = max(rightToLeft[j], dp[i-1][j] + mars[i][j]);
            }
            // 오른쪽에서 오는 경우
            if (rightToLeft[j+1] != INT_MIN) {
                rightToLeft[j] = max(rightToLeft[j], rightToLeft[j+1] + mars[i][j]);
            }
        }
        
        // 두 방향의 최댓값으로 결합
        for (int j = 0; j < M; j++) {
            dp[i][j] = max(leftToRight[j], rightToLeft[j]);
        }
    }
    
    cout << dp[N-1][M-1] << endl;
    return 0;
}
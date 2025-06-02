#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, m;
    cin >> n >> m;
    
    vector<vector<int>> matrix(n, vector<int>(m));
    vector<vector<int>> dp(n, vector<int>(m, 0));
    
    int maxSide = 0;  // 가장 큰 정사각형의 한 변의 길이
    
    // 입력 받으면서 동시에 DP 초기화
    for(int i = 0; i < n; i++) {
        string row;
        cin >> row;
        for(int j = 0; j < m; j++) {
            matrix[i][j] = row[j] - '0';
            if(matrix[i][j] == 1) {
                dp[i][j] = 1;
                maxSide = max(maxSide, dp[i][j]);
            }
        }
    }
    
    // DP 테이블 채우기 (두 번째 행부터)
    for(int i = 1; i < n; i++) {
        for(int j = 1; j < m; j++) {
            if(matrix[i][j] == 1) {
                // 왼쪽, 위쪽, 왼쪽 위 대각선의 최솟값 + 1
                dp[i][j] = min({dp[i-1][j], dp[i][j-1], dp[i-1][j-1]}) + 1;
                maxSide = max(maxSide, dp[i][j]);
            }
        }
    }
    
    // 넓이 = 한 변의 길이의 제곱
    cout << maxSide * maxSide << endl;
    
    return 0;
}
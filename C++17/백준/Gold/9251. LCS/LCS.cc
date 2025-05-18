#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    string str1, str2;
    cin >> str1 >> str2;
    
    int len1 = str1.length();
    int len2 = str2.length();
    
    // DP 테이블 생성 (2차원 배열)
    vector<vector<int>> dp(len1 + 1, vector<int>(len2 + 1, 0));
    
    // 동적 프로그래밍으로 LCS 계산
    for (int i = 1; i <= len1; i++) {
        for (int j = 1; j <= len2; j++) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    
    // 결과 출력 - LCS의 길이
    cout << dp[len1][len2] << endl;
    
    return 0;
}
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    string str1, str2;
    getline(cin, str1);
    getline(cin, str2);
    
    int n = str1.length();
    int m = str2.length();
    
    // dp[i][j] = str1[i-1]과 str2[j-1]에서 끝나는 공통 부분 문자열의 길이
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
    
    int maxLength = 0;
    
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (str1[i-1] == str2[j-1]) {
                dp[i][j] = dp[i-1][j-1] + 1;
                maxLength = max(maxLength, dp[i][j]);
            } else {
                dp[i][j] = 0;  // 연속되지 않으면 0
            }
        }
    }
    
    cout << maxLength << endl;
    
    return 0;
}
#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int main() {
    string str1, str2;
    cin >> str1 >> str2;
    
    int n = str1.length();
    int m = str2.length();
    
    // dp[i][j]: str1의 i번째까지와 str2의 j번째까지의 LCS 길이
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
    
    // 동적 계획법으로 LCS 길이 계산
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }
    
    // LCS 길이 출력
    int lcsLength = dp[n][m];
    cout << lcsLength << "\n";
    
    // LCS 길이가 0이면 문자열 출력하지 않음
    if (lcsLength == 0) {
        return 0;
    }
    
    // 역추적으로 LCS 문자열 구성
    string lcs = "";
    int i = n, j = m;
    
    while (i > 0 && j > 0) {
        if (str1[i - 1] == str2[j - 1]) {
            // 현재 문자가 같으면 LCS에 포함
            lcs = str1[i - 1] + lcs;
            i--;
            j--;
        } else if (dp[i - 1][j] > dp[i][j - 1]) {
            // 위쪽에서 온 경우
            i--;
        } else {
            // 왼쪽에서 온 경우
            j--;
        }
    }
    
    cout << lcs << "\n";
    
    return 0;
}
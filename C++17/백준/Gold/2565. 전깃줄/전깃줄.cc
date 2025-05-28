#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int n;
    cin >> n;
    
    vector<pair<int, int>> lines(n);
    for (int i = 0; i < n; i++) {
        cin >> lines[i].first >> lines[i].second;
    }
    
    // A전봇대 기준으로 정렬
    sort(lines.begin(), lines.end());
    
    // B전봇대 위치들만 추출
    vector<int> b_positions(n);
    for (int i = 0; i < n; i++) {
        b_positions[i] = lines[i].second;
    }
    
    // LIS(최장 증가 부분 수열) 길이 구하기
    vector<int> dp(n, 1);
    
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (b_positions[j] < b_positions[i]) {
                dp[i] = max(dp[i], dp[j] + 1);
            }
        }
    }
    
    // LIS의 최대 길이 찾기
    int max_lis = *max_element(dp.begin(), dp.end());
    
    // 제거해야 할 전깃줄의 개수 = 전체 개수 - LIS 길이
    cout << n - max_lis << endl;
    
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int n;
    cin >> n;
    
    vector<int> arr(n);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    
    // dp[i]: arr[i]를 마지막 원소로 하는 LIS의 길이
    vector<int> dp(n, 1);
    // parent[i]: arr[i] 이전에 오는 원소의 인덱스 (역추적용)
    vector<int> parent(n, -1);
    
    // 동적 계획법으로 LIS 길이 계산
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
                parent[i] = j;
            }
        }
    }
    
    // 최대 길이와 그 위치 찾기
    int maxLength = *max_element(dp.begin(), dp.end());
    int maxIndex = find(dp.begin(), dp.end(), maxLength) - dp.begin();
    
    // 역추적으로 LIS 구성
    vector<int> lis;
    int current = maxIndex;
    while (current != -1) {
        lis.push_back(arr[current]);
        current = parent[current];
    }
    
    // 뒤집어서 정방향으로 만들기
    reverse(lis.begin(), lis.end());
    
    // 결과 출력
    cout << maxLength << "\n";
    for (int i = 0; i < lis.size(); i++) {
        cout << lis[i];
        if (i < lis.size() - 1) cout << " ";
    }
    cout << "\n";
    
    return 0;
}
#include <iostream>
#include <vector>
#include <climits>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    
    // 행렬 크기 정보 저장
    // dimensions[i]와 dimensions[i+1]은 i번째 행렬의 행과 열 크기
    vector<int> dimensions(n + 1);
    
    // 행렬 크기 입력 받기
    for (int i = 0; i < n; i++) {
        int r, c;
        cin >> r >> c;
        
        if (i == 0) {
            dimensions[0] = r;
        }
        dimensions[i + 1] = c;
    }
    
    // dp[i][j]: i번째 행렬부터 j번째 행렬까지 곱했을 때의 최소 곱셈 연산 횟수
    vector<vector<long long>> dp(n, vector<long long>(n, 0));
    
    // 연쇄 길이가 2부터 n까지
    for (int len = 2; len <= n; len++) {
        // 연쇄 시작점
        for (int i = 0; i <= n - len; i++) {
            // 연쇄 끝점
            int j = i + len - 1;
            
            // 초기값을 충분히 큰 값으로 설정
            dp[i][j] = LLONG_MAX;
            
            // 분할 지점 k
            for (int k = i; k < j; k++) {
                // i~k 행렬과 (k+1)~j 행렬을 곱하는 비용 계산
                // dimensions[i] * dimensions[k+1] * dimensions[j+1]는 (i~k 행렬) × ((k+1)~j 행렬)의 곱셈 연산 수
                long long cost = dp[i][k] + dp[k + 1][j] + 
                                 (long long)dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                
                // 최소값 갱신
                if (cost < dp[i][j]) {
                    dp[i][j] = cost;
                }
            }
        }
    }
    
    // 0번째 행렬부터 (n-1)번째 행렬까지의 최소 곱셈 연산 횟수 출력
    cout << dp[0][n - 1] << '\n';
    
    return 0;
}
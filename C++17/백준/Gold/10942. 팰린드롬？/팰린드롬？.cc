#include <iostream>
#include <vector>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N;
    cin >> N;
    vector<int> numbers(N);
    for(int i = 0; i < N; i++) {
        cin >> numbers[i];
    }
    
    // dp[i][j] = numbers[i]부터 numbers[j]까지가 팰린드롬인지 여부
    vector<vector<int>> dp(N, vector<int>(N, 0));
    
    // 길이 1인 경우 (모든 단일 원소는 팰린드롬)
    for(int i = 0; i < N; i++) {
        dp[i][i] = 1;
    }
    
    // 길이 2인 경우
    for(int i = 0; i < N-1; i++) {
        if(numbers[i] == numbers[i+1]) {
            dp[i][i+1] = 1;
        }
    }
    
    // 길이 3 이상인 경우
    for(int len = 3; len <= N; len++) {
        for(int s = 0; s <= N - len; s++) {
            int e = s + len - 1;
            if(numbers[s] == numbers[e] && dp[s+1][e-1] == 1) {
                dp[s][e] = 1;
            }
        }
    }
    
    int M;
    cin >> M;
    while(M--) {
        int S, E;
        cin >> S >> E;
        // 1-based 입력을 0-based로 변환
        cout << dp[S-1][E-1] << '\n';
    }
    
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N, M;
    cin >> N >> M;

    vector<int> memory(N), cost(N);
    
    for (int i = 0; i < N; i++) {
        cin >> memory[i];
    }
    
    for (int i = 0; i < N; i++) {
        cin >> cost[i];
    }
    
    // 총 비용의 최대값 계산
    int total_cost = 0;
    for (int i = 0; i < N; i++) {
        total_cost += cost[i];
    }

    // dp[i][cost] = i번까지 cost만큼 사용할 때 최대 메모리
    vector<vector<long long>> dp(N+1, vector<long long>(total_cost+1, 0));
    for(int i = 1; i <= N; i++) {
        for(int j = 0; j <= total_cost; j++) {
            // i번을 종료하지 않을 때
            dp[i][j] = dp[i-1][j];

            // i번째 앱을 종료할 때
            if (j >= cost[i-1]) {
                dp[i][j] = max(dp[i][j], dp[i-1][j - cost[i-1]] + memory[i-1]);
            }
        }
    }

    // M 이상의 메모리를 확보하는 최소 비용 찾기
    int answer = total_cost;
    for (int j = 0; j <= total_cost; j++) {
        if (dp[N][j] >= M) {
            answer = j;
            break;  // 첫 번째 조건 만족 시 바로 종료 (최소 비용)
        }
    }

    cout << answer << endl;
    
    return 0;
}
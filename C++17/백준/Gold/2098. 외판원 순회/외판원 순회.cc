#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <cstring>
using namespace std;

int N;                // 도시의 수
int W[16][16];        // 비용 행렬
int dp[16][1 << 16];  // DP 테이블: dp[current][bitmask]

// current: 현재 도시, visited: 방문한 도시들(비트마스크)
int tsp(int current, int visited) {
    // 모든 도시를 방문했으면 시작 도시(0)로 돌아가는 비용 반환
    if (visited == (1 << N) - 1) {
        // 현재 도시에서 시작 도시로 갈 수 있는 경우
        if (W[current][0] > 0) {
            return W[current][0];
        }
        return INT_MAX; // 갈 수 없는 경우 매우 큰 값 반환
    }
    
    // 이미 계산한 경우 저장된 값 반환
    if (dp[current][visited] != -1) {
        return dp[current][visited];
    }
    
    // 최소 비용 계산
    dp[current][visited] = INT_MAX; // 초기값은 매우 큰 값
    
    for (int next = 0; next < N; next++) {
        // 아직 방문하지 않았고, 갈 수 있는 도시인 경우
        if (!(visited & (1 << next)) && W[current][next] > 0) {
            int nextVisited = visited | (1 << next);
            int nextCost = tsp(next, nextVisited);
            
            // 오버플로우 방지
            if (nextCost != INT_MAX && W[current][next] <= INT_MAX - nextCost) {
                int totalCost = W[current][next] + nextCost;
                dp[current][visited] = min(dp[current][visited], totalCost);
            }
        }
    }
    
    return dp[current][visited];
}

int main() {
    // 입력
    cin >> N;
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> W[i][j];
        }
    }
    
    // DP 테이블 초기화
    memset(dp, -1, sizeof(dp));
    
    // 0번 도시에서 시작하는 외판원 순회 비용 계산
    int startVisited = 1; // 0번 도시 방문 표시 (1 << 0)
    int result = tsp(0, startVisited);
    
    cout << result << endl;
    
    return 0;
}
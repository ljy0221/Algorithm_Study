#include <iostream>
#include <vector>
using namespace std;

int M, N; // M: 세로 크기, N: 가로 크기
vector<vector<int>> map;
vector<vector<int>> dp;
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};

int dfs(int x, int y) {
    // 목적지에 도달한 경우
    if(x == M-1 && y == N-1) {
        return 1;
    }
    
    // 이미 계산된 경우
    if(dp[x][y] != -1) {
        return dp[x][y];
    }
    
    // 새로 계산하는 경우
    dp[x][y] = 0;
    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        
        // 지도 범위 내이고 내리막길인 경우
        if(nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] < map[x][y]) {
            dp[x][y] += dfs(nx, ny);
        }
    }
    
    return dp[x][y];
}

int main() {
    cin >> M >> N;
    
    // M행 N열 크기로 맵과 DP 테이블 초기화
    map.resize(M, vector<int>(N));
    dp.resize(M, vector<int>(N, -1));
    
    // 지도 정보 입력
    for(int i = 0; i < M; i++) {
        for(int j = 0; j < N; j++) {
            cin >> map[i][j];
        }
    }
    
    cout << dfs(0, 0) << '\n';
    
    return 0;
}
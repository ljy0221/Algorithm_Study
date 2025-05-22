#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;
vector<vector<int>> mars;
vector<vector<bool>> visited;
int maxValue = INT_MIN;

// 3방향: 왼쪽, 오른쪽, 아래 (위로는 이동 불가)
int dx[3] = {0, 0, 1};
int dy[3] = {-1, 1, 0};

void dfs(int x, int y, int currentSum) {
    // 목적지 도달 시
    if (x == N-1 && y == M-1) {
        maxValue = max(maxValue, currentSum);
        return;
    }
    
    // 3방향으로 이동 시도
    for (int i = 0; i < 3; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        
        // 경계 체크 및 방문 체크
        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
            visited[nx][ny] = true;  // 방문 표시
            dfs(nx, ny, currentSum + mars[nx][ny]);  // 재귀 호출
            visited[nx][ny] = false; // 백트래킹: 방문 해제
        }
    }
}

int main() {
    cin >> N >> M;
    
    mars.resize(N, vector<int>(M));
    visited.resize(N, vector<bool>(M, false));
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> mars[i][j];
        }
    }
    
    // 시작점 (0,0)에서 출발
    visited[0][0] = true;
    dfs(0, 0, mars[0][0]);
    
    cout << maxValue << endl;
    return 0;
}

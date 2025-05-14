#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

int dx[] = {0, 0, -1, 1};
int dy[] = {1, -1, 0, 0};

int bfs(vector<vector<bool>>& grid, vector<vector<bool>>& visited, int start_y, int start_x, int M, int N) {
    queue<pair<int, int>> q;
    q.push({start_y, start_x});
    visited[start_y][start_x] = true;
    int area = 0;
    
    while(!q.empty()) {
        pair<int, int> current = q.front();
        q.pop();
        int y = current.first;
        int x = current.second;
        area++;
        
        // 4방향 탐색
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            
            // 범위 체크 및 방문 체크
            if(ny >= 0 && ny < M && nx >= 0 && nx < N && 
               !visited[ny][nx] && grid[ny][nx]) {
                visited[ny][nx] = true;
                q.push({ny, nx});
            }
        }
    }
    
    return area;
}

int main() {
    int M, N, K;
    cin >> M >> N >> K;
    
    // grid[y][x] 형태로 선언 (M개의 행, N개의 열)
    vector<vector<bool>> grid(M, vector<bool>(N, true));
    vector<vector<bool>> visited(M, vector<bool>(N, false));
    vector<int> areas;
    
    // 직사각형 입력 및 처리
    for(int i = 0; i < K; i++) {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;
        
        // 좌표 변환: (x,y) -> grid[M-1-y][x]
        // 왜냐하면 모눈종이에서 y=0이 아래쪽, 배열에서는 [0]이 위쪽이므로
        for(int x = x1; x < x2; x++) {
            for(int y = y1; y < y2; y++) {
                if(x >= 0 && x < N && y >= 0 && y < M) {
                    grid[M-1-y][x] = false;
                }
            }
        }
    }
    
    // BFS로 영역 찾기
    for(int y = 0; y < M; y++) {
        for(int x = 0; x < N; x++) {
            if(grid[y][x] && !visited[y][x]) {
                int area_size = bfs(grid, visited, y, x, M, N);
                areas.push_back(area_size);
            }
        }
    }
    
    // 결과 출력
    sort(areas.begin(), areas.end());
    cout << areas.size() << '\n';
    for(size_t i = 0; i < areas.size(); i++) {
        cout << areas[i];
        if(i < areas.size() - 1) cout << ' ';
    }
    cout << '\n';
    
    return 0;
}
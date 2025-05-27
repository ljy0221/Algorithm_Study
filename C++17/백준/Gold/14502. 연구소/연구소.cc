#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N, M;
vector<vector<int>> lab;
vector<pair<int, int>> empty_cells;
vector<pair<int, int>> virus_pos;
int max_safe_area = 0;

// 상하좌우 이동을 위한 방향 벡터
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

// BFS를 통해 바이러스 확산 시뮬레이션
int simulate_virus() {
    vector<vector<int>> temp_lab = lab;
    queue<pair<int, int>> q;
    
    // 모든 바이러스 위치를 큐에 추가
    for (auto& pos : virus_pos) {
        q.push(pos);
    }
    
    // BFS로 바이러스 확산
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 범위 체크 및 빈 칸인지 확인
            if (nx >= 0 && nx < N && ny >= 0 && ny < M && temp_lab[nx][ny] == 0) {
                temp_lab[nx][ny] = 2;
                q.push({nx, ny});
            }
        }
    }
    
    // 안전 영역(0인 칸) 개수 세기
    int safe_count = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (temp_lab[i][j] == 0) {
                safe_count++;
            }
        }
    }
    
    return safe_count;
}

// 3개의 벽을 세우는 모든 조합을 시도
void build_walls(int start, int walls_built) {
    // 3개의 벽을 모두 세웠으면 바이러스 확산 시뮬레이션
    if (walls_built == 3) {
        int safe_area = simulate_virus();
        max_safe_area = max(max_safe_area, safe_area);
        return;
    }
    
    // 백트래킹으로 3개 벽 조합 생성
    for (int i = start; i < empty_cells.size(); i++) {
        int x = empty_cells[i].first;
        int y = empty_cells[i].second;
        
        // 벽 세우기
        lab[x][y] = 1;
        
        // 다음 벽 세우기
        build_walls(i + 1, walls_built + 1);
        
        // 벽 제거 (백트래킹)
        lab[x][y] = 0;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin >> N >> M;
    lab.resize(N, vector<int>(M));
    
    // 연구소 정보 입력 및 빈 칸, 바이러스 위치 저장
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> lab[i][j];
            if (lab[i][j] == 0) {
                empty_cells.push_back({i, j});
            } else if (lab[i][j] == 2) {
                virus_pos.push_back({i, j});
            }
        }
    }
    
    // 3개의 벽을 세우는 모든 경우 시도
    build_walls(0, 0);
    
    cout << max_safe_area << endl;
    
    return 0;
}
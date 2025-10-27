#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

vector<vector<char>> paint;
vector<vector<bool>> visited;
int N, normal_count = 0, blind_count = 0;

int dx[] = {0, 0, -1, 1};
int dy[] = {1, -1, 0, 0};

void bfs(int start_x, int start_y, bool is_color_blind) {
    queue<pair<int, int>> q;
    q.push({start_x, start_y});
    visited[start_x][start_y] = true;
    char target_color = paint[start_x][start_y];
    
    while(!q.empty()) {
        int curr_x = q.front().first;
        int curr_y = q.front().second;
        q.pop();
        
        for (int i = 0; i < 4; i++) {
            int nx = curr_x + dx[i];
            int ny = curr_y + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                bool same_color = false;
                
                if (is_color_blind) {
                    // 적록색맹의 경우 R과 G를 같은 색으로 봄
                    if ((target_color == 'R' || target_color == 'G') && 
                        (paint[nx][ny] == 'R' || paint[nx][ny] == 'G')) {
                        same_color = true;
                    } else if (target_color == 'B' && paint[nx][ny] == 'B') {
                        same_color = true;
                    }
                } else {
                    // 정상인의 경우 정확히 같은 색만
                    same_color = (paint[nx][ny] == target_color);
                }
                
                if (same_color) {
                    visited[nx][ny] = true;
                    q.push({nx, ny});
                }
            }
        }
    }
}

int main() {
    // input
    cin >> N;
    paint.resize(N, vector<char>(N));
    
    for (int i = 0; i < N; i++) {
        string line;
        cin >> line;
        for (int j = 0; j < N; j++) {
            paint[i][j] = line[j];
        }
    }
    
    // process
     // 정상인의 경우 계산
    visited.assign(N, vector<bool>(N, false));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (!visited[i][j]) {
                bfs(i, j, false);
                normal_count++;
            }
        }
    }
    
    // 적록색맹의 경우 계산
    visited.assign(N, vector<bool>(N, false));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (!visited[i][j]) {
                bfs(i, j, true);
                blind_count++;
            }
        }
    }

    // output
    cout << normal_count << ' ' << blind_count << '\n';
    return 0;
}
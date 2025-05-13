#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<vector<int>> matirx;
vector<vector<bool>> visited;
int T, l;
int dx[8] = {1, 2, 2, 1, -1, -2, -2, -1};
int dy[8] = {2, 1, -1, -2, -2, -1, 1, 2};

int bfs(pair<int, int>current, pair<int, int> target) {
    queue<pair<int, int>>q;
    vector<vector<int>> dist (l, vector<int> (l, 0));
    q.push(current);
    visited[current.first][current.second] = true;
    dist[current.first][current.second] = 0;
    

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        if (x == target.first && y == target.second)
        {
            return dist[x][y];
        }
        
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= l || ny < 0 || ny >= l) {
                continue; // 유효한 범위 내에서만 작업
            }
            
            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
                q.push(make_pair(nx, ny));
            }
        }
    }
    return -1;
}

int main(int argc, char const *argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> T;
    while (T--) {
        cin >> l;
        matirx.assign(l, vector<int> (l, -1));
        visited.assign(l, vector<bool> (l, false));
        int currentX, currentY, targetX, targetY;
        cin >> currentX >> currentY;
        cin >> targetX >> targetY;
        matirx[currentX][currentY] = 1;

        int result = bfs(make_pair(currentX, currentY), make_pair(targetX, targetY));

        cout << result << '\n';
    }

    return 0;
}

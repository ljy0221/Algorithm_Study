#include <iostream>
#include <vector>
#include <queue>
#include <tuple>

using namespace std;

int N, M;

vector<vector<int>> matrix;
vector<vector<vector<bool>>> visited;
vector<vector<int>> moves;

int dx[4] = { 0, 0, -1, 1 };
int dy[4] = { 1, -1, 0, 0 };

int bfs() {
    queue<tuple<int, int, bool>> q;
    q.push({0, 0, false});
    visited[0][0][false] = true;
    moves[0][0] = 1;

    while (!q.empty()) {
        int currentX;
        int currentY;
        bool breakthrough;
        tie(currentX, currentY, breakthrough) = q.front();
        q.pop();

        if (currentX == N-1 && currentY == M-1) {
            return moves[currentX][currentY];
        }

        for (int i = 0; i < 4; i++) {
            int nextX = currentX + dx[i];
            int nextY = currentY + dy[i];

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                continue;
            }
            
            if (!breakthrough && !visited[nextX][nextY][false] && matrix[nextX][nextY] == 0) {
                visited[nextX][nextY][false] = true;
                q.push({nextX, nextY, false});
                moves[nextX][nextY] = moves[currentX][currentY] + 1;
            } else if (!breakthrough && matrix[nextX][nextY] == 1 && !visited[nextX][nextY][true]) {
                visited[nextX][nextY][true] = true;
                q.push({nextX, nextY, true});
                moves[nextX][nextY] = moves[currentX][currentY] + 1;
            } else if (breakthrough && matrix[nextX][nextY] == 0 && !visited[nextX][nextY][true]) {
                visited[nextX][nextY][true] = true;
                q.push({nextX, nextY, true});
                moves[nextX][nextY] = moves[currentX][currentY] + 1;
            }
        }
    }

    return -1;
}

int main(int argc, char const *argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;
    matrix.resize(N, vector<int> (M));
    visited.resize(N, vector<vector<bool>> (M, vector<bool> (2, false)));
    moves.resize(N, vector<int> (M, 0));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            char in;
            cin >> in;
            matrix[i][j] = in - '0';
        }
    }
    
    cout << bfs();

    return 0;
}

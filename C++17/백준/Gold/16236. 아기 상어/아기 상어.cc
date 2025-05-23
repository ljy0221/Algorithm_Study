#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct Fish {
    int dist, row, col;

    bool operator>(const Fish& other) const {
        if(dist != other.dist) return dist > other.dist;
        if(row != other.row) return row > other.row;
        return col > other.col;
    }
};

int N, sharkSize = 2, eat = 0, currRow, currCol;
vector<vector<int>> map;

int dr[] = {-1, 1, 0, 0};
int dc[] = {0, 0, -1, 1};

Fish bfs() {
    vector<vector<bool>> visited(N, vector<bool>(N, false));
    queue<tuple<int, int, int>> q; //row, col, dist
    priority_queue<Fish, vector<Fish>, greater<Fish>> pq;

    q.push({currRow, currCol, 0});
    visited[currRow][currCol] = true;

    while(!q.empty()) {
        auto [row, col, dist] = q.front();
        q.pop();

        for(int i = 0; i < 4; i++) {
            int nr = row + dr[i];
            int nc = col + dc[i];

            if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                if(map[nr][nc] <= sharkSize) {
                    visited[nr][nc] = true;
                    q.push({nr, nc, dist+1});

                    if(map[nr][nc] > 0 && map[nr][nc] < sharkSize) {
                        pq.push({dist+1, nr, nc});
                    }
                }
            }
        }
    }

    if(pq.empty()) {
        return {-1, -1, -1};
    }

    return pq.top();
}

int main() {
    cin >> N;
    map.resize(N, vector<int> (N));

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cin >> map[i][j];
            if(map[i][j] == 9) {
                currRow = i;
                currCol = j;
                map[i][j] = 0;
            }
        }
    }
    
    int time = 0;
    while(true) {
        Fish target = bfs();

        if(target.dist == -1) {
            break;
        }

        time += target.dist;
        currRow = target.row;
        currCol = target.col;
        map[currRow][currCol] = 0;
        eat++;

        if(eat == sharkSize) {
            sharkSize++;
            eat = 0;
        }
    }

    cout << time << '\n';
    
    return 0;
}
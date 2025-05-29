#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

int r, c;
vector<string> board;
vector<bool> visited;
int maxCount = 0;

int dr[4] = {-1, 1, 0, 0};
int dc[4] = {0, 0, -1, 1};

void dfs(int row, int col, int count) {
    maxCount = max(maxCount, count);

    for(int i = 0; i < 4; i++) {
        int nr = row + dr[i];
        int nc = col + dc[i];

        if(nr >= 0 && nr < r && nc >= 0 && nc < c) {
            int curr = board[nr][nc] - 'A';

            if(!visited[curr]) {
                visited[curr] = true;
                dfs(nr, nc, count+1);
                visited[curr] = false;
            }
        }
    }
}

int main() {
    
    cin >> r >> c;

    board.resize(r);
    visited.resize(26);

    for(int i = 0; i < r; i++) {
        cin >> board[i];
    }

    visited[board[0][0] - 'A'] = true;
    dfs(0, 0, 1);
    cout << maxCount << '\n';
    
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>
#include <tuple>

using namespace std;

int N, r, c;
int count = 0;
vector<vector<int>> pipes;
vector<vector<vector<int>>> dp;

int dfs(int r, int c, int dir) {

    if(r == N && c == N) {
        return 1;
    }

    if(r > N || c > N) {
        return 0;
    }

    if(dp[r][c][dir] != -1) return dp[r][c][dir];

    int result = 0;

    vector<tuple<int, int, int>> moves;

    if(dir == 0) {
        moves = {{0, 1, 0}, {1, 1, 2}};
    } else if(dir == 1) {
        moves = {{1, 0, 1}, {1, 1, 2}};
    } else {
        moves = {{0, 1, 0}, {1, 0, 1}, {1, 1, 2} };
    }

    for(auto [dr, dc, new_dir] : moves) {
        int nr = r + dr;
        int nc = c + dc;

        if(nr > N || nc > N) continue;

        bool can_move = true;

         if (new_dir == 2) {  // 대각선으로 이동
            if (pipes[r][c + 1] == 1 || pipes[r + 1][c] == 1 || pipes[nr][nc] == 1) {
                can_move = false;
            }
        } else {  // 직선 이동
            if (pipes[nr][nc] == 1) {
                can_move = false;
            }
        }
        
        if (can_move) {
            result += dfs(nr, nc, new_dir);
        }
    }
    
    return dp[r][c][dir] = result;
}


int main() {
    cin >> N;

    pipes.assign(N+1, vector<int> (N+1));
    dp.assign(N+1, vector<vector<int>> (N+1, vector<int> (3, -1)));
    
    for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= N; j++) {
            cin >> pipes[i][j];
        }
    }
    
    cout << dfs(1, 2, 0) << '\n';
    return 0;
}
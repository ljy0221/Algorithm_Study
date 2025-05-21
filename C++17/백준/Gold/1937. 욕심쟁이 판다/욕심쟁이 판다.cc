#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>

using namespace std;

int n;
int dx[4] = {0, 0, -1, 1};
int dy[4] = {1, -1, 0, 0};

vector<vector<int>> bamboo;
vector<vector<int>> dp; //(i,j)에서 시작했을 때 최대 칸수

int dfs(int x, int y) {
    if(dp[x][y] != 0) {
        return dp[x][y];
    }

    dp[x][y] = 1;

    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx >= 0 && nx < n && ny >= 0 && ny < n && bamboo[nx][ny] > bamboo[x][y]) {
            // 이동할 수 있는 최대 칸 수 갱신
            dp[x][y] = max(dp[x][y], dfs(nx, ny) + 1);
        }
    }

    return dp[x][y];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin >> n;
    bamboo.resize(n, vector<int>(n));
    dp.resize(n, vector<int>(n, 0)); //(i,j)에서 시작했을 때 최대 칸수
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            cin >> bamboo[i][j];
        }
    }

    int maxMove = INT_MIN;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            maxMove = max(maxMove, dfs(i, j));
        }
    }
    cout << maxMove << '\n';
    return 0;
}
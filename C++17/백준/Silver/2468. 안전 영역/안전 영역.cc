#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<int>> map;
vector<vector<bool>> visited;

int N;
int dx[] = {0, 0, -1 ,1};
int dy[] = {1, -1, 0, 0};

void dfs(int x, int y, int level){
    visited[x][y] = true;

    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] > level) {
            dfs(nx, ny, level);
        }
    }
}

int count_areas(int level) {
    int count = 0;

    visited.assign(N, vector<bool>(N, false));

    for(int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if(map[i][j] > level && !visited[i][j]) {
                dfs(i, j, level);
                count++;
            }
        }
    }
    
    return count;
}

int main() {

    // input
    cin >> N;
    map.resize(N, vector<int>(N));
    

    int min_height = 101;
    int max_height = 0;
    
    for(int i = 0; i < N; i++) {
        for(int j = 0; j< N; j++) {
            cin >> map[i][j];
            min_height = min(min_height, map[i][j]);
            max_height = max(max_height, map[i][j]);
        }
    }
    
    // process
    int max_areas = 0;
    
    for(int i = 0; i < max_height; i++) {
        int safe_areas = count_areas(i);
        max_areas = max(max_areas, safe_areas);
    }
    
    // output
    cout << max_areas << '\n';
    return 0;
}
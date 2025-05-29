#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int INF = 1e9; // 무한대 값 (도달할 수 없는 경우)

int main() {
    int n, m;
    cin >> n >> m;
    
    // 거리 배열 초기화
    vector<vector<int>> dist(n + 1, vector<int>(n + 1, INF));
    
    // 자기 자신으로 가는 비용은 0
    for (int i = 1; i <= n; i++) {
        dist[i][i] = 0;
    }
    
    // 버스 정보 입력
    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        // 같은 노선이 여러 개 있을 수 있으므로 최소값으로 갱신
        dist[a][b] = min(dist[a][b], c);
    }
    
    // 플로이드-워셜 알고리즘
    for (int k = 1; k <= n; k++) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][k] != INF && dist[k][j] != INF) {
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
    
    // 결과 출력
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (dist[i][j] == INF) {
                cout << "0 "; // 도달할 수 없는 경우
            } else {
                cout << dist[i][j] << " ";
            }
        }
        cout << "\n";
    }
    
    return 0;
}
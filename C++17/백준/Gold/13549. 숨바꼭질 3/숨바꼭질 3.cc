#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX_POS = 200000;
const int INF = 1e9;

int main() {
    int N, K;
    cin >> N >> K;
    
    // 거리 배열 초기화
    vector<int> dist(MAX_POS + 1, INF);
    
    // 덱을 사용한 0-1 BFS
    deque<int> dq;
    
    dq.push_back(N);
    dist[N] = 0;
    
    while (!dq.empty()) {
        int curr = dq.front();
        dq.pop_front();
        
        // 동생을 찾았으면 종료
        if (curr == K) {
            cout << dist[K] << '\n';
            return 0;
        }
        
        // 순간이동 (2*X, 시간 0초)
        int next_teleport = curr * 2;
        if (next_teleport <= MAX_POS && dist[next_teleport] > dist[curr]) {
            dist[next_teleport] = dist[curr];
            dq.push_front(next_teleport);  // 0초이므로 앞쪽에 추가
        }
        
        // 걸어서 이동 (X-1, X+1, 시간 1초)
        int moves[] = {curr - 1, curr + 1};
        for (int next : moves) {
            if (next >= 0 && next <= MAX_POS && dist[next] > dist[curr] + 1) {
                dist[next] = dist[curr] + 1;
                dq.push_back(next);  // 1초이므로 뒤쪽에 추가
            }
        }
    }
    
    cout << dist[K] << '\n';
    return 0;
}
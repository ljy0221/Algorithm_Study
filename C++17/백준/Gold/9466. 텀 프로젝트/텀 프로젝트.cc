#include <iostream>
#include <vector>
using namespace std;

vector<int> choice;
vector<int> state; // 0: 미방문, 1: 방문중, 2: 완료
vector<bool> inCycle;

void dfs(int node) {
    if (state[node] != 0) return;
    
    vector<int> path;
    int current = node;
    
    // 경로 추적
    while (state[current] == 0) {
        state[current] = 1; // 방문중 표시
        path.push_back(current);
        current = choice[current];
    }
    
    // 사이클 발견한 경우
    if (state[current] == 1) {
        // current부터 사이클 시작
        bool inCycleFlag = false;
        for (int node : path) {
            if (node == current) inCycleFlag = true;
            if (inCycleFlag) inCycle[node] = true;
        }
    }
    
    // 모든 경로의 노드를 완료 상태로 변경
    for (int node : path) {
        state[node] = 2;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T;
    cin >> T;
    
    while (T--) {
        int n;
        cin >> n;
        
        choice.assign(n + 1, 0);
        state.assign(n + 1, 0);
        inCycle.assign(n + 1, false);
        
        for (int i = 1; i <= n; i++) {
            cin >> choice[i];
        }
        
        // 모든 학생에 대해 DFS 수행
        for (int i = 1; i <= n; i++) {
            if (state[i] == 0) {
                dfs(i);
            }
        }
        
        // 팀에 속하지 않은 학생 수 계산
        int notInTeam = 0;
        for (int i = 1; i <= n; i++) {
            if (!inCycle[i]) {
                notInTeam++;
            }
        }
        
        cout << notInTeam << '\n';
    }
    
    return 0;
}
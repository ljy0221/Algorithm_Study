#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> buildTime;
vector<vector<int>> techTree;
vector<int> dp;

int solve(int building) {
    if (dp[building] != -1) {
        return dp[building];
    }
    
    if (techTree[building].empty()) {
        // 선행 건물이 없는 경우
        return dp[building] = buildTime[building];
    }
    
    int maxPrereqTime = 0;
    for (int prereq : techTree[building]) {
        maxPrereqTime = max(maxPrereqTime, solve(prereq));
    }
    
    return dp[building] = maxPrereqTime + buildTime[building];
}

int main() {
    int N;
    cin >> N;
    
    buildTime.resize(N + 1);
    techTree.resize(N + 1);
    dp.resize(N + 1, -1);
    
    for (int i = 1; i <= N; i++) {
        cin >> buildTime[i];
        int priority;
        while (cin >> priority && priority != -1) {
            techTree[i].push_back(priority);
        }
    }
    
    for (int i = 1; i <= N; i++) {
        cout << solve(i) << '\n';
    }
    
    return 0;
}
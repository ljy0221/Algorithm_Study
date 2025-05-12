#include <iostream>
#include <vector>

using namespace std;

int N, M, u, v;
vector<int> nodes[1001];
bool check[1001];

void dfs(int node) {
   check[node] = true;
    for(int i = 0; i < nodes[node].size(); i++) {
    int next = nodes[node][i];
        if(check[next] == false) {
            dfs(next);
        }
        
    }

}

int main() {
    
    //input
    cin >> N >> M;

    for (int i = 0; i < M; i++) {
        cin >> u >> v;
        nodes[u].push_back(v);
        nodes[v].push_back(u);
    }

    //process
    int result = 0;

    for(int i = 1; i <= N; i++) {
        if(check[i] == false) {
            dfs(i);
            result++;
        }
    }

    //output
    cout << result << '\n';
    return 0;
}
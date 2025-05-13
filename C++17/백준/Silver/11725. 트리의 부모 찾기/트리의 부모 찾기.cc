#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> tree;
vector<int> parent;

void dfs(int curr, int par) {
    parent[curr] = par;

    for(int next : tree[curr]) {
        if(next != par) {
            dfs(next, curr);
        }
    }
}

int main() {

    //input
    int N;
    cin >> N;
    tree.resize(N+1);
    parent.resize(N+1);
    for(int i = 0; i< N-1; i++) {
        int u, v;
        cin >> u >> v;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }
    
    //process
    dfs(1, 0); // root node - 1

    //output
    for(int i = 2; i <= N; i++) {
        cout << parent[i] << '\n';
    }
    return 0;
}
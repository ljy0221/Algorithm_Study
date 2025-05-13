#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int order = 1;

void dfs(const vector<int> graph[], vector<int>& visited, int current) {
	if (visited[current] != -1)
		return;

	// 방문 순서를 입력
	visited[current] = order++;

	for (int next : graph[current]) {
		if (visited[next] == -1) {
			dfs(graph, visited, next);
		}
	}
}

int main() {
	int n, m, r;
	cin >> n >> m >> r;

	vector<int> graph[n + 1];
	vector<int> visited(n + 1, -1);

	for (int i = 0; i < m; ++i) {
		int u, v;
		cin >> u >> v;
		graph[u].push_back(v);
		graph[v].push_back(u);
	}
	for (int i = 1; i <= n; ++i) {
		sort(graph[i].begin(), graph[i].end());
	}

	dfs(graph, visited, r);

	for (int i = 1; i <= n; ++i) {
		cout << ((visited[i] == -1) ? 0 : visited[i]) << '\n';
	}
}
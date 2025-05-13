#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int V, E, K;
int u, v, w;

vector<vector<pair<int, int>>> graph;
vector<int> min_distances;

vector<int> dijkstra(vector<vector<pair<int, int>>>& graph, int start) {
     vector<int> dist(V + 1, -1);  // -1은 도달 불가능을 의미
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    
    dist[start] = 0;
    pq.push({0, start});  // {거리, 노드}

    while(!pq.empty()) {
        int curr_dist = pq.top().first;
        int curr = pq.top().second;
        pq.pop();

        if(curr_dist > dist[curr] && dist[curr] != -1) continue;

        for(auto& edge : graph[curr]) {
            int next = edge.first;
            int weight = edge.second;
            int new_dist = curr_dist + weight;

            if(dist[next] == -1 || new_dist < dist[next]) {
                dist[next] = new_dist;
                pq.push({new_dist, next});
            }
        }
    }
    
    return dist;
}

int main() {
    // input
    cin >> V >> E;
    graph.resize(V+1);
    
    cin >> K;

    for(int i = 0; i < E; i++) {
        cin >> u >> v >> w;
        graph[u].push_back({v, w});
    }

  // process
    vector<int> distances = dijkstra(graph, K);
    
    // output
    for (int i = 1; i <= V; i++) {
        if (distances[i] == -1) {
            cout << "INF" << '\n';
        } else {
            cout << distances[i] << '\n';
        }
    }
    return 0;
}
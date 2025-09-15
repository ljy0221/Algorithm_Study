import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Node implements Comparable<Node> {
        int vertex;
        int weight;
    
        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int destNode = Integer.parseInt(st.nextToken());

            List<List<Node>> graph = new ArrayList<>();
            List<List<Node>> reverseGraph = new ArrayList<>();
            
            for (int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
                reverseGraph.add(new ArrayList<>());
            }
            
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                graph.get(u).add(new Node(v, w));
                reverseGraph.get(v).add(new Node(u, w)); // 역방향 그래프
            }

            // 1. 시작점에서의 최단 거리
            int[] distFromStart = dijkstra(graph, startNode);
            
            if (distFromStart[destNode] == Integer.MAX_VALUE) {
                sb.append(-1).append('\n');
                continue;
            }

            // 2. 도착점에서의 최단 거리 (역방향)
            int[] distFromDest = dijkstra(reverseGraph, destNode);

            // 3. 최단 경로에 포함된 간선들 제거
            removeShortestPathEdges(graph, distFromStart, distFromDest, destNode);

            // 4. 거의 최단 경로 구하기
            int[] almostShortestDist = dijkstra(graph, startNode);

            if (almostShortestDist[destNode] == Integer.MAX_VALUE) {
                sb.append(-1).append('\n');
            } else {
                sb.append(almostShortestDist[destNode]).append('\n');
            }
        }
        
        System.out.print(sb.toString());
    }

    // 최단 경로에 포함된 간선들을 제거
    private static void removeShortestPathEdges(List<List<Node>> graph, 
            int[] distFromStart, int[] distFromDest, int destNode) {
        
        int shortestDist = distFromStart[destNode];
        
        for (int u = 0; u < graph.size(); u++) {
            final int currentU = u; // final로 복사
            graph.get(u).removeIf(edge -> {
                int v = edge.vertex;
                int weight = edge.weight;
                
                // 간선 (u -> v)가 최단 경로에 포함되는 조건
                return distFromStart[currentU] != Integer.MAX_VALUE && 
                       distFromDest[v] != Integer.MAX_VALUE &&
                       distFromStart[currentU] + weight + distFromDest[v] == shortestDist;
            });
        }
    }

    // 다익스트라 알고리즘
    private static int[] dijkstra(List<List<Node>> graph, int start) {
        final int INF = Integer.MAX_VALUE;
        int n = graph.size();
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            if (visited[u]) continue;
            visited[u] = true;

            for (Node edge : graph.get(u)) {
                int v = edge.vertex;
                int weight = edge.weight;

                if (!visited[v] && dist[u] != INF && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        return dist;
    }
}
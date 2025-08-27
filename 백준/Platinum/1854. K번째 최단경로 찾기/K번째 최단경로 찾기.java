import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int to, weight;
        
        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    static class Node implements Comparable<Node> {
        int vertex, dist;
        
        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
        }

        PriorityQueue<Integer>[] dist = new PriorityQueue[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));
        dist[1].offer(0);
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentDist = current.dist;

            if (dist[currentVertex].size() == k && currentDist > dist[currentVertex].peek()) {
                continue;
            }

            for (Edge edge : graph[currentVertex]) {
                int nextVertex = edge.to;
                int nextDist = currentDist + edge.weight;

                if (dist[nextVertex].size() < k) {
                    dist[nextVertex].offer(nextDist);
                    pq.offer(new Node(nextVertex, nextDist));
                } else if (nextDist < dist[nextVertex].peek()) {
                    dist[nextVertex].poll();
                    dist[nextVertex].offer(nextDist);
                    pq.offer(new Node(nextVertex, nextDist));
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (dist[i].size() == k) {
                sb.append(dist[i].peek()).append('\n');
            } else {
                sb.append(-1).append('\n');
            }
        }
        
        System.out.print(sb.toString());
    }
}
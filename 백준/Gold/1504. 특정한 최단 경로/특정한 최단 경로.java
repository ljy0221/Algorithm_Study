import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Edge implements Comparable<Edge>{
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        int V1 = Integer.parseInt(st.nextToken());
        int V2 = Integer.parseInt(st.nextToken());

        int[] distFrom1 = dijkstra(graph, 1);
        int[] distFromV1 = dijkstra(graph, V1);
        int[] distFromV2 = dijkstra(graph, V2);

        int result1 = -1, result2 = -1;

        // 경로 1: 1 → V1 → V2 → N
        if (distFrom1[V1] != Integer.MAX_VALUE && 
            distFromV1[V2] != Integer.MAX_VALUE && 
            distFromV2[N] != Integer.MAX_VALUE) {
            result1 = distFrom1[V1] + distFromV1[V2] + distFromV2[N];
        }

        // 경로 2: 1 → V2 → V1 → N  
        if (distFrom1[V2] != Integer.MAX_VALUE && 
            distFromV2[V1] != Integer.MAX_VALUE && 
            distFromV1[N] != Integer.MAX_VALUE) {
            result2 = distFrom1[V2] + distFromV2[V1] + distFromV1[N];
        }

        // 결과 출력
        if (result1 == -1 && result2 == -1) {
            System.out.println(-1);
        } else if (result1 == -1) {
            System.out.println(result2);
        } else if (result2 == -1) {
            System.out.println(result1);
        } else {
            System.out.println(Math.min(result1, result2));
}
    }
    private static int[] dijkstra(List<List<Edge>> graph, int start) {
        int n = graph.size()-1;
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int from = current.to;

            if(visited[from]) continue;
            visited[from] = true;

            for (Edge edge : graph.get(from)) {
                int to = edge.to;
                int weight = edge.weight;

                if(!visited[to] && dist[from] + weight < dist[to]) {
                    dist[to] = dist[from]+weight;
                    pq.offer(new Edge(to, dist[to]));
                }
            }
            
        }

        return dist;
    }
}

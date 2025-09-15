import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Edge implements Comparable<Edge> {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<List<Edge>> goGraph = new ArrayList<>();
        List<List<Edge>> backGraph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            goGraph.add(new ArrayList<>());
            backGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            goGraph.get(u).add(new Edge(v, w));
            backGraph.get(v).add(new Edge(u, w));
        }

        int[] toX = dijkstra(backGraph, X);
        int[] fromX = dijkstra(goGraph, X);
        int result = 0;

        for (int i = 1; i <= N; i++) {
            int totalTime = toX[i] + fromX[i];
            result = Math.max(result, totalTime);
        }

        System.out.println(result);
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
            int from = current.v;
            int currW = current.w;

            if(visited[from]) continue;
            visited[from] = true;

            for(Edge edge : graph.get(from)) {
                int to = edge.v;
                int weight = edge.w;

                if(!visited[to] && currW+weight < dist[to]) {
                    dist[to] = currW + weight;
                    pq.offer(new Edge(to, dist[to]));
                }
            }
        }

        return dist;
    }
}

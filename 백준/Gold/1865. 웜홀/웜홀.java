import java.io.*;
import java.util.*;

public class Main {
    
    // 간선(Edge) 정보를 저장하는 클래스. 필드 이름을 명확하게 통일했습니다.
    private static class Edge {
        int from, to;
        long weight; // 가중치 (w)

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int TC = Integer.parseInt(br.readLine());
        
        for (int tc = 0; tc < TC; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            
            List<Edge> graph = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                long t = Long.parseLong(st.nextToken());

                graph.add(new Edge(s, e, t));
                graph.add(new Edge(e, s, t));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                long t = Long.parseLong(st.nextToken());

                graph.add(new Edge(s, e, -t));
            }

            if (bellmanFord(N, graph)) { 
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.print(sb);
    }

    private static boolean bellmanFord(int N, List<Edge> graph) {

        long INF = 500 * 10000 * 1000L;
        long[] dist = new long[N + 1];
        Arrays.fill(dist, 0);
        

        for (int i = 1; i <= N - 1; i++) {
            boolean update = false;

            for (Edge edge : graph) {
                if (dist[edge.from] != INF && dist[edge.from] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.weight;
                    update = true;
                }
            }

            if (!update) {
                break;
            }
        }

        for (Edge edge : graph) {
            if (dist[edge.from] != INF && dist[edge.from] + edge.weight < dist[edge.to]) {
                return true;
            }
        }

        return false;
    }
}
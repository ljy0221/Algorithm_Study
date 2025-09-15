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
        int to, weight, time;

        public Edge(int to, int weight, int time) {
            this.to = to;
            this.weight = weight;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.time == o.time) {
                return Integer.compare(this.weight, o.weight);
            }

            return Integer.compare(this.time, o.time);
        }
        
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d  = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Edge(v, c, d));
        }

        int result = solve(graph, N, M);

        if(result == -1) {
            System.out.println("Poor KCM");
        } else {
            System.out.println(result);
        }
    }

    private static int solve(List<List<Edge>> graph, int N, int M) {
        int[][] dp = new int[M+1][N+1];
        
        for(int i = 0; i <= M; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][1] = 0;  // 시작점
        
        for(int cost = 0; cost < M; cost++) {
            for(int from = 1; from <= N; from++) {
                if(dp[cost][from] == Integer.MAX_VALUE) continue;
                
                for(Edge edge : graph.get(from)) {
                    int to = edge.to;
                    int w = edge.weight;
                    int t = edge.time;
                    
                    if(cost + w <= M) {
                        dp[cost + w][to] = Math.min(dp[cost + w][to], dp[cost][from] + t);
                    }
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for(int cost = 0; cost <= M; cost++) {
            result = Math.min(result, dp[cost][N]);
        }
        
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}

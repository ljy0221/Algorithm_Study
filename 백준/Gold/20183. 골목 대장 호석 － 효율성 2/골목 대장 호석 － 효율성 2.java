import java.io.*;
import java.util.*;

public class Main {
    private static class Edge {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static int N, M, A, B;
    private static long C;
    private static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Long.parseLong(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        long maxCost = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());

            graph.get(u).add(new Edge(v, cost));
            graph.get(v).add(new Edge(u, cost));
            maxCost = Math.max(maxCost, cost);
        }

        long left = 1, right = maxCost;
        long answer = -1;

        while (left <= right) {
            long mid = (left + right) / 2;
            
            if (canReach(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean canReach(long maxEdgeCost) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
        
        dist[A] = 0;
        pq.offer(new Edge(A, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int node = current.to;
            long cost = current.cost;

            if (cost > dist[node]) continue;
            if (node == B) return cost <= C;

            for (Edge next : graph.get(node)) {
                if (next.cost > maxEdgeCost) continue;
                
                long newCost = cost + next.cost;
                if (newCost <= C && newCost < dist[next.to]) {
                    dist[next.to] = newCost;
                    pq.offer(new Edge(next.to, newCost));
                }
            }
        }

        return dist[B] <= C;
    }
}
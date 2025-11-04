import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        int n, m, r;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        int[] items = new int[n+1];
        for (int i = 0; i < n; i++) {
            items[i+1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }

        int maxSum = -1;
        for (int start = 1; start <= n; start++) {
            int[] dist = dijkstra(start, graph);

            int sum = 0;
            for (int i = 1; i <= n; i++) {
                if(dist[i] <= m) {
                    sum += items[i];
                }
            }

            if(maxSum < sum) {
                maxSum = sum;
            }
        }

        System.out.println(maxSum);
    }

    private static int[] dijkstra(int start, List<List<Node>> graph) {
        int n = graph.size();
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n];

        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int u = curNode.to;
            int w = curNode.w;

            if(w > dist[u]) continue;

            for (Node node : graph.get(u)) {
                int v = node.to;
                int weight = node.w;

                if(dist[u] != INF && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }


        return dist;
    }

    private static class Node implements Comparable<Node>{
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
}

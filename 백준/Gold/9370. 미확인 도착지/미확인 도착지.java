import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int T,n,m,t,s,g,h;
    static List<List<Node>> graph;

    static class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }

        
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            
            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            int ghDist = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));

                if ((a == g && b == h) || (a == h && b == g)) {
                    ghDist = d;
                }
            }

            int[] distFromS = dijkstra(s);
            int[] distFromG = dijkstra(g);
            int[] distFromH = dijkstra(h);

            // sb.append(tc+": ");
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                int x = Integer.parseInt(br.readLine());

                int directDist = distFromS[x];
                int viaGH = distFromS[g] + ghDist + distFromH[x];
                int viaHG = distFromS[h] + ghDist + distFromG[x];

                if (directDist == viaGH || directDist == viaHG) {
                    ans.add(x);
                }
            }

            Collections.sort(ans);
            for (Integer num : ans) {
                sb.append(num).append(" ");
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(dist, 1000000000);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int node = curr.v;
            int w = curr.w;

            if(w > dist[node]) continue;

            for (Node neighbor : graph.get(node)) {
                if(w+neighbor.w < dist[neighbor.v]) {
                    dist[neighbor.v] = w + neighbor.w;
                    pq.offer(new Node(neighbor.v, dist[neighbor.v]));
                }
            }
        }

        return dist;
    }
}

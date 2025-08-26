import java.io.*;
import java.util.*;

public class Solution {
    private static int[] parent;

    private static class Node implements Comparable<Node>{
        int from, to, w;

        public Node (int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    private static int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    private static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return false;

        parent[rootY]=rootX;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            parent = new int[V+1];
            for (int i = 0; i <= V; i++) {
                parent[i] = i;
            }

            List<Node> graph = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                graph.add(new Node(u, v, w));
                graph.add(new Node(v, u, w));
            }

            Collections.sort(graph);

            long result = 0;
            int size = 0;

            for (Node node : graph) {
                if(union(node.from, node.to)) {
                    result += node.w;
                    size++;
                }

                if(size == V-1) break;
            }

            System.out.printf("#%d %d\n", tc, result);
        }
    }
}

import java.io.*;
import java.util.*;

public class Solution {
    static int[] parent;

    private static class Node implements Comparable<Node> {
        int from, to;
        double w;

        public Node(int from, int to, double w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.w, o.w);
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

    private static double calculateTax(double distance, double taxRate) {
        return taxRate*distance*distance;
    }

    private static double getDistance(int x1, int x2, int y1, int y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2)+Math.pow((y1-y2), 2));
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            parent = new int[N+1];
            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            int[] Xs = new int[N];
            int[] Ys = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Xs[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Ys[i] = Integer.parseInt(st.nextToken());
            }

            double tax = Double.parseDouble(br.readLine());

            List<Node> graph = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(i == j) continue;
                    double dist = getDistance(Xs[i], Xs[j], Ys[i], Ys[j]);
                    double w = calculateTax(dist, tax);
                    graph.add(new Node(i, j, w));
                    graph.add(new Node(j, i, w));
                }
            }

            Collections.sort(graph);
            double mstWeight = 0;
            int mstSize = 0;
            for (Node node : graph) {
                if(union(node.from, node.to)) {
                    mstWeight += node.w;
                    mstSize++;
                }

                if(mstSize == N-1) break;
            }

            System.out.printf("#%d %.0f\n", tc, mstWeight);
        }
    }
}

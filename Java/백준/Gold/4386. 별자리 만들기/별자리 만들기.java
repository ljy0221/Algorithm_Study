import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Pos {
        double x, y;

        public Pos(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Node implements Comparable<Node> {
        int from, to;        // 별의 인덱스 (0, 1, 2, ...)
        double distance;     // 두 별 사이의 거리
        
        public Node(int from, int to, double distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Node o) {
            return Double.compare(this.distance, o.distance);
        }
    }

    private static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n+1];
            rank = new int[n+1];

            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        private int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        private boolean union(int x , int y) {
            int rootX = find(x);
            int rootY  = find(y);

            if(rootX == rootY) return false;

            if(rank[rootX]>rank[rootY]) {
                parent[rootY]=rootX;
            } else if (rank[rootY]> rank[rootX]) {
                parent[rootX]=rootY;
            } else {
                parent[rootY]=rootX;
                rank[rootX]++;
            }

            return true;
        }
        
    }

    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        List<Pos> star = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double from = Double.parseDouble(st.nextToken());
            double to = Double.parseDouble(st.nextToken());

            star.add(new Pos(from, to));
        }

        List<Node> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                // i번째 별과 j번째 별 사이의 거리 계산
                Pos star1 = star.get(i);
                Pos star2 = star.get(j);
                double dist = Math.sqrt(Math.pow(star1.x - star2.x, 2) + Math.pow(star1.y - star2.y, 2));
                
                graph.add(new Node(i, j, dist));  // 인덱스 i, j 사용!
            }
        }

        List<Node> mst = kruskal(N, graph);

        double totalDistance = 0.0;

        for (Node node : mst) {
            totalDistance += node.distance;
        }

        System.out.printf("%.2f\n", totalDistance);
    }

    private static List<Node> kruskal(int N, List<Node> graph) {
        List<Node> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(N);
        Collections.sort(graph);

        for (Node node : graph) {
            if(uf.union(node.from, node.to)) {
                mst.add(node);
                if(mst.size() == N - 1) break;
            }
        }

        return mst;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Node> graph = new ArrayList<>();
        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                graph.add(new Node(i, j, map[i][j]));
            }
        }

        System.out.println(kruskal(N, graph));
    }

    private static long kruskal(int n, List<Node> graph) {
        long totalWeight = 0;
        Collections.sort(graph);
        List<Node> mst = new ArrayList<>();
        UnionFind uf = new UnionFind(n);

        for (Node node : graph) {
            if(uf.union(node.from, node.to)) {
                totalWeight+= node.weight;
                mst.add(node);

                if(mst.size() == n-1) break;
            }
        }

        return totalWeight;
    }

    private static class Node implements Comparable<Node>{
        int from, to, weight;

        public Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
        
    }

    private static class UnionFind {
        int[] parent;
        int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
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

        private boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY) return false;

            if(rank[rootX]>rank[rootY]) {
                parent[rootY]=rootX;
            } else if(rank[rootY]>rank[rootX]){
                parent[rootX]=rootY;
            } else {
                parent[rootY]=rootX;
                rank[rootX]++;
            }

            return true;
        }
    }
}
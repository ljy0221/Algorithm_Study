import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

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
            parent = new int[n+1];
            rank = new int[n+1];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
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

            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }

    private static List<Node> kruscal(int N, List<Node> graph) {
        List<Node> mst = new ArrayList<>();
        Collections.sort(graph);
        UnionFind uf = new UnionFind(N);

        for (Node node : graph) {
            if(uf.union(node.from, node.to)){
                mst.add(node);
            }
            if(mst.size() == N - 1) break;
        }

        Collections.sort(mst);
        return mst;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Node> graph = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.add(new Node(from, to, weight));
        }

        List<Node> mst = kruscal(N, graph);
        mst.remove(mst.size()-1);
    
        int totalWeight = 0;

        for (Node node : mst) {
            totalWeight += node.weight;
        }

        System.out.println(totalWeight);
    }
}

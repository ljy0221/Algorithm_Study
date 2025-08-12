import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] powerStation;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        powerStation = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            powerStation[i] = Integer.parseInt(st.nextToken());
        }

        List<Node> graph = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.add(new Node(u, v, w));
        }

        System.out.println(kruskal(N, graph));
    }

    private static int kruskal(int n, List<Node> graph) {
        Collections.sort(graph);
        UnionFind uf = new UnionFind(n);
        
        // 🔥 핵심: 모든 발전소를 하나의 그룹으로 만들기
        for (int i = 1; i < powerStation.length; i++) {
            uf.union(powerStation[0], powerStation[i]);
        }
        
        int totalWeight = 0;
        int edges = 0;
        
        for (Node node : graph) {
            if (uf.union(node.from, node.to)) {  // 다른 그룹이면 연결
                totalWeight += node.weight;
                edges++;
                
                if (edges == n - powerStation.length) break;  // 발전소 수만큼 빼기
            }
        }
        
        return totalWeight;
    }

    private static class Node implements Comparable<Node> {
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

        private boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY) return false;

            if(rank[rootX] > rank[rootY]) {
                parent[rootY]=rootX;
            } else if(rank[rootX] < rank[rootY]) {
                parent[rootX]=rootY;
            } else {
                parent[rootY]=rootX;
                rank[rootX]++;
            }

            return true;
        }
    }
}

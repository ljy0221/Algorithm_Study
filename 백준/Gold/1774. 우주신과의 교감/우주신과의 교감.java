import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Pos {
        int x, y, index;


        public Pos(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    private static class Node implements Comparable<Node>{
        int from, to;
        double distance;

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
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Pos> godList = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            godList.add(new Pos(x, y, i));
        }
        
        List<int[]> connectedList = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x= Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            connectedList.add(new int[] {x, y});
        }
        
        List<Node> graph = new ArrayList<>();
        for (int i = 0; i < N-1; i++) {
            for (int j = i+1; j < N; j++) {
                Pos p1 = godList.get(i);
                Pos p2 = godList.get(j);
                
                double distance = Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
                graph.add(new Node(i, j, distance));
            }
        }
        
        double distance = kurskal(N, graph, connectedList);

        System.out.printf("%.2f\n", distance);
    }

    private static double kurskal(int n, List<Node> graph, List<int[]> connectedList) {
        double totalWeight = 0.0;
        Collections.sort(graph);
        UnionFind uf = new UnionFind(n);
        int mstEdges = 0;
    
        // 이미 연결된 통로 처리
        for (int[] node : connectedList) {
            int from = node[0] - 1;
            int to = node[1] - 1;
            if(uf.union(from, to)) {
                mstEdges++;
            }
        }
    
        // 나머지 간선들 처리
        for (Node node : graph) {
            if(uf.union(node.from, node.to)) {
                totalWeight += node.distance;
                mstEdges++;
                
                if(mstEdges == n - 1) break;
            }
        }
    
        return totalWeight;
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

            if (rootX == rootY) return false;

            if(rank[rootX] > rank[rootY]) {
                parent[rootY]=rootX;
            } else if(rank[rootY] > rank[rootX]){
                parent[rootX]=rootY;
            } else {
                parent[rootY]=rootX;
                rank[rootX]++;
            }
            return true;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
    
            if(N == 0 && M == 0) break;

            // union-find init
            UnionFind uf = new UnionFind(N);
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                
                char op = st.nextToken().charAt(0);
                if(op == '!') {
                    int a = Integer.parseInt(st.nextToken());    
                    int b = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());

                    uf.union(a, b, w);
                    
                } else if(op == '?') {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    long ans = uf.getWeightDiff(a, b);

                    sb.append(ans == Long.MAX_VALUE ? "UNKNOWN" : ans).append('\n');
                }
            }
            
            System.out.print(sb);
        }
    }

    private static class UnionFind {
        int[] parent;
        int[] rank;
        long[] weight;
        
        public UnionFind(int n) {
            parent = new int[n+1];
            rank = new int[n+1];
            weight = new long[n+1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
                weight[i] = 0;
            }
        }

        private int find(int x) {
            if(parent[x] != x) {
                int oldParent = parent[x];
                parent[x] = find(parent[x]);
                weight[x] += weight[oldParent];
            }
            return parent[x];
        }

        private void union(int x, int y, long w) {
            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY) return;

            // x가 y보다 w만큼 무겁다 → weight[x] - weight[y] = w
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                weight[rootX] = weight[y] - weight[x] + w;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                weight[rootY] = weight[x] - weight[y] - w;
            } else {
                parent[rootY] = rootX;
                weight[rootY] = weight[x] - weight[y] - w;
                rank[rootX]++;
            }
        }

        private long getWeightDiff(int x, int y) {
            if (find(x) != find(y)) {
                return Long.MAX_VALUE; // "UNKNOWN"
            }
            return weight[x] - weight[y];
        }
    }

    
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        UnionFind uf = new UnionFind(N);
        for (int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(uf.isConnected(a, b)) {
                System.out.println(m);
                return;
            }

            uf.union(a, b);
        }
        System.out.println(0);
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

        private void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if(isConnected(rootX, rootY)) return;

            if(rootX > rootY) {
                parent[rootY] = rootX;
            } else if (rootX < rootY) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
        
        private boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
        
    }
}

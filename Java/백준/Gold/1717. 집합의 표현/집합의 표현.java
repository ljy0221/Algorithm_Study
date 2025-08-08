import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        UnionFind uf = new UnionFind(n);

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switch (op) {
                case 0:
                    uf.union(a, b);
                    break;
                case 1:
                    System.out.println(uf.find(a) == uf.find(b) ? "YES" : "NO");
                    break;
            }
        }
    }

    static class UnionFind {
        int[] parent;
        int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n+1];
            rank = new int[n+1];

            for (int i = 1; i <= n; i++) {
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

            // 둘의 뿌리가 같다면 합치기 불가
            if(rootX == rootY) return false;

            if(rank[rootX] < rank[rootY]) {
                parent[rootX]=rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }
}

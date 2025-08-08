import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st;

        UnionFind uf = new UnionFind(N);
        for(int from = 1; from <= N; from++) {
            st = new StringTokenizer(br.readLine());
            for (int to = 1; to <= N; to++) {
                int target = Integer.parseInt(st.nextToken());
                if(target == 1) {
                    uf.union(from, to);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int[] plan = new int[M];

        for(int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        int rootPlan = uf.find(plan[0]);
        for (int i = 1; i < M; i++) {
            if(rootPlan != uf.find(plan[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
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

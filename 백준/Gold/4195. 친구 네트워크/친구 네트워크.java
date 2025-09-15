import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static HashMap<String, Integer> friends;
    static int friendsIndex;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for(int n = 0; n < N; n++) {
            int F = Integer.parseInt(br.readLine());
            friends = new HashMap<>();
            friendsIndex = 0;
            UnionFind uf = new UnionFind(2*F);
            
            for(int f = 0; f < F; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();

                int a = nameToIndex(name1);
                int b = nameToIndex(name2);

                int networkSize = uf.union(a, b);

                System.out.println(networkSize);
            }
        }
    }
    private static int nameToIndex(String name) {
        if(!friends.containsKey(name)) {
            friends.put(name, friendsIndex++);
        }
        return friends.get(name);
    }

    private static class UnionFind {
        int[] parent;
        int[] rank;
        int[] size;
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
                size[i] = 1;
            }
        }

        private int find(int x) {
            if(parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        private int union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if(rootX == rootY) return size[rootX];

            if(rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
                return size[rootX];
            } else if(rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                return size[rootY];
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
                size[rootY] += size[rootX];
                rank[rootX]++;
                return size[rootX];
            }
        }
        
    }
}

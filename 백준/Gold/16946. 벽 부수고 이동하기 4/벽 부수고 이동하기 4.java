import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static UnionFind uf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        uf = new UnionFind(N * M);

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    int curr = i * M + j;

                    if (j + 1 < M && map[i][j + 1] == 0) {
                        uf.union(curr, i * M + (j + 1));
                    }

                    if (i + 1 < N && map[i + 1][j] == 0) {
                        uf.union(curr, (i + 1) * M + j);
                    }
                }
            }
        }

        char[] line = new char[M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    line[j] = (char)('0' + wallBreak(i, j) % 10);
                } else {
                    line[j] = '0';
                }
            }
            bw.write(line);
            bw.newLine();
        }
        
        bw.flush();
        bw.close();
        br.close();
    }

    private static int wallBreak(int r, int c) {
        int result = 1;

        int[] roots = new int[4];
        int rootCount = 0;

        if (r > 0 && map[r-1][c] == 0) {
            roots[rootCount++] = uf.find((r-1) * M + c);
        }

        if (c < M-1 && map[r][c+1] == 0) {
            int root = uf.find(r * M + (c+1));
            if (!contains(roots, rootCount, root)) {
                roots[rootCount++] = root;
            }
        }

        if (r < N-1 && map[r+1][c] == 0) {
            int root = uf.find((r+1) * M + c);
            if (!contains(roots, rootCount, root)) {
                roots[rootCount++] = root;
            }
        }

        if (c > 0 && map[r][c-1] == 0) {
            int root = uf.find(r * M + (c-1));
            if (!contains(roots, rootCount, root)) {
                roots[rootCount++] = root;
            }
        }

        for (int i = 0; i < rootCount; i++) {
            result += uf.getSize(roots[i]);
        }
        
        return result;
    }
    
    private static boolean contains(int[] arr, int len, int val) {
        for (int i = 0; i < len; i++) {
            if (arr[i] == val) return true;
        }
        return false;
    }

    static class UnionFind {
        int[] parent;
        int[] size;
        
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX != rootY) {
                if (size[rootX] < size[rootY]) {
                    int temp = rootX;
                    rootX = rootY;
                    rootY = temp;
                }
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            }
        }
        
        public int getSize(int x) {
            return size[find(x)];
        }
    }
}
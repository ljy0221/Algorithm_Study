import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    
    static int N;
    static int[][] desertMap;
    static boolean[][] visited;
    static Set<Integer> visitedDesserts;
    static int maxLength;
    
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            desertMap = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    desertMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            maxLength = -1;
            
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    visited = new boolean[N][N];
                    visitedDesserts = new HashSet<>();
                    
                    visited[r][c] = true;
                    visitedDesserts.add(desertMap[r][c]);
                    
                    dfs(r, c, r, c, 0, 1);
                    
                    visited[r][c] = false;
                    visitedDesserts.remove(desertMap[r][c]);
                }
            }

            System.out.printf("#%d %d\n", tc, maxLength);
        }
    }
    
    static void dfs(int startR, int startC, int currentR, int currentC, int direction, int length) {
        
        int nr = currentR + dr[direction];
        int nc = currentC + dc[direction];
        
        if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
            if (direction < 3) {
                dfs(startR, startC, currentR, currentC, direction + 1, length);
            }
            return;
        }
        
        if (nr == startR && nc == startC && length >= 4) {
            maxLength = Math.max(maxLength, length);
            return;
        }
        
        if (visited[nr][nc] || visitedDesserts.contains(desertMap[nr][nc])) {
            if (direction < 3) {
                dfs(startR, startC, currentR, currentC, direction + 1, length);
            }
            return;
        }
        
        visited[nr][nc] = true;
        visitedDesserts.add(desertMap[nr][nc]);
        
        dfs(startR, startC, nr, nc, direction, length + 1);
        
        if (direction < 3) {
            dfs(startR, startC, nr, nc, direction + 1, length + 1);
        }
        
        visited[nr][nc] = false;
        visitedDesserts.remove(desertMap[nr][nc]);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[][] cheese;
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int remain = 0;
        while (hasCheese()) {
            remain = 0;
            time++;
            
            // 1. 외부 공기 표시
            boolean[][] outerAir = findOuterAir();
            
            // 2. 녹을 치즈 표시 (바로 녹이지 않고 표시만)
            boolean[][] toMelt = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (cheese[i][j] == 1) {
                        for (int d = 0; d < 4; d++) {
                            int nr = i + dr[d];
                            int nc = j + dc[d];
                            if (isValid(nr, nc) && outerAir[nr][nc]) {
                                toMelt[i][j] = true;
                                break;
                            }
                        }
                    }
                }
            }
            
            // 3. 치즈 녹이기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (toMelt[i][j]) {
                        cheese[i][j] = 0;
                        remain++;
                    }
                }
            }
        }

        System.out.println(time);
        System.out.println(remain);
    }

    // BFS로 외부 공기 찾기
    private static boolean[][] findOuterAir() {
        boolean[][] outerAir = new boolean[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        outerAir[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (isValid(nr, nc) && !visited[nr][nc] && cheese[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    outerAir[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        return outerAir;
    }

    private static boolean hasCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValid(int nr, int nc) {
        return 0 <= nr && nr < N && 0 <= nc && nc < M;
    }
}
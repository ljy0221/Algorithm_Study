import java.io.*;
import java.util.*;

public class Solution {
    static class Pos {
        int r, c, time;
        
        public Pos(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    
    static int N, M;
    static char[][] map;
    static int[][] demonTime;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            demonTime = new int[N][M];

            int startR, startC, endR, endC;
            startR = startC = endR = endC = -1;

            Queue<Pos> demonQueue = new ArrayDeque<>();

            for (int i = 0; i < N; i++) {
                String in = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = in.charAt(j);
                    demonTime[i][j] = Integer.MAX_VALUE;

                    if(map[i][j] == 'S') {
                        startR = i;
                        startC = j;
                    } else if(map[i][j] == 'D') {
                        endR = i;
                        endC = j;
                    } else if(map[i][j] == '*') {
                        demonQueue.offer(new Pos(i, j, 0));
                        demonTime[i][j] = 0;
                    }
                }
            }

            spreadDemon(demonQueue);
            
            int ans = findShortestPath(startR, startC, endR, endC);

            if(ans == -1) {
                System.out.printf("#%d GAME OVER\n", tc);
            } else {
                System.out.printf("#%d %d\n", tc, ans);
            }
        }
    }

    private static void spreadDemon(Queue<Pos> demonQueue) {
        while (!demonQueue.isEmpty()) {
            Pos current = demonQueue.poll();
            int r = current.r;
            int c = current.c;
            int time = current.time;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nTime = time+1;

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(map[nr][nc] == 'X' || map[nr][nc] == 'D') continue;
                if(demonTime[nr][nc] <= nTime) continue;

                demonTime[nr][nc] = nTime;
                demonQueue.offer(new Pos(nr, nc, nTime));
            }
        }
    }

    private static int findShortestPath(int startR, int startC, int endR, int endC) {
        Queue<Pos> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        
        queue.offer(new Pos(startR, startC, 0));
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            Pos current = queue.poll();
            int r = current.r;
            int c = current.c;
            int time = current.time;

            if(r == endR && c == endC) {
                return time;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nTime = time+1;

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if(map[nr][nc] == 'X') continue;
                if(visited[nr][nc]) continue;
                if(demonTime[nr][nc] <= nTime) continue;

                visited[nr][nc] = true;
                queue.offer(new Pos(nr, nc, nTime));
            }
        }

        return -1;
    }
}
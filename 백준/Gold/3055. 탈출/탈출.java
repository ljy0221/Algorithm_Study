import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        int destR, destC, startR, startC;
        destR = destC = startR = startC = -1;
        Queue<int[]> initialWaterPositions = new ArrayDeque<>(); 

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'D') {
                    destR = i;
                    destC = j;
                } else if (map[i][j] == 'S') {
                    startR = i;
                    startC = j;
                } else if (map[i][j] == '*') {
                    initialWaterPositions.offer(new int[] { i, j });
                }
            }
        }

        int[][] waterTime = new int[R][C];
        for (int[] row : waterTime) {
            Arrays.fill(row, INF);
        }

        for (int[] pos : initialWaterPositions) {
            waterTime[pos[0]][pos[1]] = 0;
        }

        Queue<int[]> q = new ArrayDeque<>(initialWaterPositions);
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int time = waterTime[r][c];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'X' || map[nr][nc] == 'D'
                        || waterTime[nr][nc] != INF) {
                    continue;
                }

                waterTime[nr][nc] = time + 1;
                q.offer(new int[] { nr, nc });
            }
        }

        int[][] hedgehogTime = new int[R][C];
        for (int[] row : hedgehogTime) {
            Arrays.fill(row, INF);
        }
        hedgehogTime[startR][startC] = 0;

        Queue<int[]> hedgehogQ = new ArrayDeque<>();
        hedgehogQ.offer(new int[] { startR, startC });

        while (!hedgehogQ.isEmpty()) {
            int[] cur = hedgehogQ.poll();
            int r = cur[0];
            int c = cur[1];
            int time = hedgehogTime[r][c];

            if (r == destR && c == destC) {
                System.out.println(time);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nextTime = time + 1;

                if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'X'
                        || hedgehogTime[nr][nc] != INF) {
                    continue;
                }

                if (waterTime[nr][nc] > nextTime) {
                    hedgehogTime[nr][nc] = nextTime;
                    hedgehogQ.offer(new int[] { nr, nc });
                }
            }
        }
        
        System.out.println("KAKTUS");
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        int vaccumR = -1;
        int vaccumC = -1;
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                if(map[r][c] == -1) {
                    vaccumR = r;
                    vaccumC = c;
                }
            }
        }

        while (T-- > 0) {
            /*
                1. 미세먼지 확산
                (r,c)에 있는게 4방 확산 -> 공청기, 벽 X
                확산량: val/5;
                그자리에 남은양 val - val/5*확산 방향 수;
            */

            int[][] add = new int[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if(map[r][c] > 0) {
                        for (int i = 0; i < 4; i++) {
                            int nr = r + dr[i];
                            int nc = c + dc[i];

                            if(nr < 0 || nr >= R || nc < 0 || nc >= C 
                                || map[nr][nc] == -1) continue;

                            add[nr][nc] += map[r][c]/5;
                            add[r][c] -= map[r][c]/5;
                        }
                    }
                }
            }

            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    map[r][c] += add[r][c];
                }
            }

            
            /*
                2. 공기청정기 작동
                위쪽은 반시계 아래쪽은 시계
                바람이 먼지를 밈, 테두리 만
                공청기 위치로 온 먼지 제거(이때만 숫자 변동 발생)
             */

            //상방(반시계) (vaccumR-1, 0)
            {
                for (int r = vaccumR-2; r > 0; r--) {
                    map[r][0] = map[r-1][0];
                }
                for (int c = 0; c < C-1; c++) {
                    map[0][c] = map[0][c+1];
                }
                for (int r = 0; r < vaccumR-1; r++) {
                    map[r][C-1] = map[r+1][C-1];
                }
                for (int c = C-1; c > 1; c--) {
                    map[vaccumR-1][c] = map[vaccumR-1][c-1];
                }
                map[vaccumR-1][1] = 0;
            }

            //하방(시계) (vaccumR, 0)
            {
                for (int r = vaccumR+1; r < R-1; r++) {
                    map[r][0] = map[r+1][0];
                }
                for (int c = 0; c < C-1; c++) {
                    map[R-1][c] = map[R-1][c+1];
                }
                for (int r = R-1; r > vaccumR; r--) {
                    map[r][C-1] = map[r-1][C-1];
                }
                for (int c = C-1; c > 1; c--) {
                    map[vaccumR][c] = map[vaccumR][c-1];
                }

                map[vaccumR][1] = 0;
            }
        }

        int totalDust = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(map[r][c] > 0) {
                    totalDust += map[r][c];
                }
            }
        }

        System.out.println(totalDust);
    }
}
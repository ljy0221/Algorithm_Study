import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] direction = {
        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d =  Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while (true) {
            if(map[r][c] == 0) {
                map[r][c] = 2;
                cnt++;
            }

            boolean canMove = false;

            for(int i = 0; i < 4; i++) {
                d = (d+3) % 4;

                int nr = r + direction[d][0];
                int nc = c + direction[d][1];

                if(0 <= nr && nr < N && 0 <= nc && nc < M && map[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                    canMove = true;
                    break;
                }
            }

            if (canMove) {
                continue;
            }

            int br = r - direction[d][0];
            int bc = c - direction[d][1];

            if(0 <= br && br < N && 0 <= bc && bc < M && map[br][bc] != 1) {
                r = br;
                c = bc;
            } else {
                break;
            }
        }

        System.out.println(cnt);
    }

    

    private static class State {
        int r, c, d;

        public State(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}

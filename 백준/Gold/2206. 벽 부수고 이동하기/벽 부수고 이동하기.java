
import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] map = new String[N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }

        boolean[][][] visited = new boolean[N][M][2]; // can destroy wall once
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {0, 0, 0, 0});
        visited[0][0][0] = true;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int bestMove = -1;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int breakable = curr[2];
            int moves = curr[3];

            if (r == N-1 && c == M-1) {
                bestMove = moves+1;
                break;
            }


            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if(map[nr].charAt(nc) == '1') {
                    if(breakable == 0 && !visited[nr][nc][1]) {
                        visited[nr][nc][1] = true;
                        q.offer(new int[]{nr, nc, 1, moves+1});
                    }
                } else {
                    if(!visited[nr][nc][breakable]) {
                        visited[nr][nc][breakable] = true;
                        q.offer(new int[] {nr, nc, breakable, moves+1});
                    }
                }
            }
        }

        System.out.println(bestMove);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int tc = 0; tc < 10; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[][] maze = new int[100][100];
            
            int startR = -1, startC = -1, destR = -1, destC = -1;
            for (int i = 0; i < 100; i++) {
                String in = br.readLine();
                for (int j = 0; j < 100; j++) {
                    maze[i][j] = in.charAt(j) - '0';

                    if(maze[i][j] == 2) {
                        startR = i;
                        startC = j;
                    } else if(maze[i][j] == 3) {
                        destR = i;
                        destC = j;
                    }
                }
            }
            
            Queue<int[]> Q = new ArrayDeque<>();
            boolean[][] visited = new boolean[100][100];
            Q.offer(new int[] {startR, startC});
            visited[startR][startC] = true;
            int ans = 0;
            while(!Q.isEmpty()) {
                int[] current = Q.poll();
                int r = current[0];
                int c = current[1];

                if(r == destR && c == destC) {
                    ans = 1;
                    break;
                }
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if(nr < 0 || nr >= 100 || nc < 0 || nc >= 100 || visited[nr][nc] || maze[nr][nc] == 1) continue;

                    visited[nr][nc] = true;
                    Q.offer(new int[] {nr, nc});
                }
            }

            System.out.printf("#%d %d\n", n, ans);
        }
    }

}

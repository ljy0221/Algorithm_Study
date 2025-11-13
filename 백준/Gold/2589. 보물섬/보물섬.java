import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int H, W;

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static char[][] map;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            map[i] = input.toCharArray();
        }

        int maxDiameter = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'L') {
                    int diameter = bfs(i, j);

                    if (diameter > maxDiameter) {
                        maxDiameter = diameter;
                    }
                }
            }
        }

        System.out.println(maxDiameter);
    }

    private static int bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { i, j, 0 });
        int[][] dist = new int[H][W];
        dist[i][j] = 1;

        int maxDist = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int diameter = cur[2];

            maxDist = Math.max(maxDist, diameter);

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                int newDiameter = diameter + 1;

                if (nr < 0 || nr >= H || nc < 0 || nc >= W ) continue;
                if(map[nr][nc] == 'W') continue;
                if(dist[nr][nc] > 0 ) continue;

                dist[nr][nc] = newDiameter;
                q.offer(new int[] { nr, nc, newDiameter });
            }
        }

        return maxDist;
    }
}

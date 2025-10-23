import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] dircetion = {
            { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }
    };

    private static class Point {
        int r, c, d;

        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    private static boolean[][] visited;
    private static int[][] map;
    private static int[][] result;
    private static int n, m;

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        result = new int[n][m];
        Point start = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    start = new Point(i, j, 0);
                } else if (map[i][j] == 1) {
                    result[i][j] = -1;
                }
            }
        }

        bfs(start);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    sb.append(0);
                } else {
                    sb.append(result[i][j]);
                }
                sb.append(j == m - 1 ? "" : " ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb.toString());
    }

    private static void bfs(Point start) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.r][start.c] = true;
        result[start.r][start.c] = 0;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dircetion[i][0];
                int nc = cur.c + dircetion[i][1];

                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc] && map[nr][nc] != 0) {
                    visited[nr][nc] = true;
                    int dist = cur.d+1;
                    result[nr][nc] = dist;
                    q.offer(new Point(nr, nc, dist));
                }
            }
        }
    }
}

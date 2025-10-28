import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] dircection = {
        {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] campus = new String[N];

        int[] start = new int[2];
        for (int i = 0; i < N; i++) {
            campus[i] = br.readLine();
            for (int j = 0; j < M; j++) {
                if(campus[i].charAt(j) == 'I') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        int meet = 0;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        visited[start[0]][start[1]] = true;
        q.offer(start);

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if(campus[cur[0]].charAt(cur[1]) == 'P' ) {
                meet++;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dircection[i][0];
                int nc = cur[1] + dircection[i][1];

                if(0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && campus[nr].charAt(nc) != 'X') {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
        }

        System.out.println(meet == 0 ? "TT" : meet);


    }
}

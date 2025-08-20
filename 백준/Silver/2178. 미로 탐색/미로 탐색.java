import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        // int[] : r, c, moves
        queue.offer(new int[] {0, 0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int moves = curr[2];

            if(r == N-1 && c == M-1) {
                System.out.println(moves+1);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if(map[r].charAt(c) == '0') continue;

                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.offer(new int[] {nr, nc, moves+1});
            }
        }
    }
}

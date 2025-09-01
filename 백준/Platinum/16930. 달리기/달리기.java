import java.io.*;
import java.util.*;

public class Main {
    private static class Pos {
        int r, c;

        public Pos (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int N, M, K;
    private static char[][] room;
    private static int[] dr = {-1, 0, 1, 0};
    private static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        room = new char[N][M];

        for (int i = 0; i < N; i++) {
            room[i] = br.readLine().toCharArray();
        }
        
        st = new StringTokenizer(br.readLine());
        Pos start = new Pos(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
        Pos dest = new Pos(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

        Queue<Pos> queue = new ArrayDeque<>();
        int[][] visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], -1);
        }

        queue.offer(start);
        visited[start.r][start.c] = 0;
        
        while (!queue.isEmpty()) {
            Pos current = queue.poll();
            int r = current.r;
            int c = current.c;
            int currentTime = visited[r][c];

            if(r == dest.r && c == dest.c) {
                break;
            }

            for (int d = 0; d < 4; d++) {
                for (int k = 1; k <= K; k++) {
                    int nr = r + dr[d]*k;
                    int nc = c + dc[d]*k;

                    if(!isValid(nr, nc)) break;
                    
                    // 방문하지 않은 길이면 큐에 삽입, 방문 처리
                    if(visited[nr][nc] == -1) {
                        queue.offer(new Pos(nr, nc));
                        visited[nr][nc] = currentTime + 1;
                    }
                    // 방문한 길인데 현재 경로가 더 길거나 같다면 계속 탐색
                    else if (visited[nr][nc] >= currentTime + 1) {
                        continue; // 이 칸은 건너뛰고 다음 칸 탐색
                    }
                    // 방문한 길인데 현재 경로가 더 짧다면 그 방향 중단
                    else {
                        break;
                    }
                }
            }
        }

        System.out.println(visited[dest.r][dest.c]);
    }
    
    private static boolean isValid(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < M && room[nr][nc] != '#';
    }
}
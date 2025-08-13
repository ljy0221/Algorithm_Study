import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tcNum = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());

            if(N == 0) break;

            int map[][] = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st= new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = dijkstra(map, 0, 0);
            sb.append("Problem " + tcNum + ": " + result).append('\n');
            tcNum++;
        }

        System.out.println(sb);
    }
    private static int dijkstra(int[][] map, int startR, int startC) {
        int n = map.length;
        int[][] dist = new int[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[startR][startC] = map[startR][startC];
        pq.offer(new int[] {startR, startC, map[startR][startC]});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currR = current[0];
            int currC = current[1];
            int currWeight = current[2];

            if(currWeight > dist[currR][currC]) continue;

            for (int i = 0; i < 4; i++) {
                int nr = currR + dr[i];
                int nc = currC + dc[i];

                if(nr >= 0 && nr < n && nc >= 0 && nc < n) {
                    int newWeight = currWeight + map[nr][nc];
                    
                    if(newWeight < dist[nr][nc]) {
                        dist[nr][nc] = newWeight;
                        pq.offer(new int[]{nr, nc, newWeight});
                    }
                }
            }
        }

        return dist[n-1][n-1];
    }

}

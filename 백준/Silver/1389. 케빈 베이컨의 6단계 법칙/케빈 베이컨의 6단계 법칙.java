import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 101;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    map[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u][v] = 1;
            map[v][u] = 1;
        }


        // floyd-washall
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 0; start <= N; start++) {
                for (int end = 0; end <= N; end++) {
                    if(map[start][mid] != INF && map[mid][end] != INF) {
                        map[start][end] = Math.min(map[start][end], map[start][mid] + map[mid][end]);
                    }
                }
            }
        }

        int minKB = Integer.MAX_VALUE;
        int user = -1;

        for (int i = 1; i <= N; i++) {
            int curKB = 0;
            for (int j = 1; j <= N; j++) {
                curKB += map[i][j];
            }

            if(curKB < minKB) {
                minKB = curKB;
                user = i;
            }
        }

        System.out.println(user);
    }
}

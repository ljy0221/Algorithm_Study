import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (R-- > 0) {
            for (int i = 0; i < Math.min(N, M)/2; i++) {
                int tmp = map[i][i];

                for (int l = i; l < M-i-1; l++) {
                    map[i][l] = map[i][l+1];
                }

                for (int u = i; u < N-i-1; u++) {
                    map[u][M-i-1] = map[u+1][M-i-1];
                }

                for (int r = M-i-1; r > i; r--) {
                    map[N-1-i][r] = map[N-1-i][r-1];
                }

                for (int d = N-i-1; d > i; d--) {
                    map[d][i] = map[d-1][i];
                }

                map[i+1][i] = tmp;
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
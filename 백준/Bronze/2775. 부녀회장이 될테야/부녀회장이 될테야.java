import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int K, N;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        memo = new int[15][15];

        for (int i = 1; i <= 14; i++) {
            memo[0][i] = i;
        }

        for (int i = 1; i <= 14; i++) { // floor
            for (int j = 1; j <= 14; j++) { // room
                memo[i][j] = memo[i][j-1]+memo[i-1][j];
            }
        }

        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());

            sb.append(memo[K][N]).append('\n');
        }

        System.out.print(sb);
    }
}

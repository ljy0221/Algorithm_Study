import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int M, N;
    static int[] edge;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        edge = new int[2 * M - 1];
        Arrays.fill(edge, 1);

        for (int day = 0; day < N; day++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            int idx = 0;
            
            idx += zero;
            
            for (int i = 0; i < one; i++) {
                edge[idx++] += 1;
            }
            
            for (int i = 0; i < two; i++) {
                edge[idx++] += 2;
            }
        }

        // 출력
        for (int j = 0; j < M; j++) {
            if (j > 0) sb.append(' ');
            sb.append(edge[M - 1 + j]);
        }
        sb.append('\n');

        for (int i = 1; i < M; i++) {
            sb.append(edge[M - 1 - i]);
            for (int j = 1; j < M; j++) {
                sb.append(' ').append(edge[M - 1 + j]);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
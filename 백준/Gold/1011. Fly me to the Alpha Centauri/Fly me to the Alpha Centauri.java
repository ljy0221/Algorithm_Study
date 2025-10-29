import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            long gap = y - x;

            long k = (long) Math.sqrt(gap);

            long K = k * k;

            if (gap == K) {
                sb.append(2 * k - 1);
            } else if (gap <= K + k) {
                sb.append(2 * k);
            } else {
                sb.append(2 * k + 1);
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}

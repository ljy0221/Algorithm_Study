import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        int[] zero = new int[41];
        int[] one = new int[41];
        /*
         * fibo(0) = 0 zero[0] = 1, one[0] = 0
         * fibo(1) = 1 zero[1] = 0, one[1] = 1;
         * fibo(2) = 1 (1+0) zero[2] = 1, one[2] = 1
         * fibo(3) = 2 (1+1) zero[3] = 1, one[3] = 2;
         * fibo(4) = 3 (1+2)
         * fibo(5) = 5 (2+3)
         */

        zero[0] = 1;
        zero[1] = 0;
        one[0] = 0;
        one[1] = 1;

        for (int i = 2; i <= 40; i++) {
            zero[i] = zero[i - 2] + zero[i - 1];
            one[i] = one[i - 2] + one[i - 1];
        }

        for (int i = 0; i < T; i++) {
            Integer idx = Integer.parseInt(br.readLine());

            sb.append(zero[idx]).append(" ").append(one[idx]).append('\n');
        }

        System.out.print(sb);
    }
}

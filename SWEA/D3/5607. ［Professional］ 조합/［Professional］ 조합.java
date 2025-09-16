import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T,N,R;
    static final int MOD = 1234567891;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            System.out.printf("#%d %d\n", tc, combination(N, R));
        }
    }

    private static long combination(int n, int r) {
        long up = 1;    // 분자
        long down = 1;  // 분모

        for (int i = 0; i < r; i++) {
            up = (up*(n-i))%MOD;
        }

        for (int i = 1; i <= r; i++) {
            down = (down*i)%MOD;
        }

        return (up*modInverse(down, MOD)) % MOD;
    }

    private static long modInverse(long base, int mod) {
        return power(base, mod-2, mod);
    }

    private static long power(long base, int exp, int mod) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result*base)%mod;
            }

            base = (base*base)%mod;
            exp /= 2;
        }

        return result;
    }
}

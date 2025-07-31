import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static long[] factorial;
    static long MOD = 1000000007;
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        calcFactorial(N);
        long result = binomial(N,K);

        System.out.println(result);
    }
    private static long binomial(int n, int k) {
    if (k > n) return 0;
    return (factorial[n] * modInverse(factorial[k]) % MOD) 
           * modInverse(factorial[n-k]) % MOD;
}

    private static void calcFactorial(int n) {
        factorial = new long[n+1];

        factorial[0] = 1;

        for(int i = 1; i<=n;i++) {
            factorial[i] = (factorial[i-1] * i) % MOD;
        }
    }

    // a^(MOD-2) % MOD = a의 모듈러 역원
    private static long power(long base, long exp, long mod) {
        if (exp == 0) return 1;
        
        if (exp % 2 == 0) {
            // 짝수인 경우
            long half = power(base, exp/2, mod);
            return (half * half) % mod;
        } else {
            // 홀수인 경우
            return (base * power(base, exp-1, mod)) % mod;
        }
    }

private static long modInverse(long a) {
    return power(a, MOD - 2, MOD);
}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long N = Long.parseLong(br.readLine());
            long result = solve(N);

            System.out.printf("#%d %d\n", tc, result);
        }
    }

    private static long solve(long N) {
        if(N == 2) return 0;

        long sqrt = (long)Math.sqrt(N);

        if(sqrt*sqrt == N) {
            return 1L + solve(sqrt);
        } else {
            long nextN = (sqrt+1)*(sqrt+1);
            return (nextN - N) + 1 + solve(sqrt+1);
        }
    }
}

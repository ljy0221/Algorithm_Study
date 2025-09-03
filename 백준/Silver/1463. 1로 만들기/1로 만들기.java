import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        if (N == 1) {
            System.out.println(0);
            return;
        }
        
        int[] memo = new int[1000001];
        memo[1] = 0;
        
        for (int i = 2; i <= N; i++) {
            memo[i] = memo[i - 1] + 1;
            
            if (i % 2 == 0) {
                memo[i] = Math.min(memo[i], memo[i / 2] + 1);
            }
            
            if (i % 3 == 0) {
                memo[i] = Math.min(memo[i], memo[i / 3] + 1);
            }
        }
        
        System.out.println(memo[N]);
    }
}
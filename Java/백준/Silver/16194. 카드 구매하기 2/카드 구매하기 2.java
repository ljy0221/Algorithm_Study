import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[] cards = new int[N+1];
        int[] dp = new int[N+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
        	cards[i] = Integer.parseInt(st.nextToken());
        }
        
        dp[0] = 0;
        for(int i = 1; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
            
            // j개 들어있는 카드팩을 구매하는 경우
            for(int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j] + cards[j]);
            }
        }
        
        System.out.println(dp[N]);
    }
}
import java.io.*;
import java.util.*;

public class Solution {
    private static int T, N;
    private static int[] arr, dp;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
    
            st = new StringTokenizer(br.readLine());
            arr = new int[N];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[N];
            
            // 각 위치에서의 LIS 길이 계산
            for (int i = 0; i < N; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (arr[j] < arr[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            
            // 최댓값 찾기
            int maxLength = 0;
            for (int i = 0; i < N; i++) {
                maxLength = Math.max(maxLength, dp[i]);
            }
            
            System.out.println("#" + tc + " " + maxLength);
        }
    }
}
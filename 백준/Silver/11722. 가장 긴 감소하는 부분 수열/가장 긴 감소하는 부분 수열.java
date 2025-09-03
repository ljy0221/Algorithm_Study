import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] memo = new int[N];
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(memo, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] < arr[j]) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                }
            }
        }

        int ans = 1;
        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, memo[i]);
        }
        
        System.out.println(ans);
    }
}
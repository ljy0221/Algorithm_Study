import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int t, n;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            arr = new long[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }

            Arrays.sort(arr);

            int left = 0;
            int right = n-1;

            ArrayList<Long> ans = new ArrayList<>();

            while (left <= right) {
                if(left == right) {
                    ans.add(arr[left]);
                } else {
                    ans.add(arr[left]);
                    ans.add(arr[right]);
                }

                left++;
                right--;
            }

            Collections.reverse(ans);

            for (Long val : ans) {
                sb.append(val).append(" ");
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}

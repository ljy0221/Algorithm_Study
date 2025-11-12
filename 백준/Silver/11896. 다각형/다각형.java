import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long L = Math.max(a, 4);
        long R = b;

        if (L > R) {
            System.out.println(0);
            return;
        }

        long S_start = L;
        if (S_start % 2 != 0) {
            S_start++;
        }

        long S_end = R;
        if (S_end % 2 != 0) {
            S_end--;
        }

        if (S_start > S_end) {
            System.out.println(0);
            return;
        }

        long k = (S_end - S_start) / 2 + 1;

        long sum = k * (S_start + S_end) / 2;

        System.out.println(sum);
    }
}
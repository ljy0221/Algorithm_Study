import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        long[] speed = new long[N];
        long totalSpeed = 0;

        speed[N-1] = 1;
        totalSpeed += speed[N-1];

        for (int i = N-2; i >= 0; i--) {
            long candidate1 = arr[i];
            long candidate2 = speed[i+1]+1;

            speed[i] = Math.min(candidate1, candidate2);
            totalSpeed += speed[i];
        }

        System.out.println(totalSpeed);
    }
}

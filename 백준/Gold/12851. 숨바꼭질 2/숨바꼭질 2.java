import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int MAX = 100000;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(N == K) {
            System.out.println(0);
            System.out.println(1);
            return;
        }

        int[] time = new int[MAX+1];
        int[] count = new int[MAX+1];

        Arrays.fill(time, -1);
        Queue<Integer> q = new ArrayDeque<>();

        time[N] = 0;
        count[N] = 1;
        q.offer(N);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int curT = time[cur];
            int curCnt = count[cur];

            int[] next = {cur-1, cur+1, cur*2};

            for (int n : next) {
                if(n < 0 || n > MAX) {
                    continue;
                }

                if(time[n] == -1) {
                    time[n] = curT + 1;
                    count[n] = curCnt;
                    q.offer(n);
                } else if (time[n] == curT + 1) {
                    count[n] += curCnt;
                }
            }
        }

        System.out.println(time[K]);
        System.out.println(count[K]);
    }
}

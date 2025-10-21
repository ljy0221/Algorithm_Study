import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            
            Queue<int[]> q = new ArrayDeque<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int priority = Integer.parseInt(st.nextToken());
                q.offer(new int[] {priority, j});    
                maxHeap.offer(priority);
            }

            int cnt = 0;

            while (!q.isEmpty()) {
                int[] cur = q.poll();

                if (cur[0] >= maxHeap.peek()) {
                    maxHeap.poll();
                    cnt++;

                    if (cur[1] == M) {
                        sb.append(cnt).append('\n');
                        break;
                    }
                } else {
                    q.offer(cur);
                }
            }
        }
        System.out.print(sb);
    }
}

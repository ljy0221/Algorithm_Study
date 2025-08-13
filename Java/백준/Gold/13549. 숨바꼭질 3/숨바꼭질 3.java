import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 100000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        System.out.println(dijkstra(N, K));
    }
    
    private static int dijkstra(int start, int target) {
        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        dist[start] = 0;
        pq.offer(new int[]{start, 0});
        
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int pos = current[0];
            int time = current[1];

            if (pos == target) return time;

            if (time > dist[pos]) continue;
            
            // 1. X-1 (1초)
            if (pos - 1 >= 0 && time + 1 < dist[pos - 1]) {
                dist[pos - 1] = time + 1;
                pq.offer(new int[]{pos - 1, time + 1});
            }
            
            // 2. X+1 (1초)  
            if (pos + 1 <= MAX && time + 1 < dist[pos + 1]) {
                dist[pos + 1] = time + 1;
                pq.offer(new int[]{pos + 1, time + 1});
            }
            
            // 3. 2*X (0초) - 순간이동
            if (pos * 2 <= MAX && time < dist[pos * 2]) {
                dist[pos * 2] = time;
                pq.offer(new int[]{pos * 2, time});
            }
        }
        
        return dist[target];
    }
}
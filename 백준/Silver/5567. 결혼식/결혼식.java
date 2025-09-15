import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(1);
        distance[1] = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int next : graph.get(current)) {
                if (distance[next] == -1) {
                    distance[next] = distance[current] + 1;
                    queue.offer(next);
                }
            }
        }
        
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (distance[i] == 1 || distance[i] == 2) {
                count++;
            }
        }
        
        System.out.println(count);
    }
}
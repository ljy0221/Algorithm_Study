import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int building;
        int time;
        
        Node(int building, int time) {
            this.building = building;
            this.time = time;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            int[] buildTime = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }
            
            List<Integer>[] graph = new ArrayList[N + 1];
            int[] indegree = new int[N + 1];
            
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph[X].add(Y);
                indegree[Y]++;
            }
            
            int W = Integer.parseInt(br.readLine());
            
            Queue<Node> queue = new LinkedList<>();
            int[] minTime = new int[N + 1];
            
            for (int i = 1; i <= N; i++) {
                if (indegree[i] == 0) {
                    minTime[i] = buildTime[i];
                    queue.offer(new Node(i, buildTime[i]));
                }
            }
            
            while (!queue.isEmpty()) {
                Node current = queue.poll();
                
                for (int next : graph[current.building]) {
                    minTime[next] = Math.max(minTime[next], current.time + buildTime[next]);
                    
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        queue.offer(new Node(next, minTime[next]));
                    }
                }
            }
            
            System.out.println(minTime[W]);
        }
    }
}
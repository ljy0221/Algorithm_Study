import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static List<Integer>[] network;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        network = new List[N+1];
        for (int i = 1; i <= N; i++) {
            network[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // A가 B를 신뢰 -> B를 해킹하면 A도 해킹됨
            network[b].add(a);
        }

        int maxCount = 0;
        List<Integer> result = new ArrayList<>();

        // 각 컴퓨터에서 해킹할 수 있는 총 컴퓨터 개수 계산
        for (int i = 1; i <= N; i++) {
            int count = bfs(i);
            
            if (count > maxCount) {
                maxCount = count;
                result.clear();
                result.add(i);
            } else if (count == maxCount) {
                result.add(i);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(result.get(i));
        }
        System.out.println(sb.toString());
    }
    
    private static int bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        
        queue.offer(start);
        visited[start] = true;
        int count = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            for (int next : network[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
        
        return count;
    }
}
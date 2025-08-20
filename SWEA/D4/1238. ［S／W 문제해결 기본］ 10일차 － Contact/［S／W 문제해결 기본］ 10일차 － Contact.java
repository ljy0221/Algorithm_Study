import java.io.*;
import java.util.*;

public class Solution {
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            List<Set<Integer>> graph = new ArrayList<>();

            int len = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 101; j++) {
                graph.add(new HashSet<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < len/2; j++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
            }

            int lastMax = start;

            Queue<Integer> q = new ArrayDeque<>();
            boolean[] visited = new boolean[101];

            q.offer(start);
            visited[start] = true;

            while (!q.isEmpty()) {
                int size = q.size();
                int currentMax = 0;

                for (int i = 0; i < size; i++) {
                    int current = q.poll();
                    currentMax = Math.max(currentMax, current);

                    for (int next : graph.get(current)) {
                        if(!visited[next]) {
                            visited[next] = true;
                            q.offer(next);
                        }
                    }
                }

                if(currentMax > 0) {
                    lastMax = currentMax;
                }
            }

            System.out.printf("#%d %d\n", tc, lastMax);
        }
    }
}

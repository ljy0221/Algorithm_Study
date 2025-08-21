import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            
            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                graph.add(new ArrayList<>());
            }

            int[] inDegree = new int[V + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(to);
                inDegree[to]++;
            }

            // for (int i = 1; i <= V; i++) {
            //     System.out.printf("%d: ", i);
            //     for (int j = 0; j < graph.get(i).size(); j++) {
            //         System.out.printf("%d ", graph.get(i).get(j));
            //     }
            //     System.out.println();
            // }

            // for (int i = 1; i <= V; i++) {
            //     System.out.printf("%d %d\n", i, inDegree[i]);
            // }
            Queue<Integer> queue = new ArrayDeque<>();
            List<Integer> result = new ArrayList<>();

            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();
                result.add(current);

                for (int next : graph.get(current)) {
                    inDegree[next]--;
                    
                    if (inDegree[next] == 0) {
                        queue.offer(next);
                    }

                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.size(); i++) {
                sb.append(result.get(i));
                if (i < result.size() - 1) {
                    sb.append(" ");
                }
            }

            System.out.printf("#%d %s\n", tc, sb.toString());
        }
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] inDegree = new int[26];
        int[] processTime = new int[26];
        int[] earliestTime = new int[26]; // Earliest completion time for each task
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] exists = new boolean[26]; // Track which tasks exist

        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        String line;
        while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
            st = new StringTokenizer(line);
            if (!st.hasMoreTokens()) break;
            
            int task = st.nextToken().charAt(0) - 'A';
            int time = Integer.parseInt(st.nextToken());
            
            exists[task] = true;
            processTime[task] = time;
            
            if (st.hasMoreTokens()) {
                String prerequisites = st.nextToken();
                inDegree[task] = prerequisites.length();
                
                for (int j = 0; j < prerequisites.length(); j++) {
                    int prerequisite = prerequisites.charAt(j) - 'A';
                    graph.get(prerequisite).add(task);
                }
            }
        }

        // Topological sorting with critical path calculation
        Queue<Integer> queue = new ArrayDeque<>();
        
        // Find all tasks with no prerequisites
        for (int i = 0; i < 26; i++) {
            if (exists[i] && inDegree[i] == 0) {
                queue.offer(i);
                earliestTime[i] = processTime[i];
            }
        }

        int maxTime = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            maxTime = Math.max(maxTime, earliestTime[current]);
            
            for (int dependent : graph.get(current)) {
                inDegree[dependent]--;
                // Update the earliest completion time for the dependent task
                earliestTime[dependent] = Math.max(earliestTime[dependent], 
                                                 earliestTime[current] + processTime[dependent]);
                
                if (inDegree[dependent] == 0) {
                    queue.offer(dependent);
                }
            }
        }

        System.out.println(maxTime);
    }
}
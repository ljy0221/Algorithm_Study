import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {
    
    public boolean solution(int n, int[][] path, int[][] order) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] p : path) {
            graph.get(p[0]).add(p[1]);
            graph.get(p[1]).add(p[0]);
        }
        
        int[] indegree = new int[n];
        List<List<Integer>> orderGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            orderGraph.add(new ArrayList<>());
        }
        
        for (int[] o : order) {
            orderGraph.get(o[0]).add(o[1]);
            indegree[o[1]]++;
        }
        
        boolean[] visited = new boolean[n];
        boolean[] reachable = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        
        reachable[0] = true;
        if (indegree[0] == 0) {
            queue.offer(0);
        }
        
        int visitCount = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;
            visitCount++;
            
            for (int next : graph.get(current)) {
                if (!reachable[next]) {
                    reachable[next] = true;
                    if (indegree[next] == 0 && !visited[next]) {
                        queue.offer(next);
                    }
                }
            }
            
            for (int next : orderGraph.get(current)) {
                indegree[next]--;
                if (indegree[next] == 0 && reachable[next] && !visited[next]) {
                    queue.offer(next);
                }
            }
        }
        
        return visitCount == n;
    }
}

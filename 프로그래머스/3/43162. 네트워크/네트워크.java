import java.util.*;

class Solution {
    boolean[] visited;
    int[][] computers;
    int n;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        this.visited = new boolean[n];
        this.computers = computers;
        this.n = n;
        
        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                answer++;
                bfs(i);
            }
        }
        
        return answer;
    }
    
    private void bfs(int idx) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(idx);
        visited[idx] = true;
        
        while(!q.isEmpty()) {
            int curr = q.poll();
            
            for (int i = 0; i < n; i++) {
                if(i == idx) continue;
                if(visited[i]) continue;
                
                if(computers[curr][i] == 1) {
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
        
    }
}
import java.util.*;

class Solution {
    private final int[] dx = {1, -1, 0, 0};
    private final int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] maps) {
        bfs(maps);
        
        int n = maps.length;
        int m = maps[0].length;
        
        int finalDistance = maps[n - 1][m - 1];

        if (finalDistance > 1) {
            return finalDistance;
        } else {
            return -1;
        }
    }
    
    private void bfs(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<int[]> q = new ArrayDeque<>();
        
        q.offer(new int[] {0, 0});
        maps[0][0] = 1;

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int cx = current[0];
            int cy = current[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {

                    if (maps[nx][ny] == 1) {
                        maps[nx][ny] = maps[cx][cy] + 1;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }
        }
    }
}
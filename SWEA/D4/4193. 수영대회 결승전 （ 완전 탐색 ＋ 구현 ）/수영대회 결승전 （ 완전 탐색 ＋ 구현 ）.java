import java.util.*;

public class Solution {
    static int[] dx = {-1, 1, 0, 0, 0}; // 상하좌우 + 제자리 머무르기
    static int[] dy = {0, 0, -1, 1, 0};
    
    static class State {
        int x, y, time;
        
        State(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    // 소용돌이 활성화 여부 확인
    // 0초, 1초에 활성 -> 2초에 비활성 -> 3초, 4초에 활성 -> 5초에 비활성...
    static boolean isVortexActive(int time) {
        return time % 3 != 0; // 시간을 3으로 나눈 나머지가 2가 아니면 활성
    }
    
    public static int bfs(int[][] grid, int startX, int startY, int endX, int endY, int n) {
        // [x][y][time] 형태의 3차원 방문 배열
        boolean[][][] visited = new boolean[n][n][300];
        Queue<State> queue = new LinkedList<>();
        
        queue.offer(new State(startX, startY, 0));
        visited[startX][startY][0] = true;
        
        while (!queue.isEmpty()) {
            State current = queue.poll();
            
            // 도착점에 도달했으면 시간 반환
            if (current.x == endX && current.y == endY) {
                return current.time;
            }
            
            // 시간이 너무 많이 흘렀으면 중단 (무한루프 방지)
            if (current.time >= 299) continue;
            
            // 5가지 행동: 상하좌우 이동 + 제자리 머무르기
            for (int i = 0; i < 5; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                int ntime = current.time + 1;
                
                // 경계 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                
                // 이미 방문했으면 skip
                if (visited[nx][ny][ntime]) continue;
                
                // 영구 장애물(섬)이면 이동 불가
                if (grid[nx][ny] == 1) continue;
                
                boolean canMove = true;
                
                if (i == 4) { // 제자리에 머무르기
                    // 현재 위치가 소용돌이여도 이미 있으므로 머물 수 있음
                    canMove = true;
                } else { // 다른 위치로 이동
                    if (grid[nx][ny] == 2) { // 목적지가 소용돌이
                        // 해당 시간에 소용돌이가 활성화되어 있으면 이동 불가
                        if (isVortexActive(ntime)) {
                            canMove = false;
                        }
                    }
                }
                
                if (canMove) {
                    visited[nx][ny][ntime] = true;
                    queue.offer(new State(nx, ny, ntime));
                }
            }
        }
        
        return -1; // 도착할 수 없음
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[][] grid = new int[N][N];
            
            // 격자 정보 입력
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            
            // 시작점과 도착점 입력
            int startX = sc.nextInt();
            int startY = sc.nextInt();
            int endX = sc.nextInt();
            int endY = sc.nextInt();
            
            int result = bfs(grid, startX, startY, endX, endY, N);
            System.out.println("#" + tc + " " + result);
        }
        
        sc.close();
    }
}
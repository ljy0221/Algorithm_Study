import java.io.*;
import java.util.*;

public class Solution {
    private static class Cell {
        int life, inTime, activeTime;
        boolean alive, active;

        public Cell(int life, int inTime) {
            this.life = life;
            this.inTime = inTime;
            this.alive = true;
            this.active = false;
            this.activeTime = 0;
        }

        @Override
        public String toString() {
            return life+" ";
        }

        
    }

    static int N, M, K;
    static Cell[][] board;
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static int OFFSET;
    static int SIZE;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            // 배열 크기 설정: 초기 영역 + K시간 동안 확장 가능한 최대 범위
            OFFSET = K;
            SIZE = Math.max(N, M) + 2 * OFFSET;
            board = new Cell[SIZE][SIZE];
            q.clear();
            
            // 초기 세포 배치 (offset 적용)
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int lifeValue = Integer.parseInt(st.nextToken());
                    if (lifeValue > 0) {
                        board[i + OFFSET][j + OFFSET] = new Cell(lifeValue, 0);
                    }
                }
            }

            for (int time = 0; time <= K; time++) {
                findActivatedCell(time);
                culture(time);
            }

            // 살아있는 세포 계산
            int aliveCount = 0;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] != null && board[i][j].alive) {
                        aliveCount++;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(aliveCount).append('\n');
        }

        System.out.print(sb);
    }
    
    private static void culture(int time) {
        // 번식할 세포 저장
        List<int[]> newCells = new ArrayList<>(); // {r, c, life}
        
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int r = pos[0];
            int c = pos[1];
            Cell currentCell = board[r][c];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                // 이미 세포가 있는 위치
                if (board[nr][nc] != null) continue;
                
                // 위치 저장
                newCells.add(new int[]{nr, nc, currentCell.life});
            }
        }
        
        // 동시 번식
        Map<String, int[]> conflictMap = new HashMap<>();
        for (int[] cell : newCells) {
            String key = cell[0] + "," + cell[1];
            if (!conflictMap.containsKey(key) || conflictMap.get(key)[2] < cell[2]) {
                conflictMap.put(key, cell);
            }
        }
        
        // 최종 번식
        for (int[] cell : conflictMap.values()) {
            board[cell[0]][cell[1]] = new Cell(cell[2], time);
        }
    }

    private static void findActivatedCell(int time) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == null) continue;
                
                Cell c = board[i][j];
                
                // 세포 활성화
                if (time - c.inTime == c.life+1 && !c.active) {
                    c.active = true;
                    c.activeTime = time;
                    q.offer(new int[]{i, j});
                }

                // 세포 죽음 (활성화된 후 life만큼 시간이 지남)
                if (c.active && time - c.activeTime == c.life-1) {
                    c.active = false;
                    c.alive = false;
                }
            }
        }
    }
}
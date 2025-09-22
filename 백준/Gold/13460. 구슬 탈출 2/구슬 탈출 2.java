import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class State {
        int redR, redC, blueR, blueC, count;
        
        public State(int redR, int redC, int blueR, int blueC, int count) {
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.count = count;
        }
    }

    static int N, M;
    static char[][] map;
    static int holeR, holeC;
    static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}}; // 상하좌우
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        
        int redR = 0, redC = 0, blueR = 0, blueC = 0;
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'B') {
                    blueR = i;
                    blueC = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'R') {
                    redR = i;
                    redC = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'O') {
                    holeR = i;
                    holeC = j;
                }
            }
        }

        System.out.println(bfs(redR, redC, blueR, blueC));
    }
    
    private static int bfs(int redR, int redC, int blueR, int blueC) {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(redR, redC, blueR, blueC, 0));
        visited[redR][redC][blueR][blueC] = true;
        
        while (!queue.isEmpty()) {
            State current = queue.poll();
            
            if (current.count >= 10) {
                continue;
            }
            
            for (int dir = 0; dir < 4; dir++) {
                // 빨간 구슬 이동
                int[] redPos = move(current.redR, current.redC, dir);
                // 파란 구슬 이동
                int[] bluePos = move(current.blueR, current.blueC, dir);
                
                // 파란 구슬이 구멍에 빠지면 실패
                if (bluePos[0] == holeR && bluePos[1] == holeC) {
                    continue;
                }
                
                // 빨간 구슬이 구멍에 빠지면 성공
                if (redPos[0] == holeR && redPos[1] == holeC) {
                    return current.count + 1;
                }
                
                // 두 구슬이 같은 위치에 있으면 조정
                if (redPos[0] == bluePos[0] && redPos[1] == bluePos[1]) {
                    adjustPosition(redPos, bluePos, current, dir);
                }
                
                if (!visited[redPos[0]][redPos[1]][bluePos[0]][bluePos[1]]) {
                    visited[redPos[0]][redPos[1]][bluePos[0]][bluePos[1]] = true;
                    queue.offer(new State(redPos[0], redPos[1], bluePos[0], bluePos[1], current.count + 1));
                }
            }
        }
        
        return -1;
    }
    
    private static int[] move(int r, int c, int dir) {
        int nr = r;
        int nc = c;
        
        while (true) {
            int nextR = nr + dirs[dir][0];
            int nextC = nc + dirs[dir][1];
            
            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || map[nextR][nextC] == '#') {
                break;
            }
            
            nr = nextR;
            nc = nextC;
            
            if (nr == holeR && nc == holeC) {
                break;
            }
        }
        
        return new int[]{nr, nc};
    }
    
    private static void adjustPosition(int[] redPos, int[] bluePos, State current, int dir) {
        // 이동 거리를 계산하여 더 많이 이동한 구슬을 한 칸 뒤로
        int redDist = Math.abs(redPos[0] - current.redR) + Math.abs(redPos[1] - current.redC);
        int blueDist = Math.abs(bluePos[0] - current.blueR) + Math.abs(bluePos[1] - current.blueC);
        
        if (redDist > blueDist) {
            redPos[0] -= dirs[dir][0];
            redPos[1] -= dirs[dir][1];
        } else {
            bluePos[0] -= dirs[dir][0];
            bluePos[1] -= dirs[dir][1];
        }
    }
}
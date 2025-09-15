import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static class Shark {
        int dir, size, speed;

        public Shark(int speed, int dir, int size) {
            this.dir = dir;
            this.size = size;
            this.speed = speed;
        }

        @Override
        public String toString() {
            return size+" ";
        }

        
    }

    static int[] dr = {-1, 1, 0, 0}; // 1:위, 2:아래, 3:오른쪽, 4:왼쪽
    static int[] dc = {0, 0, 1, -1};
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Shark[][] fishing = new Shark[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken())-1;
            int z = Integer.parseInt(st.nextToken());

            fishing[r][c] = new Shark(s, d, z);
        }

        int catchedSharkSize = 0;

        // 낚시왕이 C열까지 이동
        for (int fisherMan = 0; fisherMan < C; fisherMan++) {
            // 1. 가장 가까운 상어 잡기
            for (int i = 0; i < R; i++) {
                if (fishing[i][fisherMan] != null) {
                    catchedSharkSize += fishing[i][fisherMan].size;
                    // System.out.println("Catch!: " + fishing[i][fisherMan] + "current Size: " + catchedSharkSize);
                    fishing[i][fisherMan] = null;
                    break;
                }
            }
            
            // 2. 상어들 이동
            fishing = moveShark(fishing);
            // for (int i = 0; i < R; i++) {
            //     System.out.println(Arrays.toString(fishing[i]));
            // }
        }

        System.out.println(catchedSharkSize);
    }

    private static Shark[][] moveShark(Shark[][] fishing) {
        Shark[][] newFishing = new Shark[R][C];
        
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (fishing[i][j] != null) {
                    Shark shark = fishing[i][j];
                    
                    // 상어의 새로운 위치 계산
                    int r = i;
                    int c = j;
                    int dir = shark.dir;
                    int speed = shark.speed;
                    
                    // 방향 업데이트
                    for(int move = 0; move < speed; move++) {
                        int nextR = r + dr[dir];
                        int nextC = c + dc[dir];
                        
                        // 경계 체크 및 방향 반전
                        if(nextR < 0 || nextR >= R) {
                            dir = (dir == 0) ? 1 : 0; // 상하 방향 반전
                            nextR = r + dr[dir];
                        }
                        if(nextC < 0 || nextC >= C) {
                            dir = (dir == 2) ? 3 : 2; // 좌우 방향 반전
                            nextC = c + dc[dir];
                        }
                        
                        r = nextR;
                        c = nextC;
                    }
                    
                    shark.dir = dir;
                    // 새로운 위치에 상어 배치 (큰 상어가 작은 상어를 먹음)
                    if (newFishing[r][c] == null || 
                        newFishing[r][c].size < shark.size) {
                        newFishing[r][c] = shark;
                    }
                }
            }
        }
        
        return newFishing;
    }
    
}
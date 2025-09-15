import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class FireBall {
        int row, col, mass, speed, dir;
        
        public FireBall(int row, int col, int mass, int speed, int dir) {
            this.row = row;
            this.col = col;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
        }
    }
    
    static int N, M, K;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        List<FireBall>[][] map = new List[N][N];
        Queue<FireBall> ballQ = new LinkedList<>();
        
        // 방향 벡터
        int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dir1 = {0, 2, 4, 6};  // 모두 홀수거나 모두 짝수일 때
        int[] dir2 = {1, 3, 5, 7};  // 그렇지 않을 때
        
        // 초기 파이어볼 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            ballQ.add(new FireBall(row, col, mass, speed, dir));
        }
        
        // 격자 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        
        // K번 이동
        while (K-- > 0) {
            int currBalls = ballQ.size();
            
            // 1단계: 모든 파이어볼 이동
            for (int i = 0; i < currBalls; i++) {
                FireBall curr = ballQ.poll();
                
                // 속력 최적화: N을 넘어가는 이동은 의미 없음
                int moveDistance = curr.speed % N;
                curr.row += dr[curr.dir] * moveDistance;
                curr.col += dc[curr.dir] * moveDistance;
                
                // 음수 좌표 보정
                curr.row = (curr.row % N + N) % N;
                curr.col = (curr.col % N + N) % N;
                
                map[curr.row][curr.col].add(curr);
            }
            
            // 2단계: 파이어볼 합치기 및 분할
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k].size() >= 2) {
                        List<FireBall> samePosFireBalls = map[j][k];
                        int totalMass = 0;
                        int totalSpeed = 0;
                        int count = samePosFireBalls.size();
                        boolean allOdd = true;
                        boolean allEven = true;
                        
                        for (FireBall fb : samePosFireBalls) {
                            totalMass += fb.mass;
                            totalSpeed += fb.speed;
                            if (fb.dir % 2 == 0) {
                                allOdd = false;
                            } else {
                                allEven = false;
                            }
                        }
                        
                        int newMass = totalMass / 5;
                        int newSpeed = totalSpeed / count;
                        
                        // 질량이 0보다 큰 경우에만 4개의 파이어볼 생성
                        if (newMass > 0) {
                            if (allOdd || allEven) {
                                for (int idx = 0; idx < 4; idx++) {
                                    ballQ.add(new FireBall(j, k, newMass, newSpeed, dir1[idx]));
                                }
                            } else {
                                for (int idx = 0; idx < 4; idx++) {
                                    ballQ.add(new FireBall(j, k, newMass, newSpeed, dir2[idx]));
                                }
                            }
                        }
                    } else if (map[j][k].size() == 1) {
                        ballQ.add(map[j][k].get(0));
                    }
                    map[j][k].clear();
                }
            }
        }
        
        // 결과 계산
        int totalMass = 0;
        while (!ballQ.isEmpty()) {
            totalMass += ballQ.poll().mass;
        }
        
        System.out.println(totalMass);
    }
}
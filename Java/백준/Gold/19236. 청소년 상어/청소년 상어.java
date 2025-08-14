import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    // 1~8 방향을 위해 0번 인덱스를 비워둠
    static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    private static class Fish implements Comparable<Fish>{
        int idx, dir;
        int[] pos;
        
        public Fish(int[] pos, int idx, int dir) {
            this.idx = idx;
            this.dir = dir;
            this.pos = pos;
        }

        private void rotateDir() {
            this.dir = (this.dir % 8) + 1; // 1~8 순환
        }

        @Override
        public int compareTo(Fish o) {
            return Integer.compare(this.idx, o.idx);
        }
    }

    private static class Shark{
        int dir, score;
        int[] pos;
        
        public Shark(int[] pos, int score, int dir) {
            this.pos = pos;
            this.score = score;
            this.dir = dir;
        }
    }

    static int maxScore = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = 4;
        Fish[][] ocean = new Fish[N][N];
        Set<Fish> fishes = new TreeSet<>();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int fishNum = Integer.parseInt(st.nextToken());
                int fishDir = Integer.parseInt(st.nextToken());
                Fish newFish = new Fish(new int[]{i, j}, fishNum, fishDir);
                ocean[i][j] = newFish;
                fishes.add(newFish);
            }
        }

        // 0. 상어 등장 - (0,0) 물고기를 먹음
        Fish firstFish = ocean[0][0];
        Shark aShark = new Shark(new int[]{0, 0}, firstFish.idx, firstFish.dir);
        fishes.remove(firstFish);
        ocean[0][0] = null;

        recursive(fishes, ocean, aShark);
        System.out.println(maxScore);
    }

    private static void recursive(Set<Fish> fishes, Fish[][] ocean, Shark aShark) {
        // 1. 물고기가 움직인다
        moveFishes(fishes, ocean, aShark);
        
        // 2. 상어가 먹는다(물고기의 방향을 갖는다)
        boolean canMove = false;
        
        for (int i = 1; i <= 3; i++) { // 1칸부터 3칸까지
            int movePosR = aShark.pos[0] + dr[aShark.dir] * i;
            int movePosC = aShark.pos[1] + dc[aShark.dir] * i;

            if (movePosR < 0 || movePosR >= 4 || movePosC < 0 || movePosC >= 4) {
                break;
            }
            
            // 물고기가 있으면 먹음
            if (ocean[movePosR][movePosC] != null) {
                canMove = true;
                
                // 상태 저장
                Fish eatenFish = ocean[movePosR][movePosC];
                Set<Fish> fishesBackup = new TreeSet<>();
                Fish[][] oceanBackup = new Fish[4][4];
                
                for (Fish f : fishes) {
                    Fish copyFish = new Fish(new int[]{f.pos[0], f.pos[1]}, f.idx, f.dir);
                    fishesBackup.add(copyFish);
                }
                
                // 바다 상태 복사
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) {
                        if (ocean[r][c] != null) {
                            oceanBackup[r][c] = new Fish(new int[]{ocean[r][c].pos[0], ocean[r][c].pos[1]}, ocean[r][c].idx, ocean[r][c].dir);
                        } else {
                            oceanBackup[r][c] = null;
                        }
                    }
                }
                
                int[] originalPos = new int[] {aShark.pos[0], aShark.pos[1]};
                int originalDir = aShark.dir;
                int originalScore = aShark.score;

                // 상어 이동
                aShark.pos[0] = movePosR;
                aShark.pos[1] = movePosC;
                aShark.dir = eatenFish.dir;
                aShark.score += eatenFish.idx;
                ocean[movePosR][movePosC] = null;
                fishes.remove(eatenFish);
                
                // 재귀 호출
                recursive(fishes, ocean, aShark);

                // 상태 복원
                aShark.pos[0] = originalPos[0];
                aShark.pos[1] = originalPos[1];
                aShark.dir = originalDir;
                aShark.score = originalScore;
                
                // 물고기 복원
                fishes.clear();
                for (Fish f : fishesBackup) {
                    Fish newFish = new Fish(new int[]{f.pos[0], f.pos[1]}, f.idx, f.dir);
                    fishes.add(newFish);
                }
                
                // 바다 복원
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) {
                        if (oceanBackup[r][c] != null) {
                            Fish f = oceanBackup[r][c];
                            ocean[r][c] = new Fish(new int[]{f.pos[0], f.pos[1]}, f.idx, f.dir);
                            for (Fish fish : fishes) {
                                if (fish.idx == f.idx) {
                                    ocean[r][c] = fish;
                                    break;
                                }
                            }
                        } else {
                            ocean[r][c] = null;
                        }
                    }
                }
            }
        }
        
        // 더 이상 이동할 수 없으면
        if (!canMove) {
            maxScore = Math.max(maxScore, aShark.score);
        }
    }

    public static void moveFishes(Set<Fish> fishes, Fish[][] ocean, Shark shark) {
        for (Fish fish : fishes) {
            int originalDir = fish.dir;
            boolean moved = false;
            
            for (int attempt = 0; attempt < 8; attempt++) {
                int nextPosR = fish.pos[0] + dr[fish.dir];
                int nextPosC = fish.pos[1] + dc[fish.dir];
                
                if (isValidMove(nextPosR, nextPosC, ocean, shark)) {
                    swapFish(ocean, fish.pos[0], fish.pos[1], nextPosR, nextPosC);
                    moved = true;
                    break;
                }
                
                // 방향 회전
                fish.rotateDir();
            }
            
            if (!moved) {
                fish.dir = originalDir;
            }
        }
    }

    public static void swapFish(Fish[][] ocean, int r1, int c1, int r2, int c2) {
        Fish temp = ocean[r1][c1];
        ocean[r1][c1] = ocean[r2][c2];
        ocean[r2][c2] = temp;

        if (ocean[r1][c1] != null) {
            ocean[r1][c1].pos[0] = r1;
            ocean[r1][c1].pos[1] = c1;
        }
        if (ocean[r2][c2] != null) {
            ocean[r2][c2].pos[0] = r2;
            ocean[r2][c2].pos[1] = c2;
        }
    }

    public static boolean isValidMove(int r, int c, Fish[][] ocean, Shark shark) {
        // 경계
        if (r < 0 || r >= ocean.length || c < 0 || c >= ocean[0].length) {
            return false;
        }
        
        // 상어 위치
        if (shark.pos[0] == r && shark.pos[1] == c) {
            return false;
        }
        
        return true;
    }
}
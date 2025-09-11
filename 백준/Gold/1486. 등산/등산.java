import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T, D;
    static int[][] map;
    static int[][] dist;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); 
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String in = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = in.charAt(j);
                if ('A' <= c && c <= 'Z') {
                    map[i][j] = c - 'A';
                } else {
                    map[i][j] = c - 'a' + 26;
                }
            }
        }

        int totalNodes = N * M;
        dist = new int[totalNodes][totalNodes];
        
        // 1. 거리 배열 초기화
        initializeDistance(totalNodes);
        
        // 2. 인접한 칸들 사이의 거리 설정
        buildGraph();
        
        // 3. 플로이드 워셜 알고리즘
        floydWarshall(totalNodes);
        
        // 4. 결과 계산
        int result = findMaxHeight();
        System.out.println(result);
    }
    
    // 2차원 좌표를 1차원 인덱스로 변환
    static int getIndex(int r, int c) {
        return r * M + c;
    }
    
    // 1차원 인덱스를 2차원 좌표로 변환
    static int[] getCoordinate(int index) {
        return new int[]{index / M, index % M};
    }
    
    // 거리 배열 초기화
    static void initializeDistance(int totalNodes) {
        for (int i = 0; i < totalNodes; i++) {
            for (int j = 0; j < totalNodes; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = INF;
                }
            }
        }
    }
    
    // 그래프 구성 (인접한 칸들 사이의 거리 설정)
    static void buildGraph() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int currentIdx = getIndex(r, c);
                
                // 4방향 확인
                for (int dir = 0; dir < 4; dir++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
                    
                    // 범위 체크
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M) {
                        int nextIdx = getIndex(nr, nc);
                        int heightDiff = map[r][c] - map[nr][nc];
                        
                        // 높이 차이가 T 이하인 경우에만 이동 가능
                        if (Math.abs(heightDiff) <= T) {
                            int weight;
                            if (heightDiff < 0) {
                                // 올라가거나 같은 높이
                                weight = heightDiff * heightDiff;
                            } else {
                                // 내려감 1
                                weight = 1;
                            }
                            dist[currentIdx][nextIdx] = weight;
                        }
                    }
                }
            }
        }
    }
    
    // 플로이드 워셜 알고리즘
    static void floydWarshall(int totalNodes) {
        for (int k = 0; k < totalNodes; k++) {
            for (int i = 0; i < totalNodes; i++) {
                for (int j = 0; j < totalNodes; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }
    
    // 왕복 가능한 지점 중 가장 높은 곳 찾기
    static int findMaxHeight() {
        int startIdx = getIndex(0, 0); // 호텔 위치 (0,0)
        int maxHeight = map[0][0]; // 시작점의 높이
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int targetIdx = getIndex(r, c);
                
                // 호텔 목표지점 호텔로 돌아오는 시간
                int goTime = dist[startIdx][targetIdx];
                int backTime = dist[targetIdx][startIdx];
                
                // 왕복 시간이 D 이하이고, 실제로 갈 수 있는 경우
                if (goTime != INF && backTime != INF && goTime + backTime <= D) {
                    maxHeight = Math.max(maxHeight, map[r][c]);
                }
            }
        }
        
        return maxHeight;
    }
}
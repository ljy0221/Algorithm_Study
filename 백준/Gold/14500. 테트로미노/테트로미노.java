import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,M;
    static int[][] graph;

    static int[][][] tetromino = new int[][][] { //tetromino[종류][회전상태][좌표인덱스]
        // r, c
        // I
        {{0,0},{0,1},{0,2},{0,3}},  // 가로
        {{0,0},{1,0},{2,0},{3,0}},  // 세로
        // O
        {{0,0},{0,1},{1,0},{1,1}},
        // T
        {{0,0}, {0,1}, {0,2}, {1,1}},    // ㅜ
        {{0,0}, {1,0}, {2,0}, {1,1}},    // ㅏ
        {{0,0}, {0,1}, {0,2}, {-1,1}},    // ㅗ
        {{0,0}, {1,0}, {2,0}, {1,-1}},    // ㅓ
        // L
        {{0,0}, {1,0}, {2,0}, {2,1}},
        {{0,0}, {0,1}, {0,2}, {-1,2}},
        {{0,0}, {-1,0}, {-2,0}, {-2,-1}},
        {{0,0}, {0,-1}, {0,-2}, {1,-2}},
        // L symmetric
        {{0,0}, {1,0}, {2,0}, {2,-1}},
        {{0,0}, {0,1}, {0,2}, {1,2}},
        {{0,0}, {-1,0}, {-2,0}, {-2,1}},
        {{0,0}, {0,-1}, {0,-2}, {-1,-2}},
        // S
        {{0,0}, {1,0}, {1,1}, {2,1}},
        {{0,0}, {0,1}, {-1,1}, {-1,2}},
        {{0,0}, {-1,0}, {-1,-1}, {-2,-1}},
        {{0,0}, {0,-1}, {1,-1}, {1,-2}},
        // S symmetric
        {{0,0}, {1,0}, {1,-1}, {2,-1}},
        {{0,0}, {0,1}, {1,1}, {1,2}},
        {{0,0}, {-1,0}, {-1,1}, {-2,1}},
        {{0,0}, {0,-1}, {-1,-1}, {-1,-2}}
    };

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int maxSum = 0;

        // 모든 위치
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 모든 모양
                for (int k = 0; k < tetromino.length; k++) {
                    int sum = 0;
                    boolean canPlace = true;

                    for (int l = 0; l < 4; l++) {
                        int nr = i + tetromino[k][l][0];
                        int nc = j + tetromino[k][l][1];

                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
                            canPlace = false;
                            break;
                        }
                        sum += graph[nr][nc];
                    }

                    if (canPlace) {
                        maxSum = Math.max(maxSum, sum);
                    }
                }
            }
        }

        System.out.println(maxSum);
    }
}

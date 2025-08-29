import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] originalMap;
    static int[][] operations;
    static boolean[] used;
    static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        originalMap = new int[N][M];
        operations = new int[K][3];
        used = new boolean[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originalMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            operations[i][0] = Integer.parseInt(st.nextToken()) - 1;
            operations[i][1] = Integer.parseInt(st.nextToken()) - 1;
            operations[i][2] = Integer.parseInt(st.nextToken());
        }

        permutation(0, new int[K]);
        
        System.out.println(minValue);
    }

    private static void permutation(int depth, int[] order) {
        if (depth == K) {
            int[][] tempMap = copyArray(originalMap);
            
            for (int i = 0; i < K; i++) {
                int opIndex = order[i];
                int r = operations[opIndex][0];
                int c = operations[opIndex][1];
                int s = operations[opIndex][2];
                tempMap = rotate(tempMap, r, c, s);
            }
            
            int currentMin = calculateArrayValue(tempMap);
            minValue = Math.min(minValue, currentMin);
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!used[i]) {
                used[i] = true;
                order[depth] = i;
                permutation(depth + 1, order);
                used[i] = false;
            }
        }
    }

    private static int[][] copyArray(int[][] original) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }

    private static int[][] rotate(int[][] map, int r, int c, int s) {
        int[][] newMap = copyArray(map);
        
        for (int layer = 0; layer < s; layer++) {
            int startR = r - s + layer;
            int endR = r + s - layer;
            int startC = c - s + layer;
            int endC = c + s - layer;
            
            int temp = map[startR][endC];
            for (int j = endC; j > startC; j--) {
                newMap[startR][j] = map[startR][j-1];
            }
            
            for (int i = startR; i < endR; i++) {
                newMap[i+1][endC] = map[i][endC];
            }
            
            for (int j = endC; j > startC; j--) {
                newMap[endR][j-1] = map[endR][j];
            }
            
            for (int i = endR; i > startR; i--) {
                newMap[i-1][startC] = map[i][startC];
            }
            
            newMap[startR+1][endC] = temp;
        }
        
        return newMap;
    }

    private static int calculateArrayValue(int[][] map) {
        int minRowSum = Integer.MAX_VALUE;
        
        for (int i = 0; i < N; i++) {
            int rowSum = 0;
            for (int j = 0; j < M; j++) {
                rowSum += map[i][j];
            }
            minRowSum = Math.min(minRowSum, rowSum);
        }
        
        return minRowSum;
    }
}
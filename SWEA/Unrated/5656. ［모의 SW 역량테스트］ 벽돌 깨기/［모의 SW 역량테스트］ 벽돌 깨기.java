import java.io.*;
import java.util.*;

public class Solution {
    static int N, W, H;
    static int ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;
            int[][] initialState = new int[H][W];

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    initialState[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve(0, initialState);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
    
    private static void solve(int depth, int[][] remained) {
        if(depth == N) {  // N번 떨어뜨린 후 종료
            ans = Math.min(ans, count(remained));
            return;
        }

        // 모든 열에서 완전탐색
        for (int col = 0; col < W; col++) {
            int[][] copiedList = arrayCopy(remained);
            
            shot(col, copiedList);
            
            reBalance(copiedList);
            
            solve(depth + 1, copiedList);
        }
    }
    
    private static int[][] arrayCopy(int[][] original) {
        int[][] copied = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copied[i][j] = original[i][j];
            }
        }
        return copied;
    }
    
    private static int count(int[][] remained) {
        int count = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(remained[i][j] != 0) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private static void shot(int colNum, int[][] board) {
        int dropPos = -1;

        // 첫 번째 벽돌 찾기
        for (int i = 0; i < H; i++) {
            if(board[i][colNum] != 0) {
                dropPos = i;
                break;
            }
        }

        // 벽돌이 없으면 아무것도 하지 않음
        if(dropPos == -1) return;

        int power = board[dropPos][colNum];
        
        // 연쇄 반응 처리
        Queue<int[]> cascadeQueue = new ArrayDeque<>();
        cascadeQueue.offer(new int[] {dropPos, colNum, power});

        while (!cascadeQueue.isEmpty()) {
            int[] curr = cascadeQueue.poll();
            int row = curr[0];
            int col = curr[1];
            int currP = curr[2];

            board[row][col] = 0;

            // 상하좌우로 currP 범위만큼 벽돌 깨기
            // 상
            for (int nr = Math.max(0, row - currP + 1); nr <= row; nr++) {
                if(nr != row || col != col) { // 현재 위치는 이미 처리했으므로 제외
                    int nP = board[nr][col];
                    if(nP > 0) {
                        cascadeQueue.offer(new int[]{nr, col, nP});
                    }
                }
            }
            // 하
            for (int nr = row + 1; nr < Math.min(H, row + currP); nr++) {
                int nP = board[nr][col];
                if(nP > 0) {
                    cascadeQueue.offer(new int[]{nr, col, nP});
                }
            }
            // 좌
            for (int nc = Math.max(0, col - currP + 1); nc < col; nc++) {
                int nP = board[row][nc];
                if(nP > 0) {
                    cascadeQueue.offer(new int[]{row, nc, nP});
                }
            }
            // 우
            for (int nc = col + 1; nc < Math.min(W, col + currP); nc++) {
                int nP = board[row][nc];
                if(nP > 0) {
                    cascadeQueue.offer(new int[]{row, nc, nP});
                }
            }
        }
    }
    
    private static void reBalance(int[][] board) {
        for (int col = 0; col < W; col++) {
            int writePos = H - 1;
            for (int row = H - 1; row >= 0; row--) {
                if (board[row][col] != 0) {
                    if (writePos != row) {
                        board[writePos][col] = board[row][col];
                        board[row][col] = 0;
                    }
                    writePos--;
                }
            }
        }
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int maxVal = 1;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int[][] game = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                game[i][j] = Integer.parseInt(st.nextToken());
                maxVal = Math.max(maxVal, game[i][j]);
            }
        }
            play(0, game);
        

        System.out.println(maxVal);
    }

    private static void play(int count, int[][] board) {
        if (count == 5) {
            maxVal = Math.max(maxVal, findMaxVal(board));
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] newBoard = move(board, dir);
            
            play(count + 1, newBoard);
        }
    }

    private static int[][] move(int[][] board, int dir) {
        int[][] newBoard = new int[N][N];

        for (int i = 0; i < N; i++) {
            
            List<Integer> list = new ArrayList<>();
            switch (dir) {
                case 0: // Up
                    for (int j = 0; j < N; j++) {
                        if (board[j][i] != 0) list.add(board[j][i]);
                    }
                    break;
                case 1: // Left
                    for (int j = 0; j < N; j++) {
                        if (board[i][j] != 0) list.add(board[i][j]);
                    }
                    break;
                case 2: // Down
                    for (int j = N - 1; j >= 0; j--) {
                        if (board[j][i] != 0) list.add(board[j][i]);
                    }
                    break;
                case 3: // Right:
                    for (int j = N - 1; j >= 0; j--) {
                        if (board[i][j] != 0) list.add(board[i][j]);
                    }
                    break;
            }

            for (int idx = 0; idx < list.size() - 1; idx++) {
                if (list.get(idx).equals(list.get(idx + 1))) {
                    list.set(idx, list.get(idx) * 2);
                    list.set(idx + 1, 0);
                    idx++;
                }
            }
            
            // Up, Left: 앞에서부터 채움 (idx = 0 to N-1)
            if (dir == 0 || dir == 1) {
                int newIdx = 0;
                for (int val : list) {
                    if (val != 0) {
                        if (dir == 0) newBoard[newIdx++][i] = val; // Up: newRow++
                        else newBoard[i][newIdx++] = val; // Left: newCol++
                    }
                }
            }
            // Down, Right: 뒤에서부터 채움 (idx = N-1 to 0)
            else if (dir == 2 || dir == 3) {
                int newIdx = N - 1;
                for (int val : list) {
                    if (val != 0) {
                        if (dir == 2) newBoard[newIdx--][i] = val; // Down: newRow--
                        else newBoard[i][newIdx--] = val; // Right: newCol--
                    }
                }
            }
        }
        return newBoard;
    }

    private static int findMaxVal(int[][] board) {
        int val = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > val) {
                    val = board[i][j];
                }
            }
        }

        return val;
    }
}

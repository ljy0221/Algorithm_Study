import java.util.*;
import java.io.*;

public class Solution {
    
    /*
     * 세로방향에 동일한 특성의 셀들이 K개 이상 연속적일때만 통과
     * 약품은 가로방향 투입: 모든 셀이 같은 특성이 됨
     * 최소 투입횟수 구하기
     */

    private static int D, W, K;
    private static int[][] films;
    private static int ans;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            films = new int[D][W];

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    films[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = Integer.MAX_VALUE;

            if (K == 1) {
                System.out.printf("#%d %d\n", tc, 0);
                continue;
            }

            if (isValid()) {
                System.out.printf("#%d %d\n", tc, 0);
                continue;
            }

            solve(0, 0);

            System.out.printf("#%d %d\n", tc, ans);
        }
    }

    private static boolean isValid() {
        for (int w = 0; w < W; w++) {
            if (!isColumnValid(w)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColumnValid(int col) {
        int count = 1;
        int current = films[0][col];
        
        for (int d = 1; d < D; d++) {
            if (films[d][col] == current) {
                count++;
                if (count >= K) {
                    return true;
                }
            } else {
                current = films[d][col];
                count = 1;
            }
        }
        
        return count >= K;
    }

    private static void solve(int depth, int usedCount) {

        if (usedCount >= ans) {
            return;
        }

        if (isValid()) {
            ans = Math.min(ans, usedCount);
            return;
        }

        if (depth == D) {
            return;
        }
        
        int[] originalRow = new int[W];
        for (int j = 0; j < W; j++) {
            originalRow[j] = films[depth][j];
        }
        
        solve(depth + 1, usedCount);
        
        Arrays.fill(films[depth], 0);
        solve(depth + 1, usedCount + 1);
        
        Arrays.fill(films[depth], 1);
        solve(depth + 1, usedCount + 1);
        
        for (int j = 0; j < W; j++) {
            films[depth][j] = originalRow[j];
        }
    }
}
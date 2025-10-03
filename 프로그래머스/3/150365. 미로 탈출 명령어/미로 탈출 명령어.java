import java.util.*;

public class Solution {
    static int gn, gm, gk;
    static List<String> candidateList = new ArrayList<>();
    static char[] dir = { 'd', 'l', 'r', 'u' };
    static int[][] direction = {
            { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 }
    };
    static String result = null;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        gn = n;
        gm = m;
        gk = k;
        result = null;
        int minDist = getDist(x, y, r, c);

        if (minDist > k || (k - minDist) % 2 != 0) {
            return "impossible";
        }

        dfs(x, y, r, c, 0, new StringBuilder());

        return result == null ? "impossible" : result;
    }

    private static boolean dfs(int cr, int cc, int dr, int dc, int move, StringBuilder path) {
        if (result != null)
            return true;

        int remainMove = gk - move;
        int remainDist = getDist(cr, cc, dr, dc);
        if (remainDist > remainMove || (remainMove - remainDist) % 2 != 0) {
            return false;
        }

        if (move == gk) {
            if (cr == dr && cc == dc) {
                result = path.toString();
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int nr = cr + direction[i][0];
            int nc = cc + direction[i][1];

            if (isValid(nr, nc)) {
                path.append(dir[i]);
                if (dfs(nr, nc, dr, dc, move + 1, path)) {
                    return true;
                }
                path.deleteCharAt(path.length() - 1);
            }
        }

        return false;
    }

    private static boolean isValid(int nr, int nc) {
        return nr >= 1 && nr <= gn && nc >= 1 && nc <= gm;
    }

    private static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}

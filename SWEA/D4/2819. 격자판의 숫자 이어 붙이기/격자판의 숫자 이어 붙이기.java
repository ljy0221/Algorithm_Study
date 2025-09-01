import java.io.*;
import java.util.*;

public class Solution {
    static Set<String> numbers;
    static char[][] graph;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        graph = new char[4][4];
        for (int tc = 1; tc <= T; tc++) {
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    graph[i][j] = st.nextToken().charAt(0);
                }
            }

            numbers = new HashSet<>();

            for (int r = 0; r < 4; r++) {
                for (int c = 0; c < 4; c++) {
                    makeNum(""+graph[r][c], r, c);
                }
            }
            
            System.out.printf("#%d %d\n", tc, numbers.size());
        }
    }


    private static void makeNum(String number, int r, int c) {
        if(number.length() == 7) {
            numbers.add(number);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = r+dr[i];
            int nc = c+dc[i];

            if(isValid(nr, nc)) continue;

            String nextNum = number+graph[nr][nc];
            makeNum(nextNum, nr, nc);
        }
    }


    private static boolean isValid(int nr, int nc) {
        return nr < 0 || nr >= 4 || nc < 0 || nc >= 4;
    }
}
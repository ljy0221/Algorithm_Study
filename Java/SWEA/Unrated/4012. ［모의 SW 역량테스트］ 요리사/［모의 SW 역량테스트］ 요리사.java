import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[][] ingredients = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    ingredients[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int result = solve(ingredients, N);

            System.out.printf("#%d %d\n", tc, result);
        }
    }

    private static int solve(int[][] ingredients, int n) {
        int gap = Integer.MAX_VALUE;
        // nCn/2 조합찾기
        for (int mask = 0; mask < (1 << n); mask++) {
            boolean[] used = new boolean[n];
            if (Integer.bitCount(mask) == n/2) {
                List<Integer> sideA = new ArrayList<>();
                List<Integer> sideB = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    if ((mask & (1 << i)) != 0) {
                        used[i] = true;
                    }
                }
                for (int i = 0; i < n; i++) {
                    if(used[i]) {
                        sideA.add(i);
                    } else {
                        sideB.add(i);
                    }
                }
                gap = Math.min(gap, calculatePowerGap(ingredients, sideA, sideB));
            }
        }
        
        return gap;
    }

    private static int calculatePowerGap(int[][] ingredients, List<Integer> sideA, List<Integer> sideB) {
        int powerA = 0;
        int powerB = 0;

        for (int i = 0; i < sideA.size(); i++) {
            for (int j = 0; j < sideA.size(); j++) {
                powerA += ingredients[sideA.get(i)][sideA.get(j)];
            }
        }
        for (int i = 0; i < sideB.size(); i++) {
            for (int j = 0; j < sideB.size(); j++) {
                powerB += ingredients[sideB.get(i)][sideB.get(j)];
            }
        }


        int gap = Math.abs(powerA - powerB);
        return gap;
    }
}
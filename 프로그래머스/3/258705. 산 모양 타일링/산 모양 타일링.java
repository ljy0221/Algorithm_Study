import java.util.*;

public class Solution {
    private final static int MOD = 10007;

    public int solution(int n, int[] tops) {
        int green = tops[0] == 0 ? 2 : 3;
        int purple = 1;

        for (int i = 1; i < n; i++) {
            int nGreen, nPurple;
            if (tops[i] == 0) {
                nGreen = 2 * green + purple;
            } else {
                nGreen = 3 * green + 2 * purple;
            }
            nPurple = green + purple;

            green = nGreen % MOD;
            purple = nPurple % MOD;
        }

        return (green + purple) % MOD;
    }

    // public static void main(String[] args) {
    //     System.out.println(solution(4, new int[] { 1, 1, 0, 1 }));
    //     System.out.println(solution(2, new int[] { 0, 1 }));
    //     System.out.println(solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }));
    // }

}

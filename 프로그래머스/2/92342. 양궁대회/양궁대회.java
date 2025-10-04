import java.util.*;

public class Solution {
    static int[] answer;
    static int[] ryanInfo;
    static int maxDiff = 0;

    public int[] solution(int n, int[] info) {
        maxDiff = 0;
        answer = new int[11];
        ryanInfo = new int[11];

        dfs(0, n, info);

        if (maxDiff == 0) {
            return new int[] { -1 };
        }

        return answer;
    }

    private static void dfs(int idx, int remain, int[] apeachInfo) {
        if (idx == 11) {
            ryanInfo[10] = remain;

            int ryanScore = 0;
            int apeachScore = 0;

            for (int i = 0; i <= 10; i++) {
                int score = 10 - i;
                if (ryanInfo[i] == 0 && apeachInfo[i] == 0) {
                    continue;
                } else if (ryanInfo[i] > apeachInfo[i]) {
                    ryanScore += score;
                } else {
                    apeachScore += score;
                }
            }

            int diff = ryanScore - apeachScore;

            if (diff > 0) {
                if (diff > maxDiff) {
                    maxDiff = diff;
                    answer = ryanInfo.clone();
                } else if (diff == maxDiff) {
                    if (rank(ryanInfo, answer)) {
                        answer = ryanInfo.clone();
                    }
                }
            }

            ryanInfo[10] = 0;

            return;
        }

        int needed = apeachInfo[idx] + 1;
        if (remain >= needed) {
            ryanInfo[idx] = needed;
            dfs(idx + 1, remain - needed, apeachInfo);
            ryanInfo[idx] = 0;
        }

        dfs(idx + 1, remain, apeachInfo);
    }

    private static boolean rank(int[] ryanInfo, int[] answer) {
        for (int i = 10; i >= 0; i--) {
            if (ryanInfo[i] != answer[i]) {
                return ryanInfo[i] > answer[i];
            }
        }

        return false;
    }

    // public static void main(String[] args) {
    //     System.out.println(Arrays.toString(solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 })));
    //     System.out.println(Arrays.toString(solution(1, new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 })));
    //     System.out.println(Arrays.toString(solution(9, new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 })));
    //     System.out.println(Arrays.toString(solution(10, new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 })));
    // }
}

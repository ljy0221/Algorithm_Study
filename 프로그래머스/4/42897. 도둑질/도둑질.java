class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        if (n == 1) return money[0];
        if (n == 2) return Math.max(money[0], money[1]);
        
        // 경우 1: 첫 번째 집을 포함 (마지막 집 제외)
        int case1 = robLinear(money, 0, n - 2);
        
        // 경우 2: 첫 번째 집을 제외 (마지막 집 포함 가능)
        int case2 = robLinear(money, 1, n - 1);
        
        return Math.max(case1, case2);
    }
    
    // 일직선 배열에서 최대 금액 계산
    private int robLinear(int[] money, int start, int end) {
        int prev2 = 0;  // dp[i-2]
        int prev1 = 0;  // dp[i-1]
        
        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}
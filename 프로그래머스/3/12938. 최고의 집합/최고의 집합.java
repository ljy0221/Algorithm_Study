import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(n > s) return new int[] {-1};
        
        int[] bestSet = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            bestSet[i] = s/n;
            sum += bestSet[i];
        }
        
        for (int i = 0; i < s % n; i++) {
            bestSet[n - 1 - i]++;
        }
        
        return bestSet;
    }
   
}
import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int idxA = 0, idxB = 0;
        
        while(idxB < B.length) {
            if(B[idxB++] > A[idxA]) {
                answer++;
                idxA++;
            }
        }
        
        return answer;
    }
}
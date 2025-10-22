import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int work : works) {
            pq.offer(work);
        }
        
        while(n-- > 0 && !pq.isEmpty()) {
            int work = pq.poll()-1;
            if(work > 0) {
                pq.offer(work);
            }
        }
        
        while(!pq.isEmpty()) {
            int work = pq.poll();
            answer += work*work;
        }
        
        return answer;
    }
}
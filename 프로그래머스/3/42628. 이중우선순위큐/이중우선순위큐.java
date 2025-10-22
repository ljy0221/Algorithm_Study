import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> heap = new TreeSet<>();
        StringTokenizer st;
        for(String operation : operations) {
            st = new StringTokenizer(operation);
            char op = st.nextToken().charAt(0);
            switch(op) {
                case 'I':
                    int num = Integer.parseInt(st.nextToken());
                    heap.add(num);
                    break;
                case 'D':
                    int target = Integer.parseInt(st.nextToken());
                    
                    if(target == -1) {
                        heap.pollFirst();
                    } else if(target == 1) {
                        heap.pollLast();
                    }
                    break;
            }
        }
        
        if(heap.isEmpty()) {
            return new int[] {0, 0};
        }
        
        int minVal = heap.first();
        int maxVal = heap.last();
        
        return new int[] {maxVal, minVal};
    }
}
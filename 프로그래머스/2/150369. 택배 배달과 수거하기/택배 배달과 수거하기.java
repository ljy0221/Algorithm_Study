class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int deliveryIdx = n - 1;  // 배달이 필요한 가장 먼 집
        int pickupIdx = n - 1;    // 수거가 필요한 가장 먼 집
        
        while (deliveryIdx >= 0 || pickupIdx >= 0) {
            // 배달/수거가 없는 집들은 스킵
            while (deliveryIdx >= 0 && deliveries[deliveryIdx] == 0) {
                deliveryIdx--;
            }
            while (pickupIdx >= 0 && pickups[pickupIdx] == 0) {
                pickupIdx--;
            }
            
            // 더 이상 할 일이 없으면 종료
            if (deliveryIdx < 0 && pickupIdx < 0) break;
            
            // 가장 먼 거리로 한 번 왕복
            int farthest = Math.max(deliveryIdx, pickupIdx);
            answer += (long)(farthest + 1) * 2;
            
            // 배달 처리 (cap만큼, 뒤에서부터)
            int deliverLoad = cap;
            for (int i = deliveryIdx; i >= 0 && deliverLoad > 0; i--) {
                int deliver = Math.min(deliveries[i], deliverLoad);
                deliveries[i] -= deliver;
                deliverLoad -= deliver;
            }
            
            // 수거 처리 (cap만큼, 뒤에서부터)
            int pickupLoad = cap;
            for (int i = pickupIdx; i >= 0 && pickupLoad > 0; i--) {
                int pickup = Math.min(pickups[i], pickupLoad);
                pickups[i] -= pickup;
                pickupLoad -= pickup;
            }
        }
        
        return answer;
    }
}
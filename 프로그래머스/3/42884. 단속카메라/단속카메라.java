import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] route1, int[] route2) {
                return route1[1] - route2[1];
            }
        });
        
        int cameraCount = 0;
        int lastCameraPosition = -30001; 
        
        for (int[] route : routes) {
            int entry = route[0];
            int exit = route[1];
            
            if (entry > lastCameraPosition) {
                
                cameraCount++;
                
                lastCameraPosition = exit; 
            }
        }
        
        return cameraCount;
    }
}
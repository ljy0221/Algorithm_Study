class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playSec = timeToSec(play_time);
        int advSec = timeToSec(adv_time);

        long[] totalTime = new long[playSec + 1];

        for (String log : logs) {
            String[] time = log.split("-");
            int start = timeToSec(time[0]);
            int end = timeToSec(time[1]);
            
            // 차분배열 트릭
            totalTime[start]++;
            totalTime[end]--;
        }

        // 시청 인원 파악
        for (int i = 1; i <= playSec; i++) {
            totalTime[i] += totalTime[i - 1];
        }

        // 시청 시간 파악
        for (int i = 1; i <= playSec; i++) {
            totalTime[i] += totalTime[i - 1];
        }

        long maxView = totalTime[advSec - 1]; 
        int maxStartTime = 0;

        for (int i = advSec; i < playSec; i++) {
            long currentView = totalTime[i] - totalTime[i - advSec];
            
            if (currentView > maxView) {
                maxView = currentView;
                maxStartTime = i - advSec + 1;
            }
        }

        return secToTime(maxStartTime);
    }
    
    private int timeToSec(String time) {
        String[] t = time.split(":");
        int hour = Integer.parseInt(t[0]);
        int min = Integer.parseInt(t[1]);
        int sec = Integer.parseInt(t[2]);
        
        return hour * 3600 + min * 60 + sec;
    }
    
    private String secToTime(int totalSeconds) {
        int hour = totalSeconds / 3600;
        int minute = (totalSeconds % 3600) / 60;
        int second = totalSeconds % 60;
        
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
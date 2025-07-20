import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int plates = Integer.parseInt(st.nextToken());        // 접시의 수
        int dishes = Integer.parseInt(st.nextToken());        // 초밥의 가짓수
        int continuousDishes = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int coupon = Integer.parseInt(st.nextToken());        // 쿠폰 번호
        
        int[] sushiOnRail = new int[plates];
        
        for (int i = 0; i < plates; i++) {
            sushiOnRail[i] = Integer.parseInt(br.readLine());
        }
        
        // 슬라이딩 윈도우를 위한 카운트 배열
        int[] count = new int[dishes + 1];
        int uniqueCount = 0;  // 현재 윈도우에서 먹을 수 있는 초밥 종류 수
        int maxSushi = 0;     // 최대 초밥 종류 수
        
        // 첫 번째 윈도우 설정
        for (int i = 0; i < continuousDishes; i++) {
            if (count[sushiOnRail[i]] == 0) {
                uniqueCount++;
            }
            count[sushiOnRail[i]]++;
        }
        
        // 쿠폰 초밥도 고려
        if (count[coupon] == 0) {
            maxSushi = uniqueCount + 1;
        } else {
            maxSushi = uniqueCount;
        }
        
        // 슬라이딩 윈도우로 모든 경우 확인
        for (int i = 1; i < plates; i++) {
            // 이전 윈도우의 첫 번째 원소 제거
            int removeIdx = i - 1;
            count[sushiOnRail[removeIdx]]--;
            if (count[sushiOnRail[removeIdx]] == 0) {
                uniqueCount--;
            }
            
            // 새로운 윈도우의 마지막 원소 추가 (원형이므로 % 연산)
            int addIdx = (i + continuousDishes - 1) % plates;
            if (count[sushiOnRail[addIdx]] == 0) {
                uniqueCount++;
            }
            count[sushiOnRail[addIdx]]++;
            
            // 쿠폰 초밥 고려하여 최댓값 갱신
            int currentMax;
            if (count[coupon] == 0) {
                currentMax = uniqueCount + 1;
            } else {
                currentMax = uniqueCount;
            }
            
            maxSushi = Math.max(maxSushi, currentMax);
        }
        
        System.out.println(maxSushi);
    }
}
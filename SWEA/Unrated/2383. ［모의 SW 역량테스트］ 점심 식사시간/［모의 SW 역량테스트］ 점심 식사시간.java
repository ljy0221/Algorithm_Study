import java.io.*;
import java.util.*;

public class Solution {
    static class Person {
        int r, c;
        Person(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    static class Stair {
        int r, c, length;
        Stair(int r, int c, int length) {
            this.r = r;
            this.c = c;
            this.length = length;
        }
    }
    
    static int N;
    static List<Person> people;
    static List<Stair> stairs;
    static int minTime;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            people = new ArrayList<>();
            stairs = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    if (value == 1) {
                        people.add(new Person(i, j));
                    } else if (value >= 2) {
                        stairs.add(new Stair(i, j, value));
                    }
                }
            }
            
            minTime = Integer.MAX_VALUE;
            
            // 계단 조합
            dfs(0, new int[people.size()]);
            sb.append("#" + tc + " " + minTime).append('\n');
        }

        System.out.print(sb);
    }
    
    // 각 사람이 어느 계단
    static void dfs(int personIdx, int[] chosenStairs) {
        if (personIdx == people.size()) {
            // 모든 사람의 계단 배정이 완료되면 시뮬레이션 실행
            int time = simulate(chosenStairs);
            minTime = Math.min(minTime, time);
            return;
        }
        
        // 현재 사람이 첫 번째 계단 또는 두 번째 계단 선택
        for (int stairIdx = 0; stairIdx < 2; stairIdx++) {
            chosenStairs[personIdx] = stairIdx;
            dfs(personIdx + 1, chosenStairs);
        }
    }
    
    // 주어진 계단 배정에 대해 시뮬레이션 실행
    static int simulate(int[] chosenStairs) {
        // 각 계단별로 도착하는 사람들을 분류
        List<Integer> stair1Times = new ArrayList<>();
        List<Integer> stair2Times = new ArrayList<>();
        
        for (int i = 0; i < people.size(); i++) {
            Person p = people.get(i);
            int stairIdx = chosenStairs[i];
            Stair s = stairs.get(stairIdx);
            
            // 맨하탄 거리로 이동 시간 계산
            int moveTime = Math.abs(p.r - s.r) + Math.abs(p.c - s.c);
            
            if (stairIdx == 0) {
                stair1Times.add(moveTime);
            } else {
                stair2Times.add(moveTime);
            }
        }
        
        // 각 계단의 완료 시간 계산
        int stair1Complete = calculateStairTime(stair1Times, stairs.get(0).length);
        int stair2Complete = calculateStairTime(stair2Times, stairs.get(1).length);
        
        return Math.max(stair1Complete, stair2Complete);
    }
    
    // 특정 계단에서 모든 사람이 내려가는데 걸리는 시간 계산
    static int calculateStairTime(List<Integer> arrivalTimes, int stairLength) {
        if (arrivalTimes.isEmpty()) {
            return 0;
        }
        
        // 도착 시간 순
        Collections.sort(arrivalTimes);
        
        // 사람별 시간
        List<Integer> completeTimes = new ArrayList<>();
        
        for (int i = 0; i < arrivalTimes.size(); i++) {
            int arrivalTime = arrivalTimes.get(i);
            int startTime;
            
            if (i < 3) {
                // 대기 없이 시작
                startTime = arrivalTime + 1;
            } else {
                // 앞의 3명 완료 기다림
                int waitUntil = completeTimes.get(i - 3);
                startTime = Math.max(arrivalTime + 1, waitUntil);
            }
            
            int completeTime = startTime + stairLength;
            completeTimes.add(completeTime);
        }
        
        // 마지막 완료 시간
        return completeTimes.get(completeTimes.size() - 1);
    }
}
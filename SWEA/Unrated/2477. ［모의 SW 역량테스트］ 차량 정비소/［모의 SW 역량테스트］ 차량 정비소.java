import java.util.*;
import java.io.*;

class Customer {
    int number, arrivalTime, desk, repairDesk;
    
    public Customer(int number, int arrivalTime) {
        this.number = number;
        this.arrivalTime = arrivalTime;
        this.desk = 0;
        this.repairDesk = 0;
    }
}

class Waiting implements Comparable<Waiting> {
    int time, desk, number;
    
    public Waiting(int time, int desk, int number) {
        this.time = time;
        this.desk = desk;
        this.number = number;
    }
    
    @Override
    public int compareTo(Waiting o) {
        if (this.time != o.time) {
            return this.time - o.time;
        }
        return this.desk - o.desk;
    }
}

class Desk {
    boolean inUse;
    int time;
    int number;
    
    public Desk() {
        this.inUse = false;
        this.time = 0;
        this.number = 0;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 접수 창구 수
            int M = Integer.parseInt(st.nextToken()); // 정비 창구 수
            int K = Integer.parseInt(st.nextToken()); // 고객 수
            int A = Integer.parseInt(st.nextToken()); // 찾는 접수 창구
            int B = Integer.parseInt(st.nextToken()); // 찾는 정비 창구
            
            // 접수 창구 처리 시간
            st = new StringTokenizer(br.readLine());
            int[] receptionTimes = new int[N];
            for (int i = 0; i < N; i++) {
                receptionTimes[i] = Integer.parseInt(st.nextToken());
            }
            
            // 정비 창구 처리 시간
            st = new StringTokenizer(br.readLine());
            int[] repairTimes = new int[M];
            for (int i = 0; i < M; i++) {
                repairTimes[i] = Integer.parseInt(st.nextToken());
            }
            
            // 고객 도착 시간
            st = new StringTokenizer(br.readLine());
            Customer[] customers = new Customer[K];
            for (int i = 0; i < K; i++) {
                int arrivalTime = Integer.parseInt(st.nextToken());
                customers[i] = new Customer(i + 1, arrivalTime);
            }
            
            // 창구 상태
            Desk[] desks = new Desk[N];
            Desk[] repairDesks = new Desk[M];
            for (int i = 0; i < N; i++) desks[i] = new Desk();
            for (int i = 0; i < M; i++) repairDesks[i] = new Desk();
            
            // 대기 큐
            PriorityQueue<Integer> receptionQueue = new PriorityQueue<>();
            PriorityQueue<Waiting> repairQueue = new PriorityQueue<>();
            
            int time = 0;
            int customerIdx = 0;
            
            while (true) {
                // 1. 도착한 고객들을 접수 대기열에 추가
                while (customerIdx < K && customers[customerIdx].arrivalTime <= time) {
                    receptionQueue.offer(customers[customerIdx].number);
                    customerIdx++;
                }
                
                // 2. 접수 창구에서 완료된 고객들을 정비 대기열로 이동
                for (int i = 0; i < N; i++) {
                    if (desks[i].inUse && desks[i].time <= time) {
                        int customerNum = desks[i].number;
                        customers[customerNum - 1].desk = i + 1;
                        repairQueue.offer(new Waiting(time, i + 1, customerNum));
                        desks[i] = new Desk();
                    }
                }
                
                // 3. 정비 창구에서 완료된 고객들 처리
                for (int j = 0; j < M; j++) {
                    if (repairDesks[j].inUse && repairDesks[j].time <= time) {
                        int customerNum = repairDesks[j].number;
                        customers[customerNum - 1].repairDesk = j + 1;
                        repairDesks[j] = new Desk();
                    }
                }
                
                // 4. 접수 창구 배정
                for (int i = 0; i < N; i++) {
                    if (!desks[i].inUse && !receptionQueue.isEmpty()) {
                        int customerNum = receptionQueue.poll();
                        desks[i].inUse = true;
                        desks[i].time = time + receptionTimes[i];
                        desks[i].number = customerNum;
                    }
                }
                
                // 5. 정비 창구 배정
                for (int j = 0; j < M; j++) {
                    if (!repairDesks[j].inUse && !repairQueue.isEmpty()) {
                        Waiting waiting = repairQueue.poll();
                        repairDesks[j].inUse = true;
                        repairDesks[j].time = time + repairTimes[j];
                        repairDesks[j].number = waiting.number;
                    }
                }
                
                // 6. 종료 조건 확인
                boolean done = true;
                
                if (customerIdx < K) done = false;
                if (!receptionQueue.isEmpty()) done = false;
                if (!repairQueue.isEmpty()) done = false;
                
                for (int i = 0; i < N; i++) {
                    if (desks[i].inUse) {
                        done = false;
                        break;
                    }
                }
                
                for (int j = 0; j < M; j++) {
                    if (repairDesks[j].inUse) {
                        done = false;
                        break;
                    }
                }
                
                if (done) break;
                
                time++;
            }
            
            int result = 0;
            boolean found = false;
            
            for (Customer customer : customers) {
                if (customer.desk == A && customer.repairDesk == B) {
                    result += customer.number;
                    found = true;
                }
            }
            
            if (!found) result = -1;
            
            System.out.println("#" + tc + " " + result);
        }
    }
}
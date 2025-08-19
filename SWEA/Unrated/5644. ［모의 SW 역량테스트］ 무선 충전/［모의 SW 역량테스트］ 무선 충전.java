import java.io.*;
import java.util.*;

public class Solution {
    static class BC {
        int r, c, coverage, performance;
        
        public BC(int r, int c, int coverage, int performance) {
            this.r = r;
            this.c = c;
            this.coverage = coverage;
            this.performance = performance;
        }
    }
    
    static int[] dr = {0, -1, 0, 1, 0};
    static int[] dc = {0, 0, 1, 0, -1};
    
    static int M, A;
    static int[] moveA, moveB;
    static BC[] chargers;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            
            moveA = new int[M];
            moveB = new int[M];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveA[i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                moveB[i] = Integer.parseInt(st.nextToken());
            }
            chargers = new BC[A];
            for (int i = 0; i < A; i++) {
                st = new StringTokenizer(br.readLine());
                int inputR = Integer.parseInt(st.nextToken());
                int inputC = Integer.parseInt(st.nextToken());
                int coverage = Integer.parseInt(st.nextToken());
                int performance = Integer.parseInt(st.nextToken());
                chargers[i] = new BC(inputC, inputR, coverage, performance);
            }
            
            int result = solve();
            System.out.println("#" + tc + " " + result);
        }
    }
    
    static int solve() {
        int totalCharge = 0;
        
        int aRow = 1, aCol = 1;
        int bRow = 10, bCol = 10;
        
        totalCharge += getMaxCharge(aRow, aCol, bRow, bCol);
        
        for (int time = 0; time < M; time++) {
            aRow += dr[moveA[time]];
            aCol += dc[moveA[time]];
            bRow += dr[moveB[time]];
            bCol += dc[moveB[time]];
            
            totalCharge += getMaxCharge(aRow, aCol, bRow, bCol);
        }
        
        return totalCharge;
    }
    
    static int getMaxCharge(int aRow, int aCol, int bRow, int bCol) {
        List<Integer> chargeableA = new ArrayList<>();
        List<Integer> chargeableB = new ArrayList<>();
        
        for (int i = 0; i < A; i++) {
            if (getDistance(aRow, aCol, chargers[i].r, chargers[i].c) <= chargers[i].coverage) {
                chargeableA.add(i);
            }
        }
        
        for (int i = 0; i < A; i++) {
            if (getDistance(bRow, bCol, chargers[i].r, chargers[i].c) <= chargers[i].coverage) {
                chargeableB.add(i);
            }
        }
        
        int maxCharge = 0;
        
        if (chargeableA.isEmpty()) {
            chargeableA.add(-1);
        }
        
        if (chargeableB.isEmpty()) {
            chargeableB.add(-1);
        }
        
        for (int bcA : chargeableA) {
            for (int bcB : chargeableB) {
                int charge = 0;
                
                if (bcA == -1 && bcB == -1) {
                    charge = 0;
                } else if (bcA == -1) {
                    charge = chargers[bcB].performance;
                } else if (bcB == -1) {
                    charge = chargers[bcA].performance;
                } else if (bcA == bcB) {
                    charge = chargers[bcA].performance;
                } else {
                    charge = chargers[bcA].performance + chargers[bcB].performance;
                }
                
                maxCharge = Math.max(maxCharge, charge);
            }
        }
        
        return maxCharge;
    }
    
    static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
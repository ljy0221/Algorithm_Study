import java.io.*;
import java.util.*;

public class Solution {
    static class Pos {
        int r,c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Pos pos = (Pos) obj;
            return r == pos.r && c == pos.c;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
    static int T,N,M;
    static HashSet<Pos> homeList;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            homeList = new HashSet<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    if(st.nextToken().equals("1")) {
                        homeList.add(new Pos(i, j));
                    }
                }
            }

            int maxHouses = 0;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int k = 1;
                while (true) {
                    int cost = getCost(k);
                    int houses = getHousesInRange(i, j, k-1);
                    int income = houses*M;

                    if(income >= cost) {
                        maxHouses = Math.max(maxHouses, houses);
                    }
                    if (houses == homeList.size() || k - 1 >= N + N) break;
                    k++;
                }
                }
            }

            sb.append("#").append(tc).append(" ").append(maxHouses).append('\n');

        }
        System.out.print(sb);
        
    }
    private static int getHousesInRange(int r, int c, int dist) {
        int cnt = 0;

        for (int nr = Math.max(0, r - dist); nr <= Math.min(N - 1, r + dist); nr++) {
            for (int nc = Math.max(0, c - dist); nc <= Math.min(N - 1, c + dist); nc++) {
                if (getDistance(r, c, nr, nc) <= dist && homeList.contains(new Pos(nr, nc))) {
                    // System.out.println("up!");
                    cnt++;
                }
            }
        }

        return cnt;
    }
    private static int getDistance(int r, int c, int nr, int nc) {
        return Math.abs(r-nr)+Math.abs(c-nc);
    }
    private static int getCost(int k) {
        return k*k+(k-1)*(k-1);
    }
}
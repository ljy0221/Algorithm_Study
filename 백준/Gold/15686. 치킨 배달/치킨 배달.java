import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Coord {
        int r, c;

        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
    }

    static int[][] map;
    static int N, M;
    static List<Coord> houseList;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        List<Coord> chickenStreet = new ArrayList<>();
        houseList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    chickenStreet.add(new Coord(i, j));
                } else if(map[i][j] == 1) {
                    houseList.add(new Coord(i, j));
                }
            }
        }

        int storeCount = chickenStreet.size();
        int minDistance = Integer.MAX_VALUE;
        for(int mask = 1; mask < (1 << storeCount); mask++) {
            int selectCount = Integer.bitCount(mask);

            if(selectCount <= M) {
                List<Coord> selectedStoreList = new ArrayList<>();
                for(int i = 0; i < storeCount; i++) {
                    if((mask & (1 << i)) != 0) {
                        selectedStoreList.add(chickenStreet.get(i));
                    }
                }

                int chickenDistance = calculateDistance(selectedStoreList);
                minDistance = Math.min(minDistance, chickenDistance);
            }
        }

        System.out.println(minDistance);

    }
    private static int calculateDistance(List<Coord> selectedStoreList) {
        int totalDistance = 0;

        for(Coord house : houseList) {
            int minChickenDistance = Integer.MAX_VALUE;
            for(Coord chicken : selectedStoreList) {
                int distance = Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
                minChickenDistance = Math.min(minChickenDistance, distance);
            }
            totalDistance += minChickenDistance;
        }

        return totalDistance;
    }

    
}

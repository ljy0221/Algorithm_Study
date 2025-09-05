import java.io.*;
import java.util.*;

public class Main {
    private static class CCTV implements Comparable<CCTV>{
        int r, c, num;

        public CCTV(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }

        @Override
        public int compareTo(CCTV o) {
            return Integer.compare(o.num, this.num);
        }
        
    }

    private static int N, M, val;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        List<CCTV> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(1 <= map[i][j] && map[i][j] <= 5) {
                    list.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        Collections.sort(list);
        val = Integer.MAX_VALUE;

        solve(list, 0, map);


        System.out.println(val);
    }


    private static int getCnt(int[][] map) {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }


    private static void solve(List<CCTV> list, int idx, int[][] map) {
        if(idx == list.size()) {
            val = Math.min(val, getCnt(map));
            return;
        }

        // if(getCnt(map) > val) return;
        
        CCTV current = list.get(idx);
        int[][] copy;

        switch (current.num) {
            case 1:
                copy = paint(current, map, 0);
                solve(list, idx+1, copy);
                copy = paint(current, map, 1);
                solve(list, idx+1, copy);
                copy = paint(current, map, 2);
                solve(list, idx+1, copy);
                copy = paint(current, map, 3);
                solve(list, idx+1, copy);
                break;
            case 2:
                copy = paint(current, map, 0);
                copy = paint(current, copy, 2);
                solve(list, idx+1, copy);
                copy = paint(current, map, 1);
                copy = paint(current, copy, 3);
                solve(list, idx+1, copy);
                break;
            case 3:
                copy = paint(current, map, 0);
                copy = paint(current, copy, 1);
                solve(list, idx+1, copy);
                copy = paint(current, map, 1);
                copy = paint(current, copy, 2);
                solve(list, idx+1, copy);
                copy = paint(current, map, 2);
                copy = paint(current, copy, 3);
                solve(list, idx+1, copy);
                copy = paint(current, map, 3);
                copy = paint(current, copy, 0);
                solve(list, idx+1, copy);
                break;
            case 4:
                copy = paint(current, map, 0);
                copy = paint(current, copy, 1);
                copy = paint(current, copy, 2);
                solve(list, idx+1, copy);
                copy = paint(current, map, 1);
                copy = paint(current, copy, 2);
                copy = paint(current, copy, 3);
                solve(list, idx+1, copy);
                copy = paint(current, map, 2);
                copy = paint(current, copy, 3);
                copy = paint(current, copy, 0);
                solve(list, idx+1, copy);
                copy = paint(current, map, 3);
                copy = paint(current, copy, 0);
                copy = paint(current, copy, 1);
                solve(list, idx+1, copy);
                break;
            case 5:
                copy = paint(current, map, 0);
                copy = paint(current, copy, 1);
                copy = paint(current, copy, 2);
                copy = paint(current, copy, 3);
                solve(list, idx+1, copy);
                break;
            default:
                break;
        }
    }


    private static int[][] paint(CCTV current, int[][] map, int dir) {
        int[][] copy = copyArr(map);
        int r = current.r;
        int c = current.c;
        switch (dir) {
            case 0: // up
                for (int i = r-1; i >= 0; i--) {
                    if(copy[i][c]==6) break;
                    if(copy[i][c]==0) copy[i][c]=-1;
                }
                break;
            case 1: // left
                for(int i=c-1; i>=0; i--) {
                    if(copy[r][i] == 6) break;
                    if(copy[r][i] == 0) copy[r][i] = -1;
                }
                break;
            case 2: // down
                for(int i=r+1; i<N; i++) {
                    if(copy[i][c] == 6) break;
                    if(copy[i][c] == 0) copy[i][c] = -1;
                }
                break;
            case 3: // right
                for(int i=c+1; i<M; i++) {
                    if(copy[r][i] == 6) break;
                    if(copy[r][i] == 0) copy[r][i] = -1;
                }
                break;
            default:
                break;
        }

        return copy;
    }


    private static int[][] copyArr(int[][] map) {
        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = map[i][j];
            }
        }

        return arr;
    }
}
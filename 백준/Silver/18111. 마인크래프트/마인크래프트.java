import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int maxHeight = -1;
        int minHeight = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] > maxHeight) {
                    maxHeight = map[i][j];
                }

                if(map[i][j] < minHeight) {
                    minHeight = map[i][j];
                }
            }
        }

        long minTime = Long.MAX_VALUE;
        int targetHeight = 0;

        for(int h = minHeight; h <= maxHeight; h++) {
            int uninstall = 0;
            int install = 0;
            long curTime = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int diff = map[i][j] - h;

                    if(diff > 0) {
                        uninstall += diff;
                        curTime += diff*2;
                    } else if (diff < 0) {
                        install += Math.abs(diff);
                        curTime += Math.abs(diff);
                    }
                }
            }

            if(B + uninstall >= install) {
                if(curTime <= minTime) {
                    minTime = curTime;
                    targetHeight = h;
                }
            }
        }

        System.out.println(minTime + " " + targetHeight);
    }
}

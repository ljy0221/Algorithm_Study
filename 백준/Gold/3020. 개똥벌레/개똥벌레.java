import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, h;
    static int[] diff;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        diff = new int[h + 2]; // 차분 배열

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());
            if(i%2 == 0) {  // 바닥 부터
                diff[1]++;
                diff[height+1]--;
            } else {    // 천장 부터
                diff[h+1 - height]++;
                diff[h+1]--;
            }
        }

        int minObs = Integer.MAX_VALUE;
        int count = 0;
        int currentObs = 0;

        for (int i = 1; i <= h; i++) {
            currentObs += diff[i];

            if(currentObs < minObs) {
                minObs = currentObs;
                count = 1;
            } else if (currentObs == minObs) {
                count++;
            }
        }

        System.out.println(minObs + " " + count);
    }
}
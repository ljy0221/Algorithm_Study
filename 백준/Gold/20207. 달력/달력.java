import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] diff = new int[367];
    static int[] calendar = new int[366];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int end = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            diff[S]++;
            diff[E+1]--;
            end = Math.max(end, E+1);
        }

        int accumulate = 0;
        int sum = 0;

        int width = 0;
        int height = 0;

        for (int i = 0; i < end; i++) {
            accumulate += diff[i];
            // System.out.print(accumulate+" ");
            if(accumulate == 0) {
                sum += width*height;
                // System.out.println(sum);
                width = height = 0;
            } else {
                width++;
                height = Math.max(height, accumulate);
            }

            // System.out.println("width: "+width+" height: "+height);
        }

        if(width!=0 && height!=0) sum += width*height;

        System.out.println(sum);
    }
}

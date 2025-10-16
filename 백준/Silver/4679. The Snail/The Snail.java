import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static double H, U;
    static int D, F;

    public static void main(String[] args) throws IOException {

        while (true) {
            st = new StringTokenizer(br.readLine());
            H = Double.parseDouble(st.nextToken());
            U = Double.parseDouble(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            F = Integer.parseInt(st.nextToken());

            if (H == 0)
                break;

            double climbDist = U;
            double currentHeight = 0;
            int day = 1;
            boolean success = false;
            while (true) {
                // System.out.println("day: " + day);
                // System.out.println("Initial Height: " + currentHeight);

                // day
                currentHeight += climbDist;
                if (currentHeight > H) {
                    success = true;
                    break;
                }
                // System.out.println("Height After Climbed: " + currentHeight);
                // night
                currentHeight -= D;
                // System.out.println("Height After Sliding: " + currentHeight);
                if (currentHeight < 0) {
                    break;
                }

                day++;
                climbDist -= U * ((double) F / 100.0);
                if (climbDist < 0)
                    climbDist = 0;
            }

            if (success) {
                sb.append("success on day ").append(day).append('\n');
            } else {
                sb.append("failure on day ").append(day).append('\n');
            }
        }

        System.out.print(sb);
    }
}

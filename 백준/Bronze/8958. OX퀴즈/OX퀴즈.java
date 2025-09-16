import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String in = br.readLine();

            int score = 0, cnt = 0;

            for (int j = 0; j < in.length(); j++) {
                if(in.charAt(j) == 'O') {
                    cnt++;
                    score += cnt;
                } else {
                    cnt = 0;
                }
            }

            System.out.println(score);
        }
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int L;
    static String in;
    static final int r = 31;
    static final int M = 1234567891;


    public static void main(String[] args) throws IOException {
        L = Integer.parseInt(br.readLine());
        in = br.readLine();

        long hash = 0;

        for (int i = 0; i < L; i++) {
            int current = in.charAt(i)-'a'+1;
            long multiplier = (long)Math.pow(r, i);

            hash += current*multiplier;
        }

        System.out.println(hash);
    }
}

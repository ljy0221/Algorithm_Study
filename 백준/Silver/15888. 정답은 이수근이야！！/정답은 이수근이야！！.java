import java.io.*;
import java.util.*;

public class Main {
    
    static final int[] BIN_POWERS = {2, 4, 8, 16, 32, 64, 128, 256}; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A_in = Long.parseLong(st.nextToken());
        long B_in = Long.parseLong(st.nextToken());
        long C_in = Long.parseLong(st.nextToken());

        if (B_in % A_in != 0 || C_in % A_in != 0) {
            System.out.println("둘다틀렸근");
            return;
        }

        long b_long = B_in / A_in;
        long c_long = C_in / A_in;

        if (b_long * b_long - 4 * c_long <= 0) {
            System.out.println("둘다틀렸근");
            return;
        }

        for (int x1 : BIN_POWERS) {
            for (int x2 : BIN_POWERS) {
                if (x1 == x2) continue;
                
                if (x1 + x2 == -b_long && (long)x1 * x2 == c_long) {
                    System.out.println("이수근");
                    return;
                }
            }
        }

        for (int x1 = -200; x1 <= 200; x1++) {
            for (int x2 = -200; x2 <= 200; x2++) {
                if ((long)x1 + x2 == -b_long && (long)x1 * x2 == c_long) {
                    System.out.println("정수근");
                    return;
                }
            }
        }
        
        System.out.println("둘다틀렸근");
    }
}
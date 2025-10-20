import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int target = 0;

        for (int i = 0; i < 3; i++) {
            String in = br.readLine();

            if ('0' <= in.charAt(0) && in.charAt(0) <= '9') {
                target = Integer.valueOf(in) + (3 - i);
            }
        }

        if(target % 3 ==0 && target% 5 == 0) {
            System.out.println("FizzBuzz");
        } else if (target % 3 == 0) {
            System.out.println("Fizz");
        } else if (target % 5 == 0) {
            System.out.println("Buzz");
        } else {
            System.out.println(target);
        }
    }
}

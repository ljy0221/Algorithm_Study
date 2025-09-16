import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());

        int[] numbers = new int[10];

        String result = String.valueOf(A*B*C);

        for (int i = 0; i < result.length(); i++) {
            numbers[result.charAt(i)-'0']++;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(numbers[i]);
        }
    }
}
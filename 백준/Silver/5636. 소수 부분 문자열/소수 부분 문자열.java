import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        boolean[] isPrime = new boolean[100001];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int p = 2; p * p <= 100000; p++) {
            if (isPrime[p]) {

                for (int i = p * p; i <= 100000; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        while (true) {
            String input = br.readLine();

            if (input == null || input.equals("0"))
                break;

            int primeNum = 2;

            for (int i = 0; i < input.length(); i++) {
                for (int j = i + 1; j <= input.length(); j++) {

                    String sub = input.substring(i, j);
                    int num = Integer.parseInt(sub);

                    if (num > 100000) {

                        break;
                    }

                    if (isPrime[num] && num > primeNum) {
                        primeNum = num;
                    }
                }
            }

            sb.append(primeNum).append('\n');
        }

        System.out.print(sb);
    }
}
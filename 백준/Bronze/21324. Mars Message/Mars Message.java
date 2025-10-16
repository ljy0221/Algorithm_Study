import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        int[] hex = new int[N];

        for (int i = 0; i < N; i++) {
            double angle = Double.parseDouble(br.readLine());
            int hexIndex = (int) Math.floor(angle * 2.0 / 45.0);
            hex[i] = hexIndex;
        }

        for (int i = 0; i < N; i += 2) {
            int hexH = hex[i];
            int hexT = hex[i + 1];

            int ascii = hexH * 16 + hexT;

            sb.append((char) ascii);
        }

        System.out.println(sb);
    }
}

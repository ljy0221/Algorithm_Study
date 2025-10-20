import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        Integer N = Integer.parseInt(br.readLine());

        int p = 5;
        int five = 0;
        while (p <= N) {
            five += N / p;
            p *= 5;
        }
        System.out.println(five);
    }
}

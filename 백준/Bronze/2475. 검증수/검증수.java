import java.io.*;
import java.util.*;;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        int num = 0;
        for (int i = 0; i < 5; i++) {
            int in = Integer.parseInt(st.nextToken());
            num += in*in;
        }

        System.out.println(num%10);
    }
}
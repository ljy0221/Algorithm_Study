import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a==0 && b==0 && c==0) return;

            int a2 = a*a;
            int b2 = b*b;
            int c2 = c*c;

            if(a2+b2==c2 || a2+c2==b2 || b2+c2==a2) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }
    
}
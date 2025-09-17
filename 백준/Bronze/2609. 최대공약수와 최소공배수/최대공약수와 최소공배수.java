import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int A, B;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        System.out.println(getGCD(A, B));
        System.out.println(getLCM(A, B));
    }

    
    private static int getGCD(int a, int b) {
        if(b == 0) return a;
        return getGCD(b, a%b);
    }
    
    private static int getLCM(int a, int b) {
        return a*b/getGCD(a, b);
    }

}

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        long N = Long.parseLong(br.readLine());

        if(N >= Short.MIN_VALUE && N <= Short.MAX_VALUE) {
            System.out.println("short");
        } else if (N >= Integer.MIN_VALUE && N <= Integer.MAX_VALUE) {
            System.out.println("int");
        } else {
            System.out.println("long long");
        }
    }
}
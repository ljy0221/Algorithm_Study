import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N,T,P;
    static int[] sizes;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        sizes = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            sizes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int Tcnt = 0;
        for (int i = 0; i < 6; i++) {
            Tcnt += sizes[i]/T;
            if(sizes[i]%T != 0) Tcnt++;
        }

        System.out.println(Tcnt);
        System.out.println(N/P +" "+N%P);
    }
}

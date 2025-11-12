import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int ans = 0;

        for (int i = 0; i <= n+m; i++) {
            for (int x = 0; x <= n; x++) {
                for (int y = 0; y <= m; y++) {
                    if(x+y == i) {
                        ans++;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}

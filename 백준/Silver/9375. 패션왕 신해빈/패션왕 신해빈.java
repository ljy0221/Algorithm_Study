import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < t; i++) {
            Map<String, Integer> ware = new HashMap<>();
            int n = Integer.parseInt(br.readLine());
            
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();

                ware.put(type, ware.getOrDefault(type, 0)+1);    
            }

            int result = 1;
            for(int cnt : ware.values()) {
                result *= (cnt+1);
            }
            sb.append(result-1).append('\n');

        }

        System.out.print(sb);
    }

}

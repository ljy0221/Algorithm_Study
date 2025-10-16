import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        
        while (true) {
            String input = br.readLine();

            if(input.equals("0")) break;

            char[] S = input.toCharArray();
            int N = S.length;

            List<Integer> intervals = new ArrayList<>();

            for (int i = 1; i < N; i++) {
                if(S[i] == '1') {
                    intervals.add(i);

                    for (int term = i; term < N; term+=i) {
                        S[term] = (S[term] == '1') ? '0' : '1';
                    }
                }
            }

            for (int i = 0; i < intervals.size(); i++) {
                sb.append(intervals.get(i));

                if(i < intervals.size()-1) {
                    sb.append(" ");
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}

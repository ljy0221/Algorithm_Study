import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        int M = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        List<Integer> init = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            init.add(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int num = -1;
            switch (op) {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    set.add(num);
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    set.remove(num);
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    if(set.contains(num)) {
                        sb.append(1).append('\n');
                    } else {
                        sb.append(0).append('\n');
                    }
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    if(set.contains(num)) {
                        set.remove(num);
                    } else {
                        set.add(num);
                    }
                    break;
                case "all":
                    set = new HashSet<>(init);
                    break;
                case "empty":
                    set.clear();
                    break;
            }
        }

        System.out.print(sb);
    }
}

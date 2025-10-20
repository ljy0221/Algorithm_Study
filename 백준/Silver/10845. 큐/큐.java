import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static ArrayDeque<Integer> q;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            switch (op) {
                case "push":
                    q.offer(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(q.isEmpty() ? -1 : q.poll()).append('\n');
                    break;
                case "size":
                    sb.append(q.size()).append('\n');
                    break;
                case "empty":
                    sb.append(q.isEmpty() ? 1 : 0).append('\n');
                    break;
                case "front":
                    sb.append(q.isEmpty() ? -1 : q.peekFirst()).append('\n');
                    break;
                case "back":
                    sb.append(q.isEmpty() ? -1 : q.peekLast()).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }

}

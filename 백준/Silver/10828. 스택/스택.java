import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] stack;
    static int pc;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        stack = new int[10001];
        pc = -1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            switch (op) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append('\n');
                    break;
                case "size":
                    sb.append(size()).append('\n');
                    break;
                case "empty":
                    sb.append(empty()).append('\n');
                    break;
                case "top":
                    sb.append(top()).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }

    private static void push(int x) {
        stack[++pc] = x;
    }

    private static int pop() {
        return pc == -1 ? -1 : stack[pc--];
    }

    private static int size() {
        return pc + 1;
    }

    private static int empty() {
        return pc == -1 ? 1 : 0;
    }

    private static int top() {
        return pc == -1 ? -1 : stack[pc];
    }
}

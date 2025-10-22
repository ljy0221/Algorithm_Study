import java.io.*;
import java.util.*;

public class Main {
    static class State {
        int number;
        String command;

        public State(int number, String command) {
            this.number = number;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            sb.append(solve(num, target)).append('\n');
        }

        System.out.print(sb);
    }

    private static String solve(int start, int target) {
        Queue<State> queue = new LinkedList<>();
        boolean[] visited = new boolean[10000];

        queue.offer(new State(start, ""));
        visited[start] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            int n = current.number;

            if (n == target) {
                return current.command;
            }

            int nextD = (n * 2) % 10000;
            if (!visited[nextD]) {
                visited[nextD] = true;
                queue.offer(new State(nextD, current.command + 'D'));
            }

            int nextS = (n == 0) ? 9999 : n - 1;
            if (!visited[nextS]) {
                visited[nextS] = true;
                queue.offer(new State(nextS, current.command + 'S'));
            }

            int nextL = (n % 1000) * 10 + (n / 1000);
            if (!visited[nextL]) {
                visited[nextL] = true;
                queue.offer(new State(nextL, current.command + 'L'));
            }

            int nextR = (n / 10) + (n % 10) * 1000;
            if (!visited[nextR]) {
                visited[nextR] = true;
                queue.offer(new State(nextR, current.command + 'R'));
            }
        }
        return "";
    }
}
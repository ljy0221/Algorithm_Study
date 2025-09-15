import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[] visited;
    static int[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        selected = new int[M];

        recursive(1);
    }
    private static void recursive(int idx) {
        if(idx > M) {
            for (int j = 0; j < M; j++) {
                System.out.print(selected[j] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				selected[idx-1] = i;
				visited[i] = true;
				recursive(idx + 1);
				visited[i] = false;
			}
		}
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] arr;
    static int[] result;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        used = new boolean[N];
        result = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backtrack(0);

        System.out.print(sb);
    }

    private static void backtrack(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int previous_val = 0;

        for (int i = 0; i < N; i++) {
            if (used[i]) {
                continue;
            }

            if (arr[i] == previous_val) {
                continue;
            }

            used[i] = true;
            result[depth] = arr[i];
            
            previous_val = arr[i]; 

            backtrack(depth + 1);

            used[i] = false;
        }
    }
}
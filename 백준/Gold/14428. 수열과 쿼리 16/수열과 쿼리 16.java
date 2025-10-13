import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] tree;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new long[N + 1];
        tree = new int[4 * N];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(1, 1, N);

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 1) {
                arr[a] = b;
                update(1, 1, N, a);
            } else {
                sb.append(query(1, 1, N, a, b)).append('\n');
            }
        }

        System.out.print(sb);
    }

    private static int init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = start;
        }

        int mid = (start + end) / 2;
        int left = init(node * 2, start, mid);
        int right = init(node * 2 + 1, mid + 1, end);

        return tree[node] = (arr[left] <= arr[right]) ? left : right;
    }

    private static int update(int node, int start, int end, int idx) {
        if (idx < start || end < idx) {
            return tree[node];
        }

        if (start == end) {
            return tree[node] = idx;
        }

        int mid = (start + end) / 2;
        int left = update(node * 2, start, mid, idx);
        int right = update(node * 2 + 1, mid + 1, end, idx);

        return tree[node] = (arr[left] <= arr[right]) ? left : right;
    }

    private static int query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftIdx = query(node * 2, start, mid, left, right);
        int rightIdx = query(node * 2 + 1, mid + 1, end, left, right);

        if (leftIdx == 0) return rightIdx;
        if (rightIdx == 0) return leftIdx;

        return (arr[leftIdx] <= arr[rightIdx]) ? leftIdx : rightIdx;
    }
}
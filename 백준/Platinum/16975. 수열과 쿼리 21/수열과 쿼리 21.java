import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static long[] arr;
    static long[] tree;
    static long[] lazy;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new long[N + 1];
        tree = new long[4 * N];
        lazy = new long[4 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        init(1, 1, N);

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            switch (op) {
                case 1:
                    int from = Integer.parseInt(st.nextToken());
                    int to = Integer.parseInt(st.nextToken());
                    long val = Long.parseLong(st.nextToken());

                    update(1, 1, N, from, to, val);
                    break;
                case 2:
                    int idx = Integer.parseInt(st.nextToken());

                    sb.append(query(1, 1, N, idx)).append('\n');
                    break;

            }
        }

        System.out.print(sb);
    }

    private static long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }

    private static void update(int node, int start, int end, int left, int right, long val) {
        lazyPropagation(node, start, end);

        if (right < start || left > end)
            return;

        if (left <= start && right >= end) {
            lazy[node] += val;
            lazyPropagation(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, left, right, val);
        update(node * 2 + 1, mid + 1, end, left, right, val);

        lazyPropagation(node * 2, start, mid);
        lazyPropagation(node * 2 + 1, mid + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    private static long query(int node, int start, int end, int idx) {
        lazyPropagation(node, start, end);

        if (idx < start || idx > end)
            return 0;

        if (start == end)
            return tree[node];

        int mid = (start + end) / 2;

        return query(node * 2, start, mid, idx) + query(node * 2 + 1, mid + 1, end, idx);
    }

    private static void lazyPropagation(int node, int start, int end) {
        if(lazy[node] == 0) return;

        tree[node] += lazy[node]*(end-start+1);

        if(start != end) {
            lazy[node*2] += lazy[node];
            lazy[node*2+1] += lazy[node];
        }

        lazy[node] = 0;
    }

}

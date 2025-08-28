import java.util.*;
import java.io.*;

public class Main {
    static long[] segTree;
    static int N;
    private static final long MOD = 1000000007;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        segTree = new long[2*N];
        for (int i = 0; i < N; i++) {
            segTree[i+N] = Integer.parseInt(br.readLine());
        }

        for (int i = N-1; i > 0; i--) {
            segTree[i] = (segTree[i<<1] * segTree[i<<1|1])%MOD;
        }

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            switch (op) {
                case 1:
                    int idx = Integer.parseInt(st.nextToken())-1;
                    int val = Integer.parseInt(st.nextToken());
                    update(idx, val);
                    break;
                case 2:
                    int left = Integer.parseInt(st.nextToken())-1;
                    int right = Integer.parseInt(st.nextToken());
                    long result = query(left, right);
                    sb.append(result).append('\n');
                    break;
                default:
                    break;
            }
        }

        System.out.print(sb);
    }

    private static void update(int idx, int val) {
        idx += N;

        segTree[idx] = val;

        while (idx > 1) {
            idx >>= 1;
            segTree[idx] = (segTree[idx<<1] * segTree[idx<<1|1])%MOD;
        }
    }

    private static long query(int left, int right) {
        long res = 1;
        int l = left + N;
        int r = right + N;

        while (l < r) {
            if((l&1) == 1) res = (res*segTree[l++])%MOD;
            if((r&1) == 1) res = (res*segTree[--r])%MOD; 
            l >>= 1;
            r >>= 1;
        }

        return res%MOD;
    }



}

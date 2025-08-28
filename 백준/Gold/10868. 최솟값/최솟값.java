import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[] segTree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        segTree = new long[2*N];
        for (int i = 0; i < N; i++) {
            segTree[i+N] = Integer.parseInt(br.readLine());
        }

        for (int i = N-1; i > 0; i--) {
            segTree[i] = Math.min(segTree[i<<1], segTree[i<<1|1]);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken())-1;
            int right = Integer.parseInt(st.nextToken());
            long res = query(left, right);
            sb.append(res).append('\n');
        }

        System.out.print(sb);
    }
    private static long query(int left, int right) {
        long minVal = Integer.MAX_VALUE;

        int l = left+N;
        int r = right+N;

        while (l < r) {
            if((l&1) == 1) minVal = Math.min(minVal, segTree[l++]);
            if((r&1) == 1) minVal = Math.min(minVal, segTree[--r]);
            l >>= 1;
            r >>= 1;
        }

        return minVal;
    }
}

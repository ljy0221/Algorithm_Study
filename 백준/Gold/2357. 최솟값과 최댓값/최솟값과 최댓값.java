import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static Node[] segTree;

    private static class Node {
        long min, max;

        public Node (long min, long max) {
            this.min = min;
            this.max = max;
        }
        
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        segTree = new Node[2*N];

        for (int i = 0; i < N; i++) {
            segTree[i+N] = new Node(arr[i], arr[i]);
        }

        for (int i = N-1; i > 0; i--) {
            Node left = segTree[i<<1];
            Node right = segTree[i<<1|1];
            long max = Math.max(left.max, right.max);
            long min = Math.min(left.min, right.min);
            segTree[i] = new Node(min, max);
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken())-1;
            int right = Integer.parseInt(st.nextToken());

            Node result = query(left, right);
            sb.append(result.min).append(" ").append(result.max).append('\n');
        }

        System.out.print(sb);
    }

    public static Node query(int left, int right) {
        int l = left + N;
        int r = right + N;
        long min, max;
        min = Long.MAX_VALUE;
        max = Long.MIN_VALUE;
        while (l < r) {
            if((l&1)==1) {
                min = Math.min(min, segTree[l].min);
                max = Math.max(max, segTree[l].max);
                l++;
            }

            if((r&1)==1) {
                r--;
                min = Math.min(min, segTree[r].min);
                max = Math.max(max, segTree[r].max);
            }

            l >>= 1;
            r >>= 1;
        }

        return new Node(min, max);
    }

    
}

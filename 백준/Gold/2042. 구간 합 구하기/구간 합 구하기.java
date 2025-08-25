import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Long.parseLong(st.nextToken());
        }

        SegmentTree segTree = new SegmentTree(arr);

        int m = 0, k = 0;
        for (int i = 0; i < K+M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            switch (a) {
                case 1:
                    segTree.update(b-1, c);
                    m++;
                    break;
                case 2:
                    System.out.println(segTree.query(b-1, (int)c-1));
                    k++;
                    break;
                default:
                    break;
            }
            
        }
        
    }

    private static class SegmentTree {
        long[] tree;
        int n;

        SegmentTree(long[] arr) {
            n = arr.length;
            tree = new long[4*n];
            build(arr, 1, 0, n-1);
        }

        private void build(long[] arr, int node, int start, int end) {
            if(start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start+end)/2;
                build(arr, 2*node, start, mid);
                build(arr, 2*node+1, mid+1, end);
                tree[node] = tree[2*node] + tree[2*node+1];
            }
        }

        public long query(int left, int right) {
            return query(1, 0, n-1, left, right);
        }

        private long query(int node, int start, int end, int left, int right) {
            if(right < start || end < left) return 0;
            
            if(left <= start && end <= right) return tree[node];

            int mid = (start+end)/2;
            long leftSum = query(2*node, start, mid, left, right);
            long rightSum = query(2*node+1, mid+1, end, left, right);
            return leftSum+rightSum;
        }

        public void update(int idx, long val) {
            update(1, 0, n-1, idx, val);
        }

        private void update(int node, int start, int end, int idx, long val) {
            if(start == end) tree[node] = val;
            else {
                int mid = (start+end)/2;
                if(idx <= mid) {
                    update(2*node, start, mid, idx, val);
                } else {
                    update(2*node+1, mid+1, end, idx, val);
                }
                tree[node] = tree[2*node] + tree[2*node+1];
            }
        }
    }
}

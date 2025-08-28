import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        MinIndexSegmentTree mst = new MinIndexSegmentTree(arr);
        long ans = solve(mst, arr, 0, n-1);
        System.out.println(ans);

    }

    private static long solve(MinIndexSegmentTree mst, long[] heights, int left, int right) {
        if(left > right) return 0;
        
        int minIdx = mst.query(left, right);
        
        long candidate1 = heights[minIdx] * (right - left + 1);

        long candidate2 = solve(mst, heights, left, minIdx-1);

        long candidate3 = solve(mst, heights, minIdx+1, right);
        
        return Math.max(candidate1, Math.max(candidate2, candidate3));
    }

    private static class MinIndexSegmentTree {
        int[] tree;
        long[] heights;
        int n;

        // 생성자
        public MinIndexSegmentTree(long[] arr) {
            n = arr.length;
            heights = arr;
            tree = new int[4*n];
            build(1, 0, n-1);
        }

        private int compareAndReturn(int idx1, int idx2) {
            if(heights[idx1] <= heights[idx2]) {
                return idx1;
            }
            return idx2;
        }

        private void build(int node, int start, int end) {
            if(start == end) {
                tree[node] = start;
            } else {
                int mid = (start + end) / 2;
                build(2*node, start, mid);
                build(2*node+1, mid+1, end);

                tree[node] = compareAndReturn(tree[2*node], tree[2*node+1]);
            }
        }

        public int query(int left, int right) {
            return query(1, 0, n-1, left, right);
        }

        private int query(int node ,int start, int end, int left, int right) {
            if(right < start || end < left) return -1;
            if(left <= start && end <= right) return tree[node];
            
            int mid = (start + end) / 2;
            int leftMin = query(2*node, start, mid, left, right);
            int rightMin = query(2*node+1, mid+1, end, left, right);

            if(leftMin == -1) return rightMin;
            if(rightMin == -1) return leftMin;

            return compareAndReturn(leftMin, rightMin);
        }
    }
    
}

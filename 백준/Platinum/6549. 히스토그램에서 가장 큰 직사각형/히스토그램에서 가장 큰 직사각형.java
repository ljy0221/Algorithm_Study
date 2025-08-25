import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if(n == 0) break;
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Long.parseLong(st.nextToken());
            }
            
            MinIndexSegmentTree mst = new MinIndexSegmentTree(arr);
            long ans = solve(mst, arr, 0, n-1);
            System.out.println(ans);
        }
    }
    private static long solve(MinIndexSegmentTree mst, long[] heights, int left, int right) {
        if(left > right) return 0;  // 기저 조건
        
        int minIdx = mst.query(left, right);
        
        // 후보1: 전체 구간을 minIdx 높이로 채우는 직사각형
        long candidate1 = heights[minIdx] * (right - left + 1);
        
        // 후보2: 왼쪽 부분 최대 직사각형
        long candidate2 = solve(mst, heights, left, minIdx-1);
        
        // 후보3: 오른쪽 부분 최대 직사각형  
        long candidate3 = solve(mst, heights, minIdx+1, right);
        
        return Math.max(candidate1, Math.max(candidate2, candidate3));  // ✅ Math.max!
    }

    private static class MinIndexSegmentTree {
        int[] tree;        // 인덱스를 저장
        long[] heights;    // 원본 배열 참조용
        int n;

        // 생성자
        public MinIndexSegmentTree(long[] arr) {
            n = arr.length;
            heights = arr;                    // 원본 배열 참조
            tree = new int[4*n];             // 인덱스 저장용 (int 배열)
            build(1, 0, n-1);
        }

        // 두 인덱스 중 더 작은 값을 가진 인덱스 반환
        private int compareAndReturn(int idx1, int idx2) {
            if(heights[idx1] <= heights[idx2]) {
                return idx1;
            }
            return idx2;
        }

        private void build(int node, int start, int end) {
            if(start == end) {
                tree[node] = start;  // 인덱스 저장
            } else {
                int mid = (start + end) / 2;
                build(2*node, start, mid);
                build(2*node+1, mid+1, end);
                
                // 더 작은 값을 가진 인덱스 선택
                tree[node] = compareAndReturn(tree[2*node], tree[2*node+1]);
            }
        }

        // 쿼리 메서드들도 추가로 필요...

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

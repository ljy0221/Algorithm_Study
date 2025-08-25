import java.util.*;
import java.io.*;

public class Main {
    
    static class LazySegmentTree {
        private int n;
        private long[] tree;
        private long[] lazy;
        
        public LazySegmentTree(long[] arr) {
            n = arr.length;
            tree = new long[4 * n];
            lazy = new long[4 * n];
            build(arr, 1, 0, n - 1);
        }
        
        private void build(long[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
            } else {
                int mid = (start + end) / 2;
                build(arr, node * 2, start, mid);
                build(arr, node * 2 + 1, mid + 1, end);
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
            }
        }
        
        // 🔑 핵심: Push 함수 - lazy 값을 실제로 적용
        private void push(int node, int start, int end) {
            if (lazy[node] != 0) {
                // 현재 노드에 lazy 값 적용 (구간 크기만큼 곱하기)
                tree[node] += lazy[node] * (end - start + 1);
                
                // 리프 노드가 아니면 자식들에게 lazy 값 전파
                if (start != end) {
                    lazy[node * 2] += lazy[node];
                    lazy[node * 2 + 1] += lazy[node];
                }
                
                // 현재 노드의 lazy 값 초기화
                lazy[node] = 0;
            }
        }
        
        // 구간 업데이트: [l, r]에 val을 더함
        public void updateRange(int l, int r, long val) {
            updateRange(1, 0, n - 1, l, r, val);
        }
        
        private void updateRange(int node, int start, int end, int l, int r, long val) {
            push(node, start, end);  // 🔑 항상 push부터!
            
            if (start > r || end < l) return;  // 범위 벗어남
            
            // 현재 노드가 업데이트 범위에 완전히 포함됨
            if (start >= l && end <= r) {
                lazy[node] += val;  // lazy에 표시만 하고 끝
                push(node, start, end);  // 즉시 적용
                return;
            }
            
            // 부분적으로 겹치는 경우 - 자식으로 분할
            int mid = (start + end) / 2;
            updateRange(node * 2, start, mid, l, r, val);
            updateRange(node * 2 + 1, mid + 1, end, l, r, val);
            
            // 자식들의 값으로 현재 노드 업데이트
            push(node * 2, start, mid);
            push(node * 2 + 1, mid + 1, end);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
        
        // 구간 쿼리: [l, r] 구간의 합
        public long query(int l, int r) {
            return queryRange(1, 0, n - 1, l, r);
        }
        
        private long queryRange(int node, int start, int end, int l, int r) {
            if (start > r || end < l) return 0;  // 범위 벗어남
            
            push(node, start, end);  // 🔑 항상 push부터!
            
            // 현재 노드가 쿼리 범위에 완전히 포함됨
            if (start >= l && end <= r) {
                return tree[node];
            }
            
            // 부분적으로 겹치는 경우 - 자식들에서 합산
            int mid = (start + end) / 2;
            long leftSum = queryRange(node * 2, start, mid, l, r);
            long rightSum = queryRange(node * 2 + 1, mid + 1, end, l, r);
            return leftSum + rightSum;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        LazySegmentTree lst = new LazySegmentTree(arr);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            
            if (a == 1) {
                // 구간 업데이트: b번째부터 c번째까지 d를 더함
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long d = Long.parseLong(st.nextToken());
                lst.updateRange(b - 1, c - 1, d);  // 1-indexed → 0-indexed
            } else {
                // 구간 쿼리: b번째부터 c번째까지의 합
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                long result = lst.query(b - 1, c - 1);  // 1-indexed → 0-indexed
                sb.append(result).append('\n');
            }
        }
        
        System.out.print(sb);
    }
}
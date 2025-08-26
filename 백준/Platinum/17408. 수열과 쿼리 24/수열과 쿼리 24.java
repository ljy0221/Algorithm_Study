import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        long max1, max2;
        
        Node(long max1, long max2) {
            this.max1 = max1;
            this.max2 = max2;
        }

        static Node merge(Node a, Node b) {
            long[] candidates = {a.max1, a.max2, b.max1, b.max2};
            long first = 0, second = 0;
            
            for (long val : candidates) {
                if (val > first) {
                    second = first;
                    first = val;
                } else if (val > second) {
                    second = val;
                }
            }
            
            return new Node(first, second);
        }
    }
    
    static class SegTree {
        int n;
        Node[] tree;

        public SegTree(long[] arr) {
            n = arr.length;
            tree = new Node[2*n];

            for (int i = 0; i < n; i++) {
                tree[n+i] = new Node(arr[i], 0);
            }

            for (int i = n-1; i > 0; i--) {
                tree[i] = Node.merge(tree[i<<1], tree[i<<1|1]);
            }
        }

        public long query(int left, int right) {
            Node result = new Node(0, 0);
            left += n;
            right += n;

            while (left < right) {
                if((left & 1) == 1) {
                    result = Node.merge(result, tree[left++]);
                }
                if((right & 1) == 1) {
                    result = Node.merge(result, tree[--right]);
                }
                left >>= 1;
                right >>= 1;
            }

            return result.max1 + result.max2;
        }

        public void update(int idx, long val) {
            idx += n;
            tree[idx] = new Node(val, 0);

            while (idx > 1) {
                idx >>= 1;
                tree[idx] = Node.merge(tree[idx<<1], tree[idx<<1|1]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        SegTree segTree = new SegTree(arr);
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if (cmd == 1) {
                int idx = Integer.parseInt(st.nextToken()) - 1;
                long val = Long.parseLong(st.nextToken());
                segTree.update(idx, val);
            } else {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken());
                long ans = segTree.query(l, r);
                sb.append(ans).append('\n');
            }
        }

        System.out.print(sb);
    }
}
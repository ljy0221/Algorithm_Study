import java.util.*;
import java.io.*;

public class Main {
    private static class SegTree {
        int n;
        ArrayList<Long>[] tree;

        public SegTree(long[] arr) {
            n = arr.length;
            tree = new ArrayList[2*n];
            for (int i = 0; i < 2*n; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                tree[n+i].add(arr[i]);
            }

            for (int i = n-1; i > 0; i--) {
                tree[i] = merge(tree[i<<1], tree[i<<1|1]);
            }
        }

        public int query(int left, int right, long k) {
            int cnt = 0;
            left += n;
            right += n;

            while (left < right) {
                if((left & 1) == 1) {
                    int contribution = tree[left].size() - upperBound(tree[left], k);
                    cnt += contribution;
                    left++;
                }

                if((right & 1) == 1) {
                    right--;
                    int contribution = tree[right].size() - upperBound(tree[right], k);
                    cnt += contribution;
                }
                
                left >>= 1;
                right >>= 1;
            }

            return cnt;
        }


        private int upperBound(ArrayList<Long> list, long k) {
            if (list.isEmpty()) return 0;
            int left = 0, right = list.size();
            while (left < right) {
                int mid = (left+right)/2;
                if(list.get(mid) <= k) {
                    left = mid+1;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        private ArrayList<Long> merge(ArrayList<Long> left, ArrayList<Long> right) {
            ArrayList<Long> result = new ArrayList<>();
            int l=0, r=0;
            int leftLen = left.size(), rightLen = right.size();

            while (l < leftLen && r < rightLen) {
                if(left.get(l) > right.get(r)) {
                    result.add(right.get(r));
                    r++;
                } else {
                    result.add(left.get(l));
                    l++;
                }
            }

            while (l < leftLen) {
                result.add(left.get(l));
                l++;
            }

            while (r < rightLen) {
                result.add(right.get(r));
                r++;
            }

            return result;
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
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            sb.append(segTree.query(l, r, k)).append('\n');
        }

        System.out.print(sb);
    }
}

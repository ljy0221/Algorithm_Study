import java.io.*;
import java.util.*;

public class Main {
    static class SegmentTree {
        private int n;        // 원본 배열 크기
        private long[] tree;  // 세그먼트 트리 배열 (크기: 2n)

        public SegmentTree(long[] arr) {
            n = arr.length;           // 원본 배열 크기 저장
            tree = new long[2 * n];   // 트리 배열 생성 (크기: 2n)

            // 리프 노드에 원본 배열 복사 (인덱스 n부터 시작)
            for (int i = 0; i < n; i++) {
                tree[n + i] = arr[i];
            }

            // 내부 노드 계산 (bottom-up 방식)
            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[i << 1] + tree[i << 1 | 1];
            }
        }

        public long query(int l, int r) {
            long sum = 0;    // 결과 합계 저장 변수
            l += n;          // 쿼리 시작점을 트리 인덱스로 변환
            r += n;          // 쿼리 끝점을 트리 인덱스로 변환

            while (l < r) {
                // 왼쪽 경계: l이 "오른쪽 자식"이면 부모로 못 올라감 → 현재 처리
                if ((l & 1) == 1) sum += tree[l++];
                
                // 오른쪽 경계: r이 "오른쪽 자식"이면 범위 초과 → 왼쪽으로 당기고 처리  
                if ((r & 1) == 1) sum += tree[--r];
                
                // 둘 다 "왼쪽 자식"이 되면 부모 레벨로 이동 가능
                l >>= 1;
                r >>= 1;
                }
            return sum;
        }

        public void update(int idx, long val) {
            idx += n;        // 배열 인덱스를 트리 인덱스로 변환
            tree[idx] = val; // 해당 리프 노드 값 업데이트

            // 루트까지 올라가며 부모 노드들 업데이트
            while (idx > 1) {
                idx >>= 1;   // 부모 노드로 이동 (idx /= 2)
                // 부모 = 왼쪽 자식 + 오른쪽 자식
                tree[idx] = tree[idx << 1] + tree[idx << 1 | 1];
            }
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segT = new SegmentTree(arr);
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            switch (a) {
                case 1:
                    segT.update(b-1, c);
                    break;
                case 2: 
                    long ans = segT.query(b-1, (int)c);
                    System.out.println(ans);
                    break;
                default:
                    break;
            }
        }
    }
}

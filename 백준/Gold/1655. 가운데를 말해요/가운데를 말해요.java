import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N;
    // maxHeap: 작은 값들의 집합 (최댓값이 top)
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    // minHeap: 큰 값들의 집합 (최솟값이 top)
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    /*
     * 전략:
     * - maxHeap의 크기를 minHeap보다 같거나 1 크게 유지
     * - maxHeap의 최댓값 <= minHeap의 최솟값 유지
     * - 중앙값은 항상 maxHeap의 top
     */

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int in = Integer.parseInt(br.readLine());

            // 1. 일단 maxHeap에 추가
            if (maxHeap.size() == minHeap.size()) {
                maxHeap.offer(in);
            } else {
                minHeap.offer(in);
            }

            // 2. maxHeap의 top이 minHeap의 top보다 크면 교환
            if (!maxHeap.isEmpty() && !minHeap.isEmpty() 
                && maxHeap.peek() > minHeap.peek()) {
                int maxVal = maxHeap.poll();
                int minVal = minHeap.poll();
                maxHeap.offer(minVal);
                minHeap.offer(maxVal);
            }

            // 3. 중앙값 출력 (항상 maxHeap의 top)
            sb.append(maxHeap.peek()).append('\n');
        }

        System.out.print(sb);
    }
}

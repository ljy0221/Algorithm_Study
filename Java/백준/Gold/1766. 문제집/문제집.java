import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 그래프와 진입차수 배열 초기화
        List<Integer>[] graph = new ArrayList[N + 1];
        int[] indegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {  // i < M으로 수정
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);  // u -> v로 간선 추가
            indegree[v]++;    // v의 진입차수 증가
        }

        // 위상 정렬을 위한 우선순위 큐 (번호가 작은 순서대로)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 진입차수가 0인 노드들을 우선순위 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        StringBuilder result = new StringBuilder();

        // 위상 정렬 수행
        while (!pq.isEmpty()) {
            int current = pq.poll();
            result.append(current).append(" ");

            // 현재 노드와 연결된 노드들의 진입차수 감소
            for (int neighbor : graph[current]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    pq.offer(neighbor);
                }
            }
        }

        System.out.println(result.toString().trim());
    }
}
import java.io.*;
import java.util.*;

public class Main {

	/*
	 * 1. 모든 노드를 2분할 2. 해당 분할이 가능한지 확인 3. 가능하면 양쪽 인구수 계산 4. 차이를 확인해 값 갱신 5. 불가능하면
	 * pass
	 * 
	 * 노드를 분할하는 메소드->모든 조합을 다 검사? 연결이 되어있나 확인하는 메소드 각 연결된 노드의 인구수를 계산하는 메소드
	 */
	static int N;
	static int[][] graph;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int[] populations = new int[N];
		int result = Integer.MAX_VALUE;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			populations[i] = Integer.parseInt(st.nextToken());
		}

		graph = new int[N + 1][];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int edges = Integer.parseInt(st.nextToken());

			graph[i] = new int[edges];
			for (int j = 0; j < edges; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int mask = 1; mask < (1 << N) - 1; mask++) {
			// 그룹 나누기
			List<Integer> group1 = new ArrayList<>();
			List<Integer> group2 = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				if ((mask & (1 << i)) != 0) {
					group1.add(i + 1);
				} else {
					group2.add(i + 1);
				}
			}

			// 연결성 확인
			if (isConnected(group1) && isConnected(group2)) {
				// 인구수 계산
				int sum1 = 0, sum2 = 0;
				for (int val : group1)
					sum1 += populations[val - 1];
				for (int val : group2)
					sum2 += populations[val - 1];

				result = Math.min(result, Math.abs(sum1 - sum2));
			}
		}

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	static boolean isConnected(List<Integer> group) {
		if (group.isEmpty())
			return false;

		boolean[] visited = new boolean[N + 1]; // 1-based
		Queue<Integer> queue = new LinkedList<>();

		queue.add(group.get(0));
		visited[group.get(0)] = true;
		int visitedCount = 1;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int neighbor : graph[current - 1]) {
				if (group.contains(neighbor) && !visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
					visitedCount++;
				}
			}
		}

		return visitedCount == group.size();
	}

}

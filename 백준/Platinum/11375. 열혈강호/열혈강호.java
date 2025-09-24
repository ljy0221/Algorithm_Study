import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n,m;
    static int[] match; //연결 '당하는' 노드의 연결정보
    static boolean[] visited;   // 각 노드별로 연결 가능한 모든 요소 탐색
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        match = new int[m+1];
        Arrays.fill(match, -1);

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            visited = new boolean[m+1]; // 각 노드별로 연결했을 모든 경우를 살펴야 하기 때문에 여기서 초기화
            if(dfs(i)) ans++;
        }

        System.out.println(ans);
    }

    private static boolean dfs(int i) {
        for (Integer next : graph.get(i)) { // 연결된 모든 노드에서
            if(visited[next]) continue; // 이미 확인했으면 패스
            visited[next] = true;   // 확인 표시

            if(match[next] == -1 || dfs(match[next])) { // 아직 연결되지 않았거나, 다른 연결성을 찾을 수 있으면
                match[next] = i;    // 내가 연결
                return true;
            }
        }
        return false;
    }
}

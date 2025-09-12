import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    static int[] size;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        size = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // DFS로 각 노드의 서브트리 크기 계산
        dfs(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            sb.append(size[query]).append('\n');
        }
        
        System.out.print(sb);
    }

    static int dfs(int node) {
        visited[node] = true;
        size[node] = 1; // 자기 자신 포함
        
        for (int child : graph.get(node)) {
            if (!visited[child]) {
                size[node] += dfs(child);
            }
        }
        
        return size[node];
    }
}
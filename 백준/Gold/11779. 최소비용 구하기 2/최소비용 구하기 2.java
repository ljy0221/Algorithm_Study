import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Node>> grpah = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            grpah.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            grpah.get(u).add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dist = new int[n+1];
        int[] parent = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.to;
            int w = cur.w;

            if(dist[u] < w) continue;

            for (Node nextNode : grpah.get(u)) {
                int v = nextNode.to;
                int weight = nextNode.w;

                if(w + weight < dist[v]) {
                    dist[v] = w +weight;
                    parent[v] = u;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }

        sb.append(dist[end]).append('\n');

        Stack<Integer> path = new Stack<>();
        int current = end;
        while (current != 0) {
            path.push(current);
            if (current == start) break;
            current = parent[current];
        }

        sb.append(path.size()).append("\n");

        while (!path.isEmpty()) {
            sb.append(path.pop()).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    private static class Node implements Comparable<Node>{
        int to, w;

        public Node(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }

    }
}

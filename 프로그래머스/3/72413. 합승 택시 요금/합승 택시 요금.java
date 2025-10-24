import java.util.*;

class Solution {
    private class Node implements Comparable<Node> {
        int v, w;
        
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
        
        @Override
        public int compareTo (Node o) {
            return Integer.compare(this.w, o.w);
        }
    }
    
    int n;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        this.n = n;
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] fare : fares) {
            int u = fare[0];
            int v = fare[1];
            int w = fare[2];
            
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        
        int[] fromA = dijkstra(a, graph);
        int[] fromB = dijkstra(b, graph);
        int[] fromS = dijkstra(s, graph);
        
        for(int i = 1; i <= n; i++) {
            int dist = fromA[i] + fromB[i] + fromS[i];
            
            answer = Math.min(answer, dist);
        }
        
        
        return answer;
    }
    
    private int[] dijkstra(int start, List<List<Node>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.v;
            int w = cur.w;
            
            if(dist[u] < w) continue;
            
            for(Node next : graph.get(u)) {
                if(dist[u] + next.w < dist[next.v]) {
                    dist[next.v] = dist[u] + next.w;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
            
        }
        
        return dist;
    }
}
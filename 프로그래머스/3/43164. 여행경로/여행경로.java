import java.util.*;

class Solution {
    private Map<String, PriorityQueue<String>> graph = new HashMap<>();
    
    private LinkedList<String> route = new LinkedList<>();
    
    private int totalTickets;

    public String[] solution(String[][] tickets) {
        
        totalTickets = tickets.length;
        
        for (String[] ticket : tickets) {
            String from = ticket[0];
            String to = ticket[1];
            
            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).add(to);
        }
        
        dfs("ICN");

        return route.toArray(new String[0]);
    }

    private void dfs(String current) {
        PriorityQueue<String> destinations = graph.get(current);
        
        while (destinations != null && !destinations.isEmpty()) {
            String next = destinations.poll(); 
            dfs(next);
        }
        
        route.addFirst(current); 
    }
}
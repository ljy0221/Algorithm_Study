import java.io.*;
import java.util.*;

public class Main {
    static int N, removeNode;
    static List<List<Integer>> children;
    static int root;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        children = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            children.add(new ArrayList<>());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            
            if (parent == -1) {
                root = i;
            } else {
                children.get(parent).add(i);
            }
        }
        
        removeNode = Integer.parseInt(br.readLine());
        
        // 제거할 노드가 루트인 경우 리프 노드는 0개
        if (removeNode == root) {
            System.out.println(0);
        } else {
            System.out.println(countLeaves(root));
        }
    }
    
    static int countLeaves(int node) {
        if (node == removeNode) return 0;
        
        int leafCount = 0;
        int validChildCount = 0;
        
        for (int child : children.get(node)) {
            if (child != removeNode) {
                validChildCount++;
                leafCount += countLeaves(child);
            }
        }
        
        // 유효한 자식이 없으면 현재 노드가 리프 노드
        return (validChildCount == 0) ? 1 : leafCount;
    }
}
import java.util.*;
import java.io.*;

// 이진 트리 노드 클래스
class TreeNode {
    char data;
    TreeNode left;
    TreeNode right;
    
    public TreeNode(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class Main {
    
    // 전위 순회 (루트 -> 왼쪽 -> 오른쪽)
    public static void preorder(TreeNode node, StringBuilder result) {
        if (node != null) {
            result.append(node.data);
            preorder(node.left, result);
            preorder(node.right, result);
        }
    }
    
    // 중위 순회 (왼쪽 -> 루트 -> 오른쪽)
    public static void inorder(TreeNode node, StringBuilder result) {
        if (node != null) {
            inorder(node.left, result);
            result.append(node.data);
            inorder(node.right, result);
        }
    }
    
    // 후위 순회 (왼쪽 -> 오른쪽 -> 루트)
    public static void postorder(TreeNode node, StringBuilder result) {
        if (node != null) {
            postorder(node.left, result);
            postorder(node.right, result);
            result.append(node.data);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 노드 개수 입력
        int n = Integer.parseInt(br.readLine());
        
        // 노드들을 저장할 HashMap
        Map<Character, TreeNode> nodes = new HashMap<>();
        
        // 각 노드 정보 입력 및 노드 생성
        for (int i = 0; i < n; i++) {
            String[] nodeInfo = br.readLine().split(" ");
            char parent = nodeInfo[0].charAt(0);
            char leftChild = nodeInfo[1].equals(".") ? '\0' : nodeInfo[1].charAt(0);
            char rightChild = nodeInfo[2].equals(".") ? '\0' : nodeInfo[2].charAt(0);
            
            // 부모 노드가 없으면 생성
            if (!nodes.containsKey(parent)) {
                nodes.put(parent, new TreeNode(parent));
            }
            
            // 왼쪽 자식 노드 연결
            if (leftChild != '\0') {
                if (!nodes.containsKey(leftChild)) {
                    nodes.put(leftChild, new TreeNode(leftChild));
                }
                nodes.get(parent).left = nodes.get(leftChild);
            }
            
            // 오른쪽 자식 노드 연결
            if (rightChild != '\0') {
                if (!nodes.containsKey(rightChild)) {
                    nodes.put(rightChild, new TreeNode(rightChild));
                }
                nodes.get(parent).right = nodes.get(rightChild);
            }
        }
        
        // 루트 노드는 항상 'A'
        TreeNode root = nodes.get('A');
        
        // 각 순회 결과 저장할 StringBuilder
        StringBuilder preorderResult = new StringBuilder();
        StringBuilder inorderResult = new StringBuilder();
        StringBuilder postorderResult = new StringBuilder();
        
        // 순회 실행
        preorder(root, preorderResult);
        inorder(root, inorderResult);
        postorder(root, postorderResult);
        
        // 결과 출력
        System.out.println(preorderResult.toString());
        System.out.println(inorderResult.toString());
        System.out.println(postorderResult.toString());
        
        br.close();
    }
}
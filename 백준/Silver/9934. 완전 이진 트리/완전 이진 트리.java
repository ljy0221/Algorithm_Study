import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static int[] inorder;
    static List<List<Integer>> levels;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        K = Integer.parseInt(br.readLine());
        int n = (1 << K) - 1; // 2^K - 1 (완전 이진 트리의 노드 개수)
        
        inorder = new int[n];
        levels = new ArrayList<>();
        
        // 각 레벨별 리스트 초기화
        for (int i = 0; i < K; i++) {
            levels.add(new ArrayList<>());
        }
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            inorder[i] = Integer.parseInt(st.nextToken());
        }
        
        // 재귀적으로 트리 구성 (중위 순회 결과로부터)
        buildTree(0, n - 1, 0);
        
        // 각 레벨별 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < levels.get(i).size(); j++) {
                sb.append(levels.get(i).get(j));
                if (j < levels.get(i).size() - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
    
    // left: 시작 인덱스, right: 끝 인덱스, level: 현재 레벨
    static void buildTree(int left, int right, int level) {
        if (left > right) return;
        
        // 완전 이진 트리에서 중위 순회의 중간 노드가 루트
        int mid = (left + right) / 2;
        
        // 현재 레벨에 루트 노드 추가
        levels.get(level).add(inorder[mid]);
        
        // 왼쪽 서브트리와 오른쪽 서브트리 재귀 처리
        buildTree(left, mid - 1, level + 1);     // 왼쪽 서브트리
        buildTree(mid + 1, right, level + 1);    // 오른쪽 서브트리
    }
}
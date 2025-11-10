import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<Integer> preOrderList = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    /**
     * 전위 순회 결과를 이용하여 후위 순회 결과를 출력하는 재귀 함수
     * @param start 현재 서브트리의 시작 인덱스 (루트)
     * @param end 현재 서브트리의 끝 인덱스
     */
    public static void postOrder(int start, int end) {
        // start가 end보다 크면 노드가 없으므로 종료
        if (start > end) {
            return;
        }

        // 현재 서브트리의 루트 노드 (전위 순회의 첫 번째)
        int rootValue = preOrderList.get(start);
        
        // 오른쪽 서브트리의 시작 위치를 찾기 위한 인덱스
        // 초기값은 서브트리의 끝 다음 인덱스. 즉, 오른쪽 서브트리가 없는 경우
        int rightSubtreeStart = end + 1;

        // start+1부터 end까지 탐색하며 루트 값보다 큰(오른쪽 서브트리) 노드의 시작점을 찾는다.
        for (int i = start + 1; i <= end; i++) {
            if (preOrderList.get(i) > rootValue) {
                rightSubtreeStart = i; // 오른쪽 서브트리의 시작 인덱스
                break;
            }
        }

        // 1. 왼쪽 서브트리 후위 순회 (start+1부터 rightSubtreeStart-1까지)
        // 왼쪽 서브트리는 루트(start) 다음부터 오른쪽 서브트리 시작 전까지
        postOrder(start + 1, rightSubtreeStart - 1);

        // 2. 오른쪽 서브트리 후위 순회 (rightSubtreeStart부터 end까지)
        // 오른쪽 서브트리는 rightSubtreeStart부터 끝까지
        postOrder(rightSubtreeStart, end);

        // 3. 루트 노드 출력 (후위 순회의 마지막)
        sb.append(rootValue).append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력이 몇 개 들어올지 모르므로 EOF 처리 (null이 반환될 때까지)
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            preOrderList.add(Integer.parseInt(line));
        }

        // 전위 순회 결과 배열 전체에 대해 후위 순회 시작
        // 인덱스는 0부터 list.size() - 1까지
        if (!preOrderList.isEmpty()) {
            postOrder(0, preOrderList.size() - 1);
        }

        System.out.print(sb.toString());
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        // 1. 테스트 케이스 수 T 입력
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            // 2. 연산 개수 k 입력
            int k = Integer.parseInt(br.readLine());
            
            // TreeMap: 키(정수 값)를 오름차순으로 자동 정렬하며, 값(개수)을 저장
            TreeMap<Integer, Integer> tm = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String operation = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (operation.equals("I")) {
                    // I (삽입): n을 삽입하고 개수 1 증가
                    tm.put(n, tm.getOrDefault(n, 0) + 1);
                    
                } else if (operation.equals("D")) {
                    // D (삭제) 연산 시 큐가 비어있으면 무시
                    if (tm.isEmpty()) {
                        continue;
                    }
                    
                    int keyToDelete;
                    if (n == 1) { // 최댓값 삭제
                        keyToDelete = tm.lastKey();
                    } else { // n == -1, 최솟값 삭제
                        keyToDelete = tm.firstKey();
                    }
                    
                    // 삭제할 키의 현재 개수를 가져옴
                    int count = tm.get(keyToDelete);
                    
                    if (count == 1) {
                        // 개수가 1개라면 완전히 제거
                        tm.remove(keyToDelete);
                    } else {
                        // 개수가 2개 이상이라면 개수만 1 감소
                        tm.put(keyToDelete, count - 1);
                    }
                }
            }

            // 3. 최종 결과 출력
            if (tm.isEmpty()) {
                sb.append("EMPTY").append('\n');
            } else {
                sb.append(tm.lastKey()).append(" ").append(tm.firstKey()).append('\n');
            }
        }

        System.out.print(sb);
    }
}
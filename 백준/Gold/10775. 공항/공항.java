import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int G = Integer.parseInt(br.readLine()); // 게이트 수
        int P = Integer.parseInt(br.readLine()); // 비행기 수
        
        // parent 배열 초기화
        parent = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parent[i] = i; // 처음엔 자기 자신을 가리킴
        }
        
        int result = 0; // 도킹 성공한 비행기 수
        
        for (int i = 0; i < P; i++) {
            int gi = Integer.parseInt(br.readLine());
            
            // gi 이하에서 사용 가능한 가장 큰 게이트 찾기
            int availableGate = find(gi);
            
            if (availableGate == 0) {
                // 사용 가능한 게이트가 없음 → 공항 폐쇄
                break;
            }
            
            // availableGate번 게이트에 도킹 성공!
            result++;
            
            // 핵심: 이 게이트는 이제 사용됐으므로 
            // 다음번엔 (availableGate-1)번을 시도하도록 설정
            parent[availableGate] = availableGate - 1;
        }
        
        System.out.println(result);
    }
    
    // find: x번 이하에서 사용 가능한 가장 큰 게이트 번호 반환
    private static int find(int x) {
        if (parent[x] == x) {
            return x; // x번 게이트가 사용 가능
        }
        
        // Path Compression: 경로 압축으로 최적화
        return parent[x] = find(parent[x]);
    }
}
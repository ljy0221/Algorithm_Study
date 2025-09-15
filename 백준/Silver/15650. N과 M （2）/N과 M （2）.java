import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] selected;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
        
        // 1부터 시작하는 조합
        recursive(0, 1);
    }
    
    private static void recursive(int idx, int start) {
        // M개를 모두 선택했으면 출력
        if(idx == M) {
            for (int j = 0; j < M; j++) {
                System.out.print(selected[j] + " ");
            }
            System.out.println();
            return;
        }

        // start부터 N까지 선택 (오름차순 보장)
        for(int i = start; i <= N; i++) {
            selected[idx] = i;
            recursive(idx + 1, i + 1);  // 다음에는 i+1부터 선택
        }
    }
}
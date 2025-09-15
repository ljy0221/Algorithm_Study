import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
        
        // 1부터 시작하는 중복순열
        recursive(0);

        System.out.print(sb.toString());
    }
    
    private static void recursive(int idx) {
        // M개를 모두 선택했으면 출력
        if(idx == M) {
            for (int j = 0; j < M; j++) {
                sb.append(selected[j]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++) {
            selected[idx] = i;
            recursive(idx + 1);  // 다음에는 i+1부터 선택
        }
    }
}
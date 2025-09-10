import java.util.*;
import java.io.*;

public class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            
            boolean[][] reach = new boolean[N + 1][N + 1];

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                reach[a][b] = true;
            }
            
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (reach[i][k] && reach[k][j]) {
                            reach[i][j] = true;
                        }
                    }
                }
            }
            
            int answer = 0;
            
            // 각 학생별로 정확한 순위를 알 수 있는지 확인
            for (int i = 1; i <= N; i++) {
                int smallerCount = 0; // i보다 키가 작은 학생 수
                int biggerCount = 0;  // i보다 키가 큰 학생 수
                
                for (int j = 1; j <= N; j++) {
                    if (i != j) {
                        if (reach[j][i]) {
                            smallerCount++;
                        }
                        if (reach[i][j]) {
                            biggerCount++;
                        }
                    }
                }

                // 자신의 순위 = (자신보다 작은 학생 수) + 1
                if (smallerCount + biggerCount == N - 1) {
                    answer++;
                }
            }
            
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}

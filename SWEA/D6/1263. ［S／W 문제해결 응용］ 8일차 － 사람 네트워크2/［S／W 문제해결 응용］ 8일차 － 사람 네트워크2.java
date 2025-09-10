import java.io.*;
import java.util.*;

class Solution {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] graph = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (i != j && graph[i][k] != 0 && graph[k][j] != 0) {
                            if (graph[i][j] == 0) {
                                graph[i][j] = graph[i][k] + graph[k][j];
                            } else {
                                graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                            }
                        }
                    }
                }
            }

            int minCC = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int cc = 0;
                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        cc += graph[i][j];
                    }
                }
                minCC = Math.min(minCC, cc);
            }

            sb.append("#").append(tc).append(" ").append(minCC).append('\n');
        }

        System.out.print(sb);
    }
}
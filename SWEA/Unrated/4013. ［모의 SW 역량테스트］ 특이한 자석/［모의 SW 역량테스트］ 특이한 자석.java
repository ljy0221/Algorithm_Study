import java.util.*;
import java.io.*;

public class Solution {
    static int[][] magnets;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int K = Integer.parseInt(br.readLine());
            magnets = new int[4][8];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    magnets[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int target = Integer.parseInt(st.nextToken())-1;
                int rotateDir = Integer.parseInt(st.nextToken());

                int[] rotation = new int[4];

                rotation[target] = rotateDir;

                // left
                for (int j = target; j > 0; j--) {
                    if(magnets[j][6] != magnets[j-1][2]) rotation[j-1] = -rotation[j];
                    else break;
                }

                // right
                for (int j = target; j < 3; j++) {
                    if(magnets[j][2] != magnets[j+1][6]) rotation[j+1] = -rotation[j];
                    else break;
                }

                for (int j = 0; j < 4; j++) {
                    if(rotation[j] != 0)
                        rotate(j, rotation[j]);
                }
            }

            int ans = 0;

            int[] score = {1, 2, 4, 8};

            for (int i = 0; i < 4; i++) {
                if(magnets[i][0] == 1) ans += 1<<i;
            }

            sb.append("#").append(tc).append(" ").append(ans).append('\n');
        }

        System.out.print(sb);
    }

    private static void rotate(int target, int dir) {
        int[] magnet = magnets[target];

        if(dir == -1) {
            int tmp = magnet[0];
            for (int i = 0; i < 7; i++) {
                magnet[i] = magnet[i+1];
            }
            magnet[7] = tmp;
        } else {
            int tmp = magnet[7];
            for (int i = 7; i > 0; i--) {
                magnet[i] = magnet[i-1];
            }
            magnet[0] = tmp;
        }
    }
}

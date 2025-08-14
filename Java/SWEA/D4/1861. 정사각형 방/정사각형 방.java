import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] rooms = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    rooms[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int startRoom = Integer.MAX_VALUE, maxMove = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int currentMove = move(rooms, i, j);
                    
                    if (currentMove > maxMove || (currentMove == maxMove && rooms[i][j] < startRoom)) {
                        maxMove = currentMove;
                        startRoom = rooms[i][j];
                    }
                }
            }

            System.out.printf("#%d %d %d\n", tc, startRoom, maxMove);
        }
    }

    private static int move(int[][] rooms, int r, int c) {
        int n = rooms.length;
        int maxCount = 1;  // 현재 방 포함
        
        for (int dir = 0; dir < 4; dir++) {
            int nr = r + dr[dir];  // dir 사용!
            int nc = c + dc[dir];
            
            if (nr >= 0 && nr < n && nc >= 0 && nc < n && 
                rooms[nr][nc] == rooms[r][c] + 1) {  // 정확히 1 큰 방
                maxCount = Math.max(maxCount, 1 + move(rooms, nr, nc));
            }
        }
        
        return maxCount;
    }

    
}

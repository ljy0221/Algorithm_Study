import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class State {
        int x, y, keys, moves;
        
        State(int x, int y, int keys, int moves) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.moves = moves;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        char[][] maze = new char[N][M];
        int startX = 0, startY = 0;
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == '0') {
                    startX = i;
                    startY = j;
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<State> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][64];
        
        q.offer(new State(startX, startY, 0, 0));
        visited[startX][startY][0] = true;
        
        while (!q.isEmpty()) {
            State current = q.poll();
            int x = current.x;
            int y = current.y;
            int keys = current.keys;
            int moves = current.moves;
            
            if (maze[x][y] == '1') {
                System.out.println(moves);
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // out of bounds
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                
                // wall
                if (maze[nx][ny] == '#') {
                    continue;
                }
                
                // copy key
                int newKeys = keys;
                
                // key(a~f)
                if (maze[nx][ny] >= 'a' && maze[nx][ny] <= 'f') {
                    int keyBit = maze[nx][ny] - 'a';
                    // | -> duplicate doesn't matter
                    newKeys |= (1 << keyBit);
                }
                // door(A~F)
                else if (maze[nx][ny] >= 'A' && maze[nx][ny] <= 'F') {
                    int keyBit = maze[nx][ny] - 'A';
                    // if no key to match door
                    if ((keys & (1 << keyBit)) == 0) {
                        continue;
                    }
                }
                
                // cycle check
                if (visited[nx][ny][newKeys]) {
                    continue;
                }
                
                visited[nx][ny][newKeys] = true;
                q.offer(new State(nx, ny, newKeys, moves + 1));
            }
        }
        
        // not reachable
        System.out.println(-1);
    }
}
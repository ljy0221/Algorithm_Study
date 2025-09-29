import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int[][] dir = new int[][] {
        {-1,0}, {0,1}, {1,0}, {0,-1}
    };

    static int T, w, h;
    static char[][] map;
    static int keyMask;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            for (int i = 0; i < h; i++) {
                String line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = line.charAt(j);
                }
            }

            keyMask = 0;
            String in = br.readLine();
            if (!in.equals("0")) {
                for (int i = 0; i < in.length(); i++) {
                    getKey(in.charAt(i));
                }
            }

            int result = search();
            sb.append(result).append('\n');
        }
        System.out.print(sb);
    }

    private static int search() {
        int cnt = 0;
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h][w];
        List<int[]>[] doorQueues = new ArrayList[26];
        
        for (int i = 0; i < 26; i++) {
            doorQueues[i] = new ArrayList<>();
        }

        // 테두리의 모든 진입 가능한 칸을 시작점으로 추가
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h-1 || j == 0 || j == w-1) {
                    if (map[i][j] != '*') {
                        if (canEnter(i, j, doorQueues)) {
                            q.offer(new int[] {i, j});
                            visited[i][j] = true;
                            if (map[i][j] == '$') cnt++;
                            else if ('a' <= map[i][j] && map[i][j] <= 'z') {
                                int keyIdx = map[i][j] - 'a';
                                getKey(map[i][j]);
                                // 이 열쇠로 열 수 있는 문들 처리
                                for (int[] door : doorQueues[keyIdx]) {
                                    if (!visited[door[0]][door[1]]) {
                                        visited[door[0]][door[1]] = true;
                                        q.offer(door);
                                        if (map[door[0]][door[1]] == '$') cnt++;
                                    }
                                }
                                doorQueues[keyIdx].clear();
                            }
                        }
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dir[i][0];
                int nc = c + dir[i][1];

                if (!isValid(nr, nc) || visited[nr][nc]) continue;

                if (canEnter(nr, nc, doorQueues)) {
                    visited[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                    
                    if (map[nr][nc] == '$') {
                        cnt++;
                    } else if ('a' <= map[nr][nc] && map[nr][nc] <= 'z') {
                        getKey(map[nr][nc]);
                        // 이 열쇠로 열 수 있는 문들 처리
                        int keyIdx = map[nr][nc] - 'a';
                        for (int[] door : doorQueues[keyIdx]) {
                            if (!visited[door[0]][door[1]]) {
                                visited[door[0]][door[1]] = true;
                                q.offer(door);
                                if (map[door[0]][door[1]] == '$') cnt++;
                            }
                        }
                        doorQueues[keyIdx].clear();
                    }
                }
            }
        }

        return cnt;
    }

    private static boolean canEnter(int r, int c, List<int[]>[] doorQueues) {
        char cell = map[r][c];
        if (cell == '*') return false;
        if (cell == '.' || cell == '$') return true;
        if ('a' <= cell && cell <= 'z') return true;
        if ('A' <= cell && cell <= 'Z') {
            if (isMatch(cell)) {
                return true;
            } else {
                doorQueues[cell - 'A'].add(new int[] {r, c});
                return false;
            }
        }
        return true;
    }

    private static boolean isValid(int nr, int nc) {
        return nr >= 0 && nr < h && nc >= 0 && nc < w;
    }

    private static void getKey(char key) {
        keyMask = keyMask | (1 << (key - 'a'));
    }

    private static boolean isMatch(char door) {
        int d = door - 'A';
        return (keyMask & (1 << d)) != 0;
    }
}
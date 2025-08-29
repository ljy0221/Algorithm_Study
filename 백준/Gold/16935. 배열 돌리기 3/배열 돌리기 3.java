import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (R-- > 0) {
            int op = Integer.parseInt(st.nextToken());
            switch (op) {
                case 1:
                    map = reverseUpDown(map);
                    break;
                case 2:
                    map = reverseLeftRight(map);
                    break;
                case 3:
                    map = rotate90Right(map);
                    break;
                case 4:
                    map = rotate90Left(map);
                    break;
                case 5:
                    map = clockwork(map);
                    break;
                case 6:
                    map = reverseClock(map);
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] reverseClock(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] newMap = new int[n][m];

        // sect1
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
                newMap[i+n/2][j] = map[i][j];
            }
        }
        // sect2
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
                newMap[i][j] = map[i][j+m/2];
            }
        }
        // sect3
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
                newMap[i][j+m/2] = map[i+n/2][j+m/2];
            }
        }
        // sect4
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
            newMap[i+n/2][j+m/2] = map[i+n/2][j];
            }
        }

        return newMap;
    }

    private static int[][] clockwork(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] newMap = new int[n][m];

        // sect1
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
                newMap[i][j+m/2] = map[i][j];
            }
        }
        // sect2
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
                newMap[i+n/2][j+m/2] = map[i][j+m/2];
            }
        }
        // sect3
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
                newMap[i+n/2][j] = map[i+n/2][j+m/2];
            }
        }
        // sect4
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < m/2; j++) {
            newMap[i][j] = map[i+n/2][j];
            }
        }

        return newMap;
    }

    private static int[][] rotate90Left(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] newMap = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[j][i] = map[i][m-1-j];
            }
        }

        return newMap;
    }

    private static int[][] rotate90Right(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] newMap = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[j][i] = map[n-1-i][j];
            }
        }


        return newMap;
    }

    private static int[][] reverseLeftRight(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] newMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[i][m-1-j];
            }
        }

        return newMap;
    }

    private static int[][] reverseUpDown(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] newMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[n-1-i][j];
            }
        }

        return newMap;
    }
}

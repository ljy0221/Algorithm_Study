import java.io.*;
import java.util.*;

public class Solution {
    static class Germ {
        int row, col, size, dir;

        public Germ(int row, int col, int size, int dir) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /*
         * 1. 움직인다
         * 2. 약품구역이면 절반만 살고 방향은 반대로 바뀐다.
         * 3. 겹치면 합쳐진다(방향은 가장 큰놈으로)
         * 
         * return 전체 수
         */

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            List<Germ> germList = new ArrayList<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int row = Integer.parseInt(st.nextToken());
                int col = Integer.parseInt(st.nextToken());
                int size = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                Germ newGerm = new Germ(row, col, size, dir);
                germList.add(newGerm);
            }
            
            while (M-- > 0) {
                List<Germ>[][] map = new List[N][N];
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        map[i][j] = new ArrayList<>();
                    }
                }
                
                // move
                for (Germ germ : germList) {
                    switch (germ.dir) {
                        case 1: // 상
                            if (--germ.row == 0) {
                                germ.size /= 2;
                                germ.dir = 2;
                            }
                            break;
                        case 2: // 하
                            if (++germ.row == N - 1) {
                                germ.size /= 2;
                                germ.dir = 1;
                            }
                            break;
                        case 3: // 좌
                            if (--germ.col == 0) {
                                germ.size /= 2;
                                germ.dir = 4;
                            }
                            break;
                        case 4: // 우
                            if (++germ.col == N - 1) {
                                germ.size /= 2;
                                germ.dir = 3;
                            }
                            break;
                    }

                    map[germ.row][germ.col].add(germ);
                }

                // merge
                germList.clear();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        List<Germ> curr = map[i][j];
                        if (curr.size() > 0) {
                            if (curr.size() == 1) {
                                germList.add(curr.get(0));
                            } else {
                                Germ largest = curr.get(0);
                                int totalSize = 0;
                                
                                for (Germ g : curr) {
                                    totalSize += g.size;
                                    if (g.size > largest.size) {
                                        largest = g;
                                    }
                                }

                                Germ merged = new Germ(i, j, totalSize, largest.dir);
                                germList.add(merged);
                            }
                        }
                    }
                }
            }

            int ans = 0;
            for (Germ g : germList) {
                ans += g.size;
            }

            System.out.printf("#%d %d\n", tc, ans);
        }
    }
}
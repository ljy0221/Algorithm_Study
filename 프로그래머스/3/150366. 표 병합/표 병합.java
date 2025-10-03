import java.io.*;
import java.util.*;

public class Solution {

    static class UnionFind {
        int[] parent;

        UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootY] = rootX;
            }
        }

        void reset(int x) {
            parent[x] = x;
        }
    }

    public String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();
        StringTokenizer st;
        String[][] cells = new String[51][51];
        UnionFind uf = new UnionFind(2601); // 51*51

        for (String command : commands) {
            st = new StringTokenizer(command);

            switch (st.nextToken()) {
                case "UPDATE":
                    if (st.countTokens() == 3) {
                        int r = Integer.parseInt(st.nextToken());
                        int c = Integer.parseInt(st.nextToken());
                        String value = st.nextToken();

                        int idx = r * 51 + c;
                        int root = uf.find(idx);
                        int rootR = root / 51;
                        int rootC = root % 51;
                        cells[rootR][rootC] = value;

                    } else if (st.countTokens() == 2) {
                        String value1 = st.nextToken();
                        String value2 = st.nextToken();

                        for (int i = 1; i <= 50; i++) {
                            for (int j = 1; j <= 50; j++) {
                                if (cells[i][j] != null && cells[i][j].equals(value1)) {
                                    cells[i][j] = value2;
                                }
                            }
                        }
                    }
                    break;
                case "MERGE": {
                    int r1 = Integer.parseInt(st.nextToken());
                    int c1 = Integer.parseInt(st.nextToken());
                    int r2 = Integer.parseInt(st.nextToken());
                    int c2 = Integer.parseInt(st.nextToken());

                    if (r1 == r2 && c1 == c2)
                        break;

                    int idx1 = r1 * 51 + c1;
                    int idx2 = r2 * 51 + c2;
                    int root1 = uf.find(idx1);
                    int root2 = uf.find(idx2);

                    if (root1 == root2)
                        break;

                    int rootR1 = root1 / 51;
                    int rootC1 = root1 % 51;
                    int rootR2 = root2 / 51;
                    int rootC2 = root2 % 51;

                    String value = cells[rootR1][rootC1] != null ? cells[rootR1][rootC1] : cells[rootR2][rootC2];

                    uf.union(idx1, idx2);

                    int newRoot = uf.find(idx1);
                    int newRootR = newRoot / 51;
                    int newRootC = newRoot % 51;

                    cells[rootR1][rootC1] = null;
                    cells[rootR2][rootC2] = null;
                    cells[newRootR][newRootC] = value;
                }
                    break;
                case "UNMERGE": {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    int idx = r * 51 + c;
                    int root = uf.find(idx);
                    int rootR = root / 51;
                    int rootC = root % 51;

                    String value = cells[rootR][rootC];

                    List<Integer> toUnmerge = new ArrayList<>();
                    for (int i = 1; i <= 50; i++) {
                        for (int j = 1; j <= 50; j++) {
                            int tempIdx = i * 51 + j;
                            if (uf.find(tempIdx) == root) {
                                toUnmerge.add(tempIdx);
                            }
                        }
                    }

                    for (int tempIdx : toUnmerge) {
                        uf.reset(tempIdx);
                        int tempR = tempIdx / 51;
                        int tempC = tempIdx % 51;
                        cells[tempR][tempC] = null;
                    }

                    cells[r][c] = value;
                }
                    break;
                case "PRINT": {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());

                    int idx = r * 51 + c;
                    int root = uf.find(idx);
                    int rootR = root / 51;
                    int rootC = root % 51;

                    if (cells[rootR][rootC] == null) {
                        answer.add("EMPTY");
                    } else {
                        answer.add(cells[rootR][rootC]);
                    }
                }
                    break;
            }
        }

        return answer.toArray(new String[0]);
    }
}
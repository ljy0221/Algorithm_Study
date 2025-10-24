import java.util.*;

class Solution {
    int[][] board;
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, 1, 0, -1};
    
    private class Pos {
        int r, c;
        public Pos(int r, int c) { this.r = r; this.c = c; }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return r == pos.r && c == pos.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public int solution(int[][] board, int r, int c) {
        this.board = board; 
        
        TreeMap<Integer, List<Pos>> pair = new TreeMap<>();
        int cardCount = 0;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] != 0) {
                    pair.computeIfAbsent(board[i][j], k -> new ArrayList<>()).add(new Pos(i, j));
                    cardCount++;
                }
            }
        }
        
        return dfs(r, c, 0, cardCount / 2, pair);
    }
    
    private int dfs(int r, int c, int result, int remainingCards, TreeMap<Integer, List<Pos>> pair) {
        if (remainingCards == 0) {
            return result;
        }

        int min = Integer.MAX_VALUE;
        List<Integer> keys = new ArrayList<>(pair.keySet());
        
        for (int key : keys) {
            List<Pos> cards = pair.get(key);
            Pos card1 = cards.get(0);
            Pos card2 = cards.get(1);
            
            // 1. 첫 번째 순서: Card1 -> Card2
            int dist1 = bfs(r, c, card1.r, card1.c) + bfs(card1.r, card1.c, card2.r, card2.c) + 2;
            
            // 2. 두 번째 순서: Card2 -> Card1
            int dist2 = bfs(r, c, card2.r, card2.c) + bfs(card2.r, card2.c, card1.r, card1.c) + 2;
            
            board[card1.r][card1.c] = 0;
            board[card2.r][card2.c] = 0;
            pair.remove(key);
            
            // 경로 1 재귀: card2 위치에서 다음 탐색 시작
            min = Math.min(min, dfs(card2.r, card2.c, result + dist1, remainingCards - 1, pair));
            // 경로 2 재귀: card1 위치에서 다음 탐색 시작
            min = Math.min(min, dfs(card1.r, card1.c, result + dist2, remainingCards - 1, pair));
            
            // 백트래킹: 상태 복구
            board[card1.r][card1.c] = key;
            board[card2.r][card2.c] = key;
            pair.put(key, cards);
        }
        
        return min;
    }
    
    private int bfs(int r1, int c1, int r2, int c2) {
        if(r1 == r2 && c1 == c2) return 0;
        
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][4];
        
        q.offer(new int[] {r1, c1, 0});
        visited[r1][c1] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];
            
            int newDist = dist + 1;
            
            for(int i = 0; i < 4; i++) {
                // 1. 일반 화살표 이동
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(nr >= 0 && nr < 4 && nc >= 0 && nc < 4) {
                    if (nr == r2 && nc == c2) return newDist;
                    
                    if(!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        q.offer(new int[] {nr, nc, newDist});
                    }
                }

                // 2. Ctrl + 화살표 이동
                int cr = r;
                int cc = c;
                boolean moved = false;
                
                while(true) {
                    int next_cr = cr + dr[i];
                    int next_cc = cc + dc[i];
                    
                    if(next_cr < 0 || next_cr >= 4 || next_cc < 0 || next_cc >= 4) {
                        break;
                    }
                    
                    cr = next_cr;
                    cc = next_cc;
                    moved = true;

                    if (board[cr][cc] != 0) {
                        break;
                    }
                }
                
                if (moved) {
                    if (cr == r2 && cc == c2) return newDist;

                    if (!visited[cr][cc]) {
                        visited[cr][cc] = true;
                        q.offer(new int[] {cr, cc, newDist});
                    }
                }
            }
        }
        return Integer.MAX_VALUE; 
    }
}
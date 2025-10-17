import java.util.*;

class Solution {
    
    // 좌표 저장 클래스
    private static class Pos {
        int r, c;
        
        private Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
        
        @Override
        public boolean equals(Object o) { // 좌표 비교를 위한 equals 오버라이드
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return r == pos.r && c == pos.c;
        }

        @Override
        public int hashCode() { // HashMap 사용을 위한 hashCode 오버라이드
            return Objects.hash(r, c);
        }
    }
    
    // 상하좌우 이동 방향
    private static int[][] direction = {
        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    
    public int solution(int[][] game_board, int[][] table) {
        
        List<List<Pos>> blanks = new ArrayList<>();
        List<List<Pos>> blocks = new ArrayList<>();
        
        // 빈칸 찾기 (game_board에서 0인 셀)
        for (int i = 0; i < game_board.length; i++) {
            for (int j = 0; j < game_board[0].length; j++) {
                if(game_board[i][j] == 0) {
                    // 찾은 빈칸 조각을 표준화하여 저장
                    blanks.add(normalize(findBlock(i, j, game_board, true)));
                }
            }
        }
        
        // 블럭찾기 (table에서 1인 셀)
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if(table[i][j] == 1) {
                    // 찾은 블록 조각을 표준화하여 저장
                    blocks.add(normalize(findBlock(i, j, table, false)));
                }
            }
        }
        
        int totalArea = 0;
        boolean[] usedBlocks = new boolean[blocks.size()];

        // 3. 매칭 및 면적 계산 (블록을 회전시키며 빈칸과 비교)
        for (List<Pos> blank : blanks) {
            for (int i = 0; i < blocks.size(); i++) {
                if (usedBlocks[i]) continue;
                
                List<Pos> currentBlock = blocks.get(i);
                List<Pos> rotatedBlock = currentBlock; // 0도 회전

                for (int rot = 0; rot < 4; rot++) {
                    // 크기 같고, 표준화된 모양(좌표 목록)이 같은지 확인
                    // List의 equals는 요소의 순서가 같을 때만 true를 반환하며, normalize에서 정렬했기 때문에 모양이 같으면 true
                    if (blank.size() == rotatedBlock.size() && blank.equals(rotatedBlock)) {
                        totalArea += blank.size();
                        usedBlocks[i] = true; // 블록 사용 처리
                        break; // 현재 빈칸에 매칭되는 블록을 찾았으므로 다음 빈칸으로 넘어감
                    }
                    
                    // 다음 90도 회전
                    rotatedBlock = rotate(rotatedBlock);
                }
                
                if (usedBlocks[i]) break; // 현재 빈칸에 매칭되는 블록을 찾았으면 다음 빈칸 검사
            }
        }
        
        // 최종적으로 채워진 블록의 총 면적을 반환
        return totalArea; 
    }
    
    // BFS를 이용해 인접한 조각을 찾고, 조각을 추출한 후에는 보드 값을 변경하여 중복 추출 방지
    private List<Pos> findBlock(int r, int c, int[][] board, boolean isBlank) {
        List<Pos> block = new ArrayList<>();
        
        Queue<Pos> q = new ArrayDeque<>();
        
        // 시작 지점 처리
        Pos start = new Pos(r, c);
        q.offer(start);
        block.add(start);
        
        // 중복 탐색 방지: 빈칸(0)은 1로, 블록(1)은 0으로 바꿔서 재탐색을 막음
        board[r][c] = isBlank ? 1 : 0; 
        
        while(!q.isEmpty()) {
            Pos curr = q.poll();
            int cr = curr.r;
            int cc = curr.c;
            
            for (int i = 0; i < 4; i++) {
                int nr = cr + direction[i][0];
                int nc = cc + direction[i][1];
                
                // 맵 경계 체크
                if(0 <= nr && nr < board.length && 0 <= nc && nc < board[0].length) {
                    
                    // 빈칸을 찾는 경우: 인접 칸이 0 (빈칸)
                    // 블록을 찾는 경우: 인접 칸이 1 (블록)
                    int targetValue = isBlank ? 0 : 1; 
                    
                    if (board[nr][nc] == targetValue) {
                        Pos next = new Pos(nr, nc);
                        
                        // 💡 중복 탐색 방지: 다음 칸의 값을 변경
                        board[nr][nc] = isBlank ? 1 : 0; 
                        
                        block.add(next);
                        q.offer(next);
                    }
                }
            }
        }
        
        return block;
    }
    
    // 조각의 좌표 목록을 표준화 (최소 행/열을 (0,0)으로 이동 후 정렬)
    private List<Pos> normalize(List<Pos> block) {
        if (block.isEmpty()) return block;

        // 최소 행/열 찾기
        int minR = block.stream().mapToInt(p -> p.r).min().getAsInt();
        int minC = block.stream().mapToInt(p -> p.c).min().getAsInt();

        // 상대 좌표로 변환
        List<Pos> normalized = new ArrayList<>();
        for (Pos p : block) {
            normalized.add(new Pos(p.r - minR, p.c - minC));
        }

        // 행 우선, 열 우선으로 정렬 (고유한 모양 지문을 만듦)
        Collections.sort(normalized, (a, b) -> {
            if (a.r != b.r) return a.r - b.r;
            return a.c - b.c;
        });

        return normalized;
    }
    
    // 표준화된 조각을 90도 시계 방향으로 회전 후 다시 표준화
    private List<Pos> rotate(List<Pos> block) {
        List<Pos> rotated = new ArrayList<>();

        // 90도 시계 방향 회전 공식: (r, c) -> (c, -r)
        for (Pos p : block) {
            int newR = p.c;
            int newC = -p.r; 
            rotated.add(new Pos(newR, newC));
        }

        // 회전 후 음수 좌표 등을 처리하기 위해 다시 표준화
        return normalize(rotated);
    }
}
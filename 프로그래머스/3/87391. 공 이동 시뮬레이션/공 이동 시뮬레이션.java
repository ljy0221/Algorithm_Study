class Solution {

    public long solution(int n, int m, int x, int y, int[][] queries) {
        // 도착지점 (x, y)에서부터 가능한 시작점 범위를 초기화
        long minX = x, maxX = x, minY = y, maxY = y;

        // 쿼리를 역순으로 순회하며 시작 가능한 범위 역추적
        for (int i = queries.length - 1; i >= 0; i--) {
            int mode = queries[i][0];
            int move = queries[i][1];

            switch (mode) {
            case 0:  // 좌로 dx칸 이동 → 역방향은 우측으로 확장
                minY = (minY == 0) ? 0 : minY + move;  // 범위가 0에 걸려 있으면 확장 X
                maxY = Math.min(maxY + move, m - 1);   // 우측 확장은 최대 m-1까지
                break;

            case 1:  // 우로 dx칸 이동 → 역방향은 좌측으로 확장
                minY = Math.max(minY - move, 0);       // 좌측으로 확장 (0 이상)
                maxY = (maxY == m - 1) ? m - 1 : maxY - move;  // 끝 벽에 붙어있으면 이동 X
                break;

            case 2:  // 상으로 dx칸 이동 → 역방향은 하단으로 확장
                minX = (minX == 0) ? 0 : minX + move;
                maxX = Math.min(maxX + move, n - 1);
                break;

            case 3:  // 하로 dx칸 이동 → 역방향은 상단으로 확장
                minX = Math.max(minX - move, 0);
                maxX = (maxX == n - 1) ? n - 1 : maxX - move;
                break;
            }

            // 유효 범위를 벗어나는 경우 도달 불가능
            if (minX > maxX || minY > maxY) return 0;
        }

        // 가능한 시작점 개수 = 범위의 넓이
        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}
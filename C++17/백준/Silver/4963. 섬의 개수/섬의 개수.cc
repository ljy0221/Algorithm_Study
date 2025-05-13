#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <string.h>
 
int w = 0, h = 0;
 
int visit[51][51] = { -1, };
 
int map[51][51] = { 0, };
 
int dy[8] = { -1, 0, 1, 0, 1, -1, 1, -1};
int dx[8] = { 0, -1, 0, 1, 1, 1, -1, -1};
 
void DFS(int r, int c)
{
    visit[r][c] = 1; // 방문 표시(true)
 
    for (int i = 0; i < 8; i++)
    {
        int y = r + dy[i];
        int x = c + dx[i];
 
        if (0 <= y && y < h && 0 <= x && x < w && map[y][x] == 1 && visit[y][x] == -1)
        {
            DFS(y, x);
        }
    }
}
 
int main(void)
{
    int icnt = 0, scnt = 0;
 
    while (1)
    {
        memset(visit, -1, sizeof(visit));
        memset(map, 0, sizeof(map));
 
        scanf("%d %d", &w, &h);
 
        if (w == 0 && h == 0) break;
 
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                scanf("%d", &map[i][j]);
            }
        }
 
        int result = 0;
 
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                if (map[i][j] == 1 && visit[i][j] == -1) // 땅(1)이고, 방문하지 않은 곳이라면
                {
                    DFS(i, j);
                    ++result;
                }
            }
        }
 
        printf("%d\n", result);
    }
 
    return 0;
}

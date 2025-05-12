#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M, K;
vector<vector<int>> board, sumW, sumB;

int min(int a, int b) { return a < b ? a : b; }

void calculateSum(vector<vector<int>>& sum, char start) {
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            char expected = ((i + j) % 2 == 0) ? start : (start == 'W' ? 'B' : 'W');
            int value = (board[i][j] == expected) ? 0 : 1;
            sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + value;
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M >> K;
    board.resize(N+1, vector<int>(M+1));
    sumW.resize(N+1, vector<int>(M+1, 0));
    sumB.resize(N+1, vector<int>(M+1, 0));

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            char c;
            cin >> c;
            board[i][j] = c;
        }
    }

    calculateSum(sumW, 'W');
    calculateSum(sumB, 'B');

    int result = N * M;
    for (int i = K; i <= N; i++) {
        for (int j = K; j <= M; j++) {
            int white = sumW[i][j] - sumW[i-K][j] - sumW[i][j-K] + sumW[i-K][j-K];
            int black = sumB[i][j] - sumB[i-K][j] - sumB[i][j-K] + sumB[i-K][j-K];
            result = min(result, min(white, black));
        }
    }

    cout << result << '\n';
    return 0;
}
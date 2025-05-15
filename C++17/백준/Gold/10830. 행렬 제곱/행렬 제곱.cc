#include <iostream>
#include <vector>

using namespace std;

typedef vector<vector<int>> matrix;
const int MOD = 1000;

int N;
long long B;

matrix multiply(const matrix &a, const matrix &b) {
    matrix result(N, vector<int>(N, 0));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                result[i][j] = (result[i][j] + 1LL * a[i][k] * b[k][j]) % MOD;
            }
        }
    }
    return result;
}

matrix power(matrix a, long long b) {
    matrix result(N, vector<int>(N, 0));
    for (int i = 0; i < N; i++) {
        result[i][i] = 1;
    }
    
    while (b > 0) {
        if (b & 1) {
            result = multiply(result, a);
        }
        a = multiply(a, a);
        b >>= 1;
    }
    
    return result;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    cin >> N >> B;
    
    matrix A(N, vector<int>(N));
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> A[i][j];
        }
    }
    
    matrix result = power(A, B);
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout << result[i][j] % MOD << " ";
        }
        cout << "\n";
    }
    
    return 0;
}
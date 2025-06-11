#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int N, M;
    cin >> N >> M;
    
    vector<int> vip(M);
    for (int i = 0; i < M; i++) {
        cin >> vip[i];
    }
    
    // 연속된 좌석 수에 따른 경우의 수 계산 (피보나치)
    vector<long long> fib(N + 1);
    fib[0] = 1;
    if (N >= 1) fib[1] = 1;
    for (int i = 2; i <= N; i++) {
        fib[i] = fib[i-1] + fib[i-2];
    }
    
    // VIP 좌석으로 구간 나누기
    vector<int> sections;
    int start = 1;
    
    for (int i = 0; i < M; i++) {
        if (vip[i] > start) {
            sections.push_back(vip[i] - start);
        }
        start = vip[i] + 1;
    }
    
    // 마지막 구간
    if (start <= N) {
        sections.push_back(N - start + 1);
    }
    
    // 각 구간의 경우의 수를 곱함
    long long result = 1;
    for (int section : sections) {
        result *= fib[section];
    }
    
    cout << result << endl;
    
    return 0;
}
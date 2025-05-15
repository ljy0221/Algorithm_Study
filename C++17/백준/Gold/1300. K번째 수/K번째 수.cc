#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

long long N, k;

// x보다 작거나 같은 값의 개수를 계산하는 함수
long long countLessOrEqual(long long x) {
    long long count = 0;
    
    // 각 행 i에 대해, i*j <= x를 만족하는 j의 개수를 계산
    for (long long i = 1; i <= N; i++) {
        // i*j <= x에서 j <= x/i
        // 단, j는 1부터 N까지만 가능
        count += min(N, x / i);
    }
    
    return count;
}

int main() {
    // input
    cin >> N;
    cin >> k;
    
    // 이분 탐색을 위한 범위 설정
    long long left = 1;        // 최솟값: 1*1 = 1
    long long right = N * N;   // 최댓값: N*N
    long long result = 0;
    
    // 매개변수 탐색 (이분 탐색)
    while (left <= right) {
        long long mid = (left + right) / 2;
        
        // mid보다 작거나 같은 값의 개수
        long long count = countLessOrEqual(mid);
        
        if (count >= k) {
            // mid보다 작거나 같은 값이 k개 이상이면
            // k번째 값은 mid 이하에 있음
            result = mid;
            right = mid - 1;
        } else {
            // mid보다 작거나 같은 값이 k개 미만이면
            // k번째 값은 mid보다 큼
            left = mid + 1;
        }
    }
    
    // output
    cout << result << '\n';
    
    return 0;
}
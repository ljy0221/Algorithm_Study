#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


int main() {
    int N, S;
    // input
    cin >> N;
    vector<int> A(N); // init

    for(int i = 0; i < N; i++) {
        cin >> A[i];
    }

    cin >> S;

    // process
    int change = 0;
    for(int i = 0; i < N && S > 0; i++) {
        // i번째 위치에 올 수 있는 가장 큰 값의 인덱스 찾기
        int max_idx = i;
        for(int j = i+1; j < N && j <= i + S; j++) {
            if(A[j] > A[max_idx]) {
                max_idx = j;
            }
        }

         // 최대값을 i번째 위치로 이동 (버블 정렬처럼 한 칸씩 이동)
        for (int j = max_idx; j > i; j--) {
            swap(A[j], A[j-1]);
        }
        
        // 교환 횟수 갱신
        S -= (max_idx - i);
    }

    for(auto a : A) {
        cout << a << ' ';
    }
    return 0;
}
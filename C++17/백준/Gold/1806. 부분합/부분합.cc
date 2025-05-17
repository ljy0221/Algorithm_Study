#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int N, S;
vector<int> sequence;


int main() {
    // input
    cin >> N >> S;
    sequence.resize(N);

    for(int i = 0; i < N; i++) {
        cin >> sequence[i];
    }

    // process
    int left = 0;
    int right = 0;
    int sum = 0;
    int length = INT_MAX;

    while(right < N) {
        sum +=sequence[right];

        while(sum >= S) {
            length = min(length, right - left + 1);
            sum -= sequence[left];
            left++;
        }
        right++;
    }
    
    // output
    if (length == INT_MAX) {
        cout << 0 << '\n';  // 합이 S 이상인 부분 수열이 없는 경우
    } else {
        cout << length << '\n';
    }
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N;
    cin >> N;
    vector<int> line(N);

    for(int i = 0; i < N; i++) {
        cin >> line[i];
    }

    vector<int> dp(N, 1);

    for(int i = 1; i < N; i++) {
        for(int j = 0; j < i; j++) {
            if(line[j] < line[i]) {
                dp[i] = max(dp[i], dp[j]+1);
            }
        }
    }

    int minimum_count = *max_element(dp.begin(), dp.end());
    
    cout << N - minimum_count << '\n';
    
    return 0;
}
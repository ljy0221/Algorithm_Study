#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    int N;
    cin >> N;
    
    vector<int> positive;  // 1보다 큰 양수들
    vector<int> negative;  // 음수들
    int zeros = 0;         // 0의 개수
    int ones = 0;          // 1의 개수
    
    for (int i = 0; i < N; i++) {
        int num;
        cin >> num;
        
        if (num > 1) {
            positive.push_back(num);
        } else if (num == 1) {
            ones++;
        } else if (num == 0) {
            zeros++;
        } else {  // num < 0
            negative.push_back(num);
        }
    }
    
    // 양수는 내림차순으로 정렬 (큰 수부터)
    sort(positive.begin(), positive.end(), greater<int>());
    
    // 음수는 오름차순으로 정렬 (작은 수부터, 즉 절댓값이 큰 수부터)
    sort(negative.begin(), negative.end());
    
    int sum = 0;
    
    // 양수들 처리 (1보다 큰 양수들은 가능한 큰 수끼리 짝지어 곱함)
    for (int i = 0; i < positive.size(); i += 2) {
        if (i + 1 < positive.size()) {
            // 두 수를 묶을 수 있으면 곱해서 더함
            sum += positive[i] * positive[i + 1];
        } else {
            // 남은 하나는 그냥 더함
            sum += positive[i];
        }
    }
    
    // 음수들 처리 (음수들은 가능한 작은 수(절댓값이 큰 수)끼리 짝지어 곱함)
    for (int i = 0; i < negative.size(); i += 2) {
        if (i + 1 < negative.size()) {
            // 두 음수를 묶을 수 있으면 곱해서 더함 (양수가 됨)
            sum += negative[i] * negative[i + 1];
        } else {
            // 남은 하나의 음수는 0이 있으면 0과 묶고(=0), 없으면 그냥 더함
            if (zeros > 0) {
                // 0과 묶어서 상쇄 (더하면 0이므로 아무것도 하지 않음)
            } else {
                sum += negative[i];
            }
        }
    }
    
    // 1은 곱하는 것보다 더하는 게 이득이므로 그냥 더함
    sum += ones;
    
    cout << sum << endl;
    
    return 0;
}
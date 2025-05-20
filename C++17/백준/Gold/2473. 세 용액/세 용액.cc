#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int main() {
    int N;
    cin >> N;
    vector<long long> fluids(N);
    for(int i = 0; i < N; i++) {
        cin >> fluids[i];
    }

    sort(fluids.begin(), fluids.end());

    long long closest_sum = LLONG_MAX;
    int ans_i = 0, ans_j = 0, ans_k = 0;
    
    for(int i = 0; i < N - 2; i++) {
        int left = i + 1;
        int right = N - 1;
        
        while(left < right) {
            long long sum = fluids[i] + fluids[left] + fluids[right];
            
            // 현재까지 찾은 가장 0에 가까운 합보다 더 가까운 경우
            if(abs(sum) < abs(closest_sum)) {
                closest_sum = sum;
                ans_i = i;
                ans_j = left;
                ans_k = right;
            }
            
            if(sum > 0) {
                right--;
            } else if(sum < 0) {
                left++;
            } else {
                // 합이 정확히 0인 경우, 이보다 더 좋은 답은 없으므로 바로 종료
                cout << fluids[i] << ' ' << fluids[left] << ' ' << fluids[right] << endl;
                return 0;
            }
        }
    }
    
    // 가장 0에 가까운 세 용액 출력
    cout << fluids[ans_i] << ' ' << fluids[ans_j] << ' ' << fluids[ans_k] << endl;
    return 0;
}
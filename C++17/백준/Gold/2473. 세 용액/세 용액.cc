// 투포인터를 이용한 방법

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
/*
이분탐색을 이용한 방법
#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <climits>
using namespace std;

int main() {
    int N;
    cin >> N;
    
    vector<long long> solutions(N);
    for(int i = 0; i < N; i++) {
        cin >> solutions[i];
    }
    
    sort(solutions.begin(), solutions.end());
    
    long long closest_sum = LLONG_MAX;
    vector<long long> answer(3);
    
    // 두 개의 용액을 고정
    for(int i = 0; i < N-2; i++) {
        for(int j = i+1; j < N-1; j++) {
            // 세 번째 용액을 이분 탐색으로 찾기
            long long target = -(solutions[i] + solutions[j]); // 합이 0이 되는 값
            
            // lower_bound를 사용하여 target 이상의 첫 번째 원소의 위치 찾기
            int idx = lower_bound(solutions.begin() + j + 1, solutions.end(), target) - solutions.begin();
            
            // target보다 큰 값 확인 (idx가 범위 내에 있을 경우)
            if(idx < N) {
                long long sum = solutions[i] + solutions[j] + solutions[idx];
                if(abs(sum) < abs(closest_sum)) {
                    closest_sum = sum;
                    answer = {solutions[i], solutions[j], solutions[idx]};
                }
            }
            
            // target보다 작은 값 확인 (idx-1이 j보다 클 경우)
            if(idx > j+1) {
                long long sum = solutions[i] + solutions[j] + solutions[idx-1];
                if(abs(sum) < abs(closest_sum)) {
                    closest_sum = sum;
                    answer = {solutions[i], solutions[j], solutions[idx-1]};
                }
            }
        }
    }
    
    // 결과 출력 (이미 오름차순으로 정렬되어 있음)
    cout << answer[0] << " " << answer[1] << " " << answer[2] << endl;
    
    return 0;
}
*/

#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <climits>

using namespace std;

vector<int> fluids;
int N;

int main() {
    // input 
    cin >> N;
    fluids.resize(N);

    // process
    for(int i = 0; i < N; i++) {
        int fluid;
        cin >> fluids[i];
    }

    sort(fluids.begin(), fluids.end());
    
    long long min_diff = LLONG_MAX;  // 0에 가장 가까운 값을 찾기 위한 변수
    pair<int, int> answer;  // 결과를 저장할 변수
    
    for (int i = 0; i < N - 1; i++) {
        // 현재 용액의 음수값과 가장 가까운 값을 찾기 위한 이분탐색
        int target = -fluids[i];
        
        int left = i + 1;  // 현재 용액 다음부터 탐색
        int right = N - 1;
        
        // 이분탐색
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // 현재 혼합값 계산
            long long mixed = (long long)fluids[i] + fluids[mid];
            
            // 현재 혼합의 절대값이 지금까지의 최소값보다 작다면 갱신
            if (abs(mixed) < min_diff) {
                min_diff = abs(mixed);
                answer = {fluids[i], fluids[mid]};
            }
            
            // 이분탐색 진행
            if (mixed < 0) {
                left = mid + 1;
            } 
            else if (mixed > 0) {
                right = mid - 1;
            } 
            else {  // 혼합값이 0이면 더 이상 탐색할 필요 없음
                break;
            }
        }
    }
    
    // output
    cout << answer.first << " " << answer.second << '\n';
    return 0;
}
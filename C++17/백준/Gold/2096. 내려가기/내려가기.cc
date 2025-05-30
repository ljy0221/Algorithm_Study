#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <tuple>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N;
    cin >> N;
    
    // 현재 행과 이전 행만 저장 (공간 복잡도 O(1))
    vector<tuple<int, int>> prev_row(3), curr_row(3);
    
    // 첫 번째 행 초기화
    for(int j = 0; j < 3; j++) {
        int val;
        cin >> val;
        prev_row[j] = make_tuple(val, val); // (min, max)
    }
    
    // 각 위치에서 갈 수 있는 다음 위치들 정의
    vector<vector<int>> next_positions = {
        {0, 1},     // 0번 위치에서 갈 수 있는 곳: 0, 1
        {0, 1, 2},  // 1번 위치에서 갈 수 있는 곳: 0, 1, 2  
        {1, 2}      // 2번 위치에서 갈 수 있는 곳: 1, 2
    };
    
    for(int i = 1; i < N; i++) {
        vector<int> row(3);
        for(int j = 0; j < 3; j++) {
            cin >> row[j];
        }
        
        // 각 열에 대해 최솟값과 최댓값 계산
        for(int j = 0; j < 3; j++) {
            int min_val = INT_MAX;
            int max_val = INT_MIN;
            
            // 이전 행에서 현재 위치로 올 수 있는 모든 위치 확인
            for(int prev_j = 0; prev_j < 3; prev_j++) {
                // prev_j에서 j로 갈 수 있는지 확인
                bool can_reach = false;
                for(int next_pos : next_positions[prev_j]) {
                    if(next_pos == j) {
                        can_reach = true;
                        break;
                    }
                }
                
                if(can_reach) {
                    auto [prev_min, prev_max] = prev_row[prev_j];
                    min_val = min(min_val, prev_min);
                    max_val = max(max_val, prev_max);
                }
            }
            
            curr_row[j] = make_tuple(min_val + row[j], max_val + row[j]);
        }
        
        // 현재 행을 이전 행으로 업데이트
        prev_row = curr_row;
    }
    
    // 마지막 행에서 전체 최솟값과 최댓값 찾기
    int final_min = INT_MAX;
    int final_max = INT_MIN;
    
    for(int j = 0; j < 3; j++) {
        auto [min_val, max_val] = prev_row[j];
        final_min = min(final_min, min_val);
        final_max = max(final_max, max_val);
    }
    
    cout << final_max << ' ' << final_min << '\n';
    
    return 0;
}
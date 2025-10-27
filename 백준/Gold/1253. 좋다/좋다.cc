#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N;
    cin >> N;
    
    // 값과 원래 인덱스를 함께 저장
    vector<pair<long long, int>> indexedNumbers(N);
    for (int i = 0; i < N; i++) {
        cin >> indexedNumbers[i].first;  // 값
        indexedNumbers[i].second = i;    // 원래 인덱스
    }
    
    // 값을 기준으로 정렬
    sort(indexedNumbers.begin(), indexedNumbers.end());
    
    int goodCount = 0; // 좋은 수의 개수
    
    // 각 수에 대해 검사
    for (int k = 0; k < N; k++) {
        long long target = indexedNumbers[k].first; // 현재 확인하려는 수의 값
        int targetIndex = indexedNumbers[k].second; // 현재 확인하려는 수의 원래 인덱스
        
        // 투 포인터 설정
        int left = 0;
        int right = N - 1;
        bool isGood = false;
        
        while (left < right) {
            // 현재 두 수의 인덱스가 타겟의 인덱스와 같지 않은지 확인
            if (left == k) {
                left++;
                continue;
            }
            if (right == k) {
                right--;
                continue;
            }
            
            // 현재 합계 계산
            long long sum = indexedNumbers[left].first + indexedNumbers[right].first;
            
            // 합계가 타겟과 같은 경우
            if (sum == target) {
                isGood = true;
                break;
            } 
            // 합계가 타겟보다 작은 경우
            else if (sum < target) {
                left++;
            } 
            // 합계가 타겟보다 큰 경우
            else {
                right--;
            }
        }
        
        if (isGood) {
            goodCount++;
        }
    }
    
    cout << goodCount << endl;
    
    return 0;
}
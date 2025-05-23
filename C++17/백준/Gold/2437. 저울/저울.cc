#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    
    vector<int> weights(n);
    for(int i = 0; i < n; i++) {
        cin >> weights[i];
    }
    
    // 추의 무게를 오름차순으로 정렬
    sort(weights.begin(), weights.end());
    
    int maxMeasurable = 0;  // 현재까지 측정 가능한 최대 무게
    
    for(int i = 0; i < n; i++) {
        // 현재 추의 무게가 (maxMeasurable + 1)보다 크면
        // maxMeasurable + 1은 측정할 수 없는 최소 무게
        if(weights[i] > maxMeasurable + 1) {
            break;
        }
        
        // 현재 추를 추가하면 측정 가능한 범위 확장
        maxMeasurable += weights[i];
    }
    
    // 측정할 수 없는 최소 무게 출력
    cout << maxMeasurable + 1 << '\n';
    
    return 0;
}
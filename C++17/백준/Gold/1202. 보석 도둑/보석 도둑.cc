#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

struct Jewel {
    int weight, price;
    bool operator<(const Jewel& other) const {
        return weight < other.weight;  // 무게 오름차순
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, k;
    cin >> n >> k;
    
    vector<Jewel> jewels(n);
    for(int i = 0; i < n; i++) {
        cin >> jewels[i].weight >> jewels[i].price;
    }
    
    vector<int> bags(k);
    for(int i = 0; i < k; i++) {
        cin >> bags[i];
    }
    
    // 보석을 무게 오름차순으로 정렬
    sort(jewels.begin(), jewels.end());
    
    // 가방을 용량 오름차순으로 정렬  
    sort(bags.begin(), bags.end());
    
    priority_queue<int> pq; // 가격 최대힙
    long long totalPrice = 0;
    int jewelIdx = 0;
    
    // 각 가방에 대해 처리
    for(int i = 0; i < k; i++) {
        int bagCapacity = bags[i];
        
        // 현재 가방에 들어갈 수 있는 모든 보석을 우선순위 큐에 추가
        while(jewelIdx < n && jewels[jewelIdx].weight <= bagCapacity) {
            pq.push(jewels[jewelIdx].price);
            jewelIdx++;
        }
        
        // 가장 비싼 보석을 선택 (있다면)
        if(!pq.empty()) {
            totalPrice += pq.top();
            pq.pop();
        }
    }
    
    cout << totalPrice << '\n';
    
    return 0;
}
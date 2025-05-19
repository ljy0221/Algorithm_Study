#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>
#include <queue>

using namespace std;


int main() {
    int N;
    cin >> N;
    priority_queue<int, vector<int>, greater<int>> pq;

    for(int i = 0; i < N; i++) {
        int card;
        cin >> card;
        pq.push(card);
    }

    int total_sum = 0;
    while(pq.size() > 1) {
        int first = pq.top();
        pq.pop();
        int second = pq.top();
        pq.pop();

        int sum = first + second;
        total_sum += sum;
        pq.push(sum);
    }
    
    cout << total_sum;
    return 0;
}
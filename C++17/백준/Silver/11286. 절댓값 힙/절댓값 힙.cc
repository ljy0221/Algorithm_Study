#include <iostream>
#include <algorithm>
#include <queue>
#include <functional>
using namespace std;

struct cmp{ // 정렬 기준 바꾸는 함수
    bool operator()(int a, int b){
        if(abs(a) == abs(b))
            return a > b; // 절대값이 같은 경우 가장 작은 원소로
        return abs(a) > abs(b); // 절대값이 작은 원소로
    }
};

priority_queue<int, vector<int>, cmp> pq;
int main(int argc, char const *argv[])
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N;
    while (N--)
    {
        int x;
        cin >> x;
        if (x == 0) {
            if (pq.empty()) {
                cout << 0 << '\n';
            } else {
                cout << pq.top() << '\n';
                pq.pop();
            }
        } else {
            pq.push(x);
        }
    }

    return 0;
}
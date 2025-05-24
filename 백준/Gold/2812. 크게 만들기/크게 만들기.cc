#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N, K;
    cin >> N >> K;
    string number;
    cin >> number;
    
    vector<char> stack;
    int removed = 0;
    
    for (char digit : number) {
        // 현재 숫자보다 작은 앞의 숫자들을 제거
        while (!stack.empty() && stack.back() < digit && removed < K) {
            stack.pop_back();
            removed++;
        }
        stack.push_back(digit);
    }
    
    // 아직 K개를 다 제거하지 못한 경우 뒤에서부터 제거
    while (removed < K) {
        stack.pop_back();
        removed++;
    }
    
    // vector를 그대로 출력 (이미 올바른 순서)
    for (char c : stack) {
        cout << c;
    }
    cout << '\n';
    
    return 0;
}
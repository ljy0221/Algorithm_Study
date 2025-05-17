#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

bool isPalindrome(const string& txt, int left, int right) {
    while(left < right) {
        if(txt[left] != txt[right]) {
            return false;
        }
        left++;
        right--;
    }
    return true;
}

int checkString(const string& txt) {
    int left = 0;
    int right = txt.length() - 1;
    
    // 먼저 회문인지 확인
    while(left < right) {
        if(txt[left] != txt[right]) {
            // 불일치가 발견되면, 왼쪽 문자를 건너뛰거나 오른쪽 문자를 건너뛰었을 때
            // 회문이 되는지 확인
            if(isPalindrome(txt, left + 1, right) || isPalindrome(txt, left, right - 1)) {
                return 1; // 한 문자를 삭제하여 회문이 될 수 있으면 1 반환
            }
            return 2; // 한 문자를 삭제해도 회문이 될 수 없으면 2 반환
        }
        left++;
        right--;
    }
    
    return 0; // 이미 회문이면 0 반환
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int N;
    cin >> N;

    for(int i = 0; i < N; i++) {
        string text;
        cin >> text;

        cout << checkString(text) << '\n';
    }
   
    return 0;
}
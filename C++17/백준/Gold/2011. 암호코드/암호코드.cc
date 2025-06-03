#include <iostream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

int main() {
    string number;
    cin >> number;

    int len = number.length();

    if(number[0] == '0') {
        cout << 0 << endl;
        return 0;
    }
    
    vector<int> dp(len+1, 0);
    dp[0] = 1;
    dp[1] = 1;
    
    for(int i = 2; i <= len; i++) {
        if(number[i-1] >= '1' && number[i-1] <= '9') {
            dp[i] += dp[i-1];
            dp[i] %= 1000000;
        }

        int two_digit = (number[i-2] - '0') * 10 + (number[i-1] - '0');
        if(two_digit >= 10 && two_digit <= 26) {
            dp[i] += dp[i-2];
            dp[i] %= 1000000;
        }
    }


    cout << dp[len] << '\n';
    return 0;
}
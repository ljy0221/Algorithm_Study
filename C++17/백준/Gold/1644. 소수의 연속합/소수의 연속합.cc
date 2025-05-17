#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

int N;

vector<int> generatePrimes(int N) {
    vector<bool> isPrime(N+1, true);
    vector<int> primes;

    isPrime[0] = isPrime[1] = false;

    for(int i = 2; i*i <= N; i++) {
        if(isPrime[i]) {
            for(int j = i*i; j <= N; j += i) {
                isPrime[j] = false;
            }
        }
    }

    for(int i = 2; i <= N; i++) {
        if(isPrime[i]) {
            primes.push_back(i);
        }
    }
    
    return primes;
}

int main() {
    // input
    cin >> N;
    vector<int> prime_numbers = generatePrimes(N);

    // process
    int left = 0, right = 0, sum = 0, result = 0;

    if (prime_numbers.empty()) {
        cout << 0 << '\n';
        return 0;
    }
    
    sum = prime_numbers[0];
    
    while(right < prime_numbers.size()) {
        if(sum == N) {
            result++;

            right++;
            if(right < prime_numbers.size()) {
                sum += prime_numbers[right];
            }
        } else if (sum < N) {
            right++;
            if (right < prime_numbers.size()) {
                sum += prime_numbers[right];
            }
        } else {
            sum -= prime_numbers[left];
            left++;
        }
    }

    
    // output
    cout << result << '\n';
    return 0;
}
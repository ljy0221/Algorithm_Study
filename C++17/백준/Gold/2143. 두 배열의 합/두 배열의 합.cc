#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int main() {
    long long T;
    cin >> T;

    int n;
    cin >> n;
    vector<int> A(n);
    for(int i = 0; i < n; i++) {
        cin >> A[i];
    }

    int m;
    cin >> m;
    vector<int> B(m);
    for(int i = 0; i < m; i++) {
        cin >> B[i];
    }

    unordered_map<long long, int> subA;
    for(int i = 0; i < n; i++) {
        long long sum = 0;

        for(int j = i; j < n; j++) {
            sum += A[j];
            subA[sum]++;
        }
    }

    long long count = 0;
    for(int i = 0; i < m; i++) {
        long long sum = 0;

        for(int j = i; j < m; j++) {
            sum += B[j];
            if(subA.find(T-sum) != subA.end()) {
                count += subA[T-sum];
            }
        }
    }

    cout << count;
    
    return 0;
}
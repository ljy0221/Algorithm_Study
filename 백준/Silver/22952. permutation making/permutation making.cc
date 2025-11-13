#include <iostream>
#define ll long long
using namespace std;

int main(void)
{
    ios_base::sync_with_stdio(false); cin.tie(NULL);
    ll N, i;
    cin >> N;
    cout << N << ' ';
    for (i = 1; i < N / 2; i++) cout << i << ' ' << N - i << ' ';
    if (N == 1) return 0;
    cout << N / 2 << ' ';
    if (N % 2 == 1)  cout << N / 2 + 1;
}
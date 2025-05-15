#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, M;

int main(int argc, char const *argv[])
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N;
    vector<int> A(N);
    for (int i = 0; i < N; i++) {
        cin >> A.at(i);
    }
    sort(A.begin(), A.end());
    cin >> M;
    while (M--)
    {
        int target;
        cin >> target;
        cout << binary_search(A.begin(), A.end(), target) << '\n';
    }
    

    return 0;
}
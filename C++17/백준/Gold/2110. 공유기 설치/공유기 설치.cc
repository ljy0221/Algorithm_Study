#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int N, C;
vector<int> lengths;

bool check(long long len) {
    long long cnt = 1;
    long long last_point = lengths[0];
    for (int i = 1; i < N; i++) {
        if (lengths[i] - last_point >= len) {
            cnt++;
            last_point = lengths[i];
        } else 
            continue;
    }

    if (cnt >= C) {
        return true;
    } else 
        return false;
}

long long solve() {
    long long start = 1, end = lengths[N-1] - lengths[0];
    long long result = 0;

    while (start <= end)
    {
        long long mid = start + (end - start) / 2;

        if (check(mid)) {
            result = max (result, mid);
            start = mid + 1;
        } else {
            end = mid - 1;
        }
    }

    return result;
}

int main(int argc, char const *argv[])
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> C;
    lengths.resize(N);
    for (int i = 0; i < N; i++) {
        cin >> lengths.at(i);
    }
    sort(lengths.begin(), lengths.end());
    long long result = solve();

    cout << result << '\n';
    return 0;
}
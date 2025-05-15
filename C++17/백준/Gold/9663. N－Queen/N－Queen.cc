#include <iostream>
#include <vector>

using namespace std;

int N, result = 0;
vector<int> queens;

bool check(int location) {
    for (int i = 0; i < location; i++) {
        if (queens[i] == queens[location] || abs(queens[location] - queens[i]) == location - i) {
            return false;
        }
    }
    return true;
}

void nqueens(int location) {
    if(location == N) {
        result++;
        return;
    }

    for (int i = 0; i < N; i++)
    {
        queens[location] = i;
        if (check(location))
        {
            nqueens(location+1);
        }
    }
}

int main(int argc, char const *argv[]) {
    
    cin >> N;
    queens.resize(N);
    nqueens(0);

    cout << result;
    return 0;
}

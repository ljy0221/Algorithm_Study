#include <iostream>
#include <algorithm>
using namespace std;

void printStar(int, int, int);

int main(int argc, char const *argv[])
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    cin >> N; 

    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            printStar(N, i, j);
        }
        cout << '\n';
    }
    
    return 0;
}
void printStar(int N, int x, int y)
{
    if (N == 0)
    {
        return;
    }
    
    if ((x / N) % 3 == 1 && (y / N) % 3 == 1)
    {
        cout << ' ';
    }
    else
    {
        if (N == 1)
        {
            cout << '*';
        }
        else
            printStar(N/3, x, y);
    }
    
}
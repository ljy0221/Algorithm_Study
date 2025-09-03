#include <stdio.h>

int main()
{
    //make 1;
    int N;
    int make1[1000001] = { 0, };
    scanf("%d", &N);

    make1[1] = 0;
    for(int i = 2; i <= N; i++)
    {
        make1[i] = make1[i-1] + 1;

        if(i % 2 == 0)
        {
            if(make1[i] > make1[i/2] + 1)
            {
                make1[i] = make1[i/2] + 1;
            }
        }

        if(i % 3 == 0)
        {
            if(make1[i] > make1[i/3] + 1)
            {
                make1[i] = make1[i/3] + 1;
            }
        }
    }
    printf("%d\n", make1[N]);

    return 0;
}
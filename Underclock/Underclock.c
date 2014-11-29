#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void bubble_sort(float *, unsigned long long);

int main(int argc, const char * argv[])
{
    const unsigned long long length = 100000;
    srand48(time(NULL));
    
    float * buff = malloc(sizeof *buff * length);
    
    for (unsigned long long idx = 0; idx < length; idx++)
    {
        buff[idx] = (float)drand48() * 1000000;
    }
    
    bubble_sort(buff, length);
        
    for (unsigned long long idx = 0; idx < length; idx++)
    {
        printf("%f\n", buff[idx]);
    }
   
    return 0;
}

void bubble_sort(float * buff, unsigned long long size)
{
    for (unsigned long long top = size - 1; top > 0; --top)
    {
        for (unsigned long long idx = 0; idx < top; ++idx)
        {
            float i = *(buff + idx);
            float j = *(buff + idx + 1);
            
            if (i > j) {
                *(buff + idx) = j;
                *(buff + idx + 1) = i;
            }
        }
    }
}

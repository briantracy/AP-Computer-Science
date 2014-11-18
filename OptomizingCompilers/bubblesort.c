#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void bubble_sort(int *, size_t);


int 
main(int argc, const char ** argv)
{
	const int size = 100000;
	int *array = malloc(sizeof(int) * size);
	if (!array) printf("malloc error\n");

	srand(time(NULL));
	for (int i = 0; i < size; i++) {
		array[i] = rand() % 100;
	}

	bubble_sort(array, size);

	// for (int i = 0; i < size; i++) {
	// 	printf("%d  ", array[i]);
	// 	if (!(i%40)) printf("\n");
	// }

}




void
bubble_sort(int *in, size_t size)
{
	for (size_t top = size - 1; top > 0; top--)
	{
		for (int idx = 0; idx < top; idx++)
		{
			int i = *(in + idx);
			int j = *(in + idx + 1);

			if (i > j) {
				*(in + idx) = j;
				*(in + idx + 1) = i;
			}
		}
	}
}
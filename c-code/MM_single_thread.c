#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/types.h>
#include <unistd.h>
#include <stdint.h>
#include <inttypes.h>
#include <pthread.h>

int size;
unsigned long **matrix1;
unsigned long **matrix2;
unsigned long **resMatrix;

unsigned long random_int(int min, int max, int seed)
{
	srand( seed );
	return min + rand() % (max+1 - min);
}

void toString(unsigned long **array)
{
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
			printf("%lu ", array[i][j]);
		printf("\n");
	}

}

void *matrixMultiplication()
{
        for (int i = 0; i < size; i++)
        {
                for (int j = 0; j < size; j++)
                {
                        for (int k = 0; k < size; k++)
                                resMatrix[i][j] += matrix1[i][k]*matrix2[k][j];
                }
        }
}

int main()
{
	struct timespec start, end;
	int count;
	pthread_t *thread;
	printf("Input matrix size: ");
	scanf("%d", &size);
	matrix1 = malloc(size*sizeof(unsigned long *));
	matrix2 = malloc(size*sizeof(unsigned long *));
	resMatrix = malloc(size*sizeof(unsigned long *));

	for(int i = 0; i < size; i++)
	{
		matrix1[i] = malloc(size*sizeof(unsigned long));
		matrix2[i] = malloc(size*sizeof(unsigned long));
		resMatrix[i] = malloc(size*sizeof(unsigned long));
	}

	//need a seed for random numbers, because C is stupid
	for(int i = 0; i < size; i++)
	{
		for(int j = 0; j < size; j++)
		{
			matrix1[i][j] = random_int(0,1000, i+3*j+1);
			matrix2[i][j] = random_int(0,1000, 3*i+j+3);
		}
	}	
	thread = malloc(size*sizeof(pthread_t));
matrixMultiplication();
toString(matrix1);
printf("--------\n");
toString(matrix2);
printf("--------\n");
toString(resMatrix);


	clock_gettime(CLOCK_MONOTONIC, &start);	

for(int i = 0; i < 50; i++)
{	
	pthread_create(thread, NULL, matrixMultiplication, NULL);
	pthread_join(*thread, NULL);
}		
	clock_gettime(CLOCK_MONOTONIC, &end);
double seconds =  (double)end.tv_sec + 1.0e-9*end.tv_nsec - (double)start.tv_sec + 1.0e-9*start.tv_nsec;
	printf("Seconds: %.9f\n",seconds);
	printf("Average seconds: %.9f\n", seconds/50);
	


}

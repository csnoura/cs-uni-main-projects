#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <mpi.h>
#include <time.h>

// Method for generating the vectors of random numbers ranging from 0 to 1000
void generate_random_vector(double* vector, int array_size) {
    for (int i = 0; i < array_size; i++)
        vector[i] = (double)rand() / RAND_MAX ; 
}

int main(int argc, char* argv[]) {
    int n = atoi(argv[1]);
    int rank, num_of_process;
    double process_sum = 0.0;
    double total_sum = 0.0;
    double* a = NULL, * b = NULL;
    int* displs = NULL, * rcounts = NULL;
    int local_num_elements, remainder;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &num_of_process);

    // Calculating the number of elements each process will handle
    local_num_elements = n / num_of_process;
    remainder = n % num_of_process;
    if (rank < remainder) local_num_elements += 1;

    double* local_a = (double*)malloc(local_num_elements * sizeof(double));  // Allocate memory for vector a
    double* local_b = (double*)malloc(local_num_elements * sizeof(double)); // Allocate memory for vector b

    if (rank == 0) {
        a = (double*)malloc(n * sizeof(double));
        b = (double*)malloc(n * sizeof(double));
    }

    double total_time = 0.0; // To accumulate total time across runs

    // Run 10 times
    for (int run = 0; run < 10; run++) {
        double start_time = MPI_Wtime();

        // Each process generates its local random part of the vectors
        srand(time(NULL) + rank+1*run); // Seed the random number generator
        generate_random_vector(local_a, local_num_elements);
        generate_random_vector(local_b, local_num_elements);

        
        displs = (int*)malloc(num_of_process * sizeof(int));
        rcounts = (int*)malloc(num_of_process * sizeof(int));

        if (rank == 0) {
            int process_elements = 0;
            for (int i = 0; i < num_of_process; ++i) {
                rcounts[i] = (n / num_of_process) + (i < remainder ? 1 : 0);
                displs[i] = process_elements;
                process_elements += rcounts[i];
            }
        }
        // only to gather the local vectors to be in one n length vector in process 0
        MPI_Gatherv(local_a, local_num_elements, MPI_DOUBLE, a, rcounts, displs, MPI_DOUBLE, 0, MPI_COMM_WORLD);
        MPI_Gatherv(local_b, local_num_elements, MPI_DOUBLE, b, rcounts, displs, MPI_DOUBLE, 0, MPI_COMM_WORLD);

        // Compute local sum
        for (int i = 0; i < local_num_elements; i++) {
            double diff = local_a[i] - local_b[i];
            process_sum += diff * diff;
        }

        // Reduce results to process 0
        MPI_Reduce(&process_sum, &total_sum, 1, MPI_DOUBLE, MPI_SUM, 0, MPI_COMM_WORLD);

        // Compute the Euclidean Distance
        if (rank == 0) {
            double euclidean_distance = sqrt(total_sum);
            printf("\nRun %d:\n", run + 1);
            //print a small sample of the vector
            printf("a small sample of the vector :\n");
            for (int i = 0; i < 5; i++) {
                printf("a[%d] = %f, b[%d] = %f\n", i, a[i], i, b[i]);
            }
            printf("Euclidean distance: %f\n", euclidean_distance);
            double end_time = MPI_Wtime();
            double run_time = end_time - start_time;
            printf("Execution time for run %d: %f seconds\n", run + 1, run_time);
            total_time += run_time; // Accumulate total execution time
            
        }

        // Reset for the next run
        process_sum = 0.0;
        total_sum = 0.0;
        free(displs);
        free(rcounts);
        MPI_Barrier(MPI_COMM_WORLD);
    }

    // Calculate and print average time
    if (rank == 0) {
        printf("\nAverage execution time over 10 runs: %f seconds\n", total_time / 10);
    }

    // Free memory resources
    free(local_b);
    free(local_a);
    if (rank == 0) {
        free(a);
        free(b);
    }

    MPI_Finalize();
    return 0;
}
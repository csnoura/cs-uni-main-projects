# Parallel Computing: Euclidean Distance Calculation with MPI

This project demonstrates the calculation of the **Euclidean distance** between two randomly generated vectors using **MPI (Message Passing Interface)**. The program divides the computation among multiple processes for parallel execution, leveraging distributed computing to improve efficiency.

## Highlights 
- Uses **MPI** to distribute the generation and computation of vectors among processes.
- Computes the Euclidean distance in parallel.
- Measures the total execution time for the computation.
- Demonstrates MPI functions like `MPI_Gatherv` and `MPI_Reduce`.

---
## How the Project Uses MPI
1. **Distribute Workload:** The vectors (`a` and `b`) are divided among the processes, and each process generates its portion of the vectors independently.
2. **Local Computation:** Each process calculates the squared differences for its assigned portion of the vectors.
3. **Global Reduction:** The `MPI_Reduce` function combines the local results from all processes into a single total sum at the root process (rank `0`).
4. **Result Gathering:** The `MPI_Gatherv` function gathers the generated vectors (`a` and `b`) at the root process to print their values as a single vector combined from all processes, which is an additional step.

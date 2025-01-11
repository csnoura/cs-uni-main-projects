# Algorithms:Dynamic Programming-Based Portfolio Optimization

This project implements a **dynamic programming (DP)** approach for **portfolio optimization**. It determines the optimal allocation of investments across multiple assets to maximize portfolio return while staying within a specified risk tolerance.

## Highlights
- Reads asset data from a file.
- Calculates portfolio return and risk based on asset allocations.
- Finds the optimal allocation using a DP table approach.
- Considers user-defined investment amount and risk tolerance.

---

## How It Works

1. **Input:**
   - A file containing asset details (`assets2.txt`).
   - User inputs for total investment and risk tolerance.

2. **Dynamic Programming Table:**
   - Constructs a DP table where each cell represents a subproblem of portfolio allocation with specific investment and asset constraints.
   - Considers the risk level and portfolio return for optimal allocation.

3. **Output:**
   - Displays the optimal allocation of assets (quantity for each asset).
   - Prints the portfolio's expected return and risk level.

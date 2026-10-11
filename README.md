# Performance Analysis: Binary Search Tree vs Hash Table

## 1. Project Overview
This project benchmarks and compares the empirical and theoretical performance of a Binary Search Tree (BST) and a Hash Table (Separate Chaining) implemented from scratch in Java.

## 2. Theoretical Complexity Analysis

| Data Structure | Operation | Best Case | Average Case | Worst Case | Worst-Case Scenario Trigger |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **BST** | Insert | O(1) | O(log n) | O(n) | Sorted/reverse-sorted input degrades tree to linked list |
| | Search | O(1) | O(log n) | O(n) | Target is at leaf of skewed tree or missing |
| | Delete | O(1) | O(log n) | O(n) | Finding node/successor in degenerate tree takes O(n) |
| **Hash Table** | Insert | O(1) | O(1) | O(n) | Severe hash collisions where all keys fall in one bucket |
| | Search | O(1) | O(1) | O(n) | All keys map to the same bucket; linear chain scan |
| | Delete | O(1) | O(1) | O(n) | Linear chain traversal caused by collisions |

## 3. Experimental Results

The benchmark was executed with randomly generated integer keys (seed = 42). Execution time in milliseconds (ms):

| N | Structure | Insert (ms) | Search (ms) | Delete (ms) |
| :--- | :--- | :--- | :--- | :--- |
| **100** | BST | 0.3044 | 0.0318 | 0.0279 |
| | HashTable | 0.0514 | 0.1153 | 0.0103 |
| **1,000** | BST | 0.1475 | 0.1053 | 0.1936 |
| | HashTable | 0.4102 | 0.0487 | 0.0540 |
| **10,000** | BST | 0.9228 | 0.9011 | 1.5598 |
| | HashTable | 0.8908 | 0.2381 | 0.2153 |

## 4. Discussion & Practical Differences

1. **Search & Deletion Efficiency:** At $N = 10,000$, the Hash Table is roughly $3.8\times$ faster in search ($0.2381$ ms vs $0.9011$ ms) and $7.2\times$ faster in deletion ($0.2153$ ms vs $1.5598$ ms). This aligns with the theoretical gap between $O(1)$ and $O(\log n)$.
2. **Insertion Behavior:** At $N = 1,000$, Hash Table insertion took longer due to table resizing (reallocating and rehashing elements when load factor threshold $\ge 0.75$ is reached).
3. **Memory & Cache Locality:** The Hash Table leverages array index calculations for immediate bucket access, whereas the BST suffers from pointer-chasing and CPU cache misses across the heap.
4. **Architectural Trade-offs:** While the Hash Table provides superior raw lookup and mutation speed, it loses key ordering. The BST maintains ordered data, enabling $O(\log n + k)$ range queries and $O(n)$ in-order traversals without rehashing overhead.
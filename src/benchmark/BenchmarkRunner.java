package benchmark;

import bst.BST;
import hash.HashTable;
import java.util.Random;

public class BenchmarkRunner {
    private final Random rand = new Random(42);

    public int[] generateRandomData(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = rand.nextInt(n * 10);
        }
        return data;
    }

    public void runTest(int n) {
        int[] data = generateRandomData(n);

        BST bst = new BST();
        long start = System.nanoTime();
        for (int key : data) bst.insert(key);
        double bstInsertTime = (System.nanoTime() - start) / 1e6;

        start = System.nanoTime();
        for (int key : data) bst.search(key);
        double bstSearchTime = (System.nanoTime() - start) / 1e6;

        start = System.nanoTime();
        for (int key : data) bst.delete(key);
        double bstDeleteTime = (System.nanoTime() - start) / 1e6;

        System.out.printf("%-10d %-12s %-15.4f %-15.4f %-15.4f%n",
                n, "BST", bstInsertTime, bstSearchTime, bstDeleteTime);

        HashTable ht = new HashTable(16);
        start = System.nanoTime();
        for (int key : data) ht.insert(key);
        double htInsertTime = (System.nanoTime() - start) / 1e6;

        start = System.nanoTime();
        for (int key : data) ht.search(key);
        double htSearchTime = (System.nanoTime() - start) / 1e6;

        start = System.nanoTime();
        for (int key : data) ht.delete(key);
        double htDeleteTime = (System.nanoTime() - start) / 1e6;

        System.out.printf("%-10d %-12s %-15.4f %-15.4f %-15.4f%n",
                n, "HashTable", htInsertTime, htSearchTime, htDeleteTime);
        System.out.println("-------------------------------------------------------------------------");
    }
}
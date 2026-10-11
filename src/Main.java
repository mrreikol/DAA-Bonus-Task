import benchmark.BenchmarkRunner;

public class Main {
    public static void main(String[] args) {
        int[] testSizes = {100, 1000, 10000};
        BenchmarkRunner runner = new BenchmarkRunner();

        System.out.printf("%-10s %-12s %-15s %-15s %-15s%n",
                "N", "Structure", "Insert (ms)", "Search (ms)", "Delete (ms)");
        System.out.println("-------------------------------------------------------------------------");

        for (int n : testSizes) {
            runner.runTest(n);
        }
    }
}
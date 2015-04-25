package cth.alg.matrix;

import java.math.BigInteger;

import static cth.alg.matrix.IntMatrix.cols;
import static cth.alg.matrix.IntMatrix.rows;

public class SimpleMultiplier {

    private final boolean stats;


    public SimpleMultiplier(boolean stats) {
        this.stats = stats;
    }

    int[][] mult(int[][] a, int[][] b) {

        checkArgs(a, b);

        BigInteger operations = BigInteger.ZERO;

        long start = System.currentTimeMillis();

        int[][] d = new int[rows(a)][cols(b)];

        for (int row = 0; row < rows(d); row++) {
            for (int col = 0; col < cols(d); col++) {
                for (int i = 0; i < cols(a); i++) {
                    d[row][col] += a[col][i] * b[i][col];
                    if (stats) {
                        operations = operations.add(BigInteger.ONE);
                    }
                }
            }
        }

        if (stats) {
            printStats(a, b, operations, start);
        }

        return d;
    }

    private void printStats(int[][] a, int[][] b, BigInteger operations, long start) {
        System.out.println(pp(a, b));
        System.out.println("# operations :" + operations.toString());
        System.out.println("in " + (System.currentTimeMillis() - start) + " ms");
    }

    private void checkArgs(int[][] a, int[][] b) {
        if (rows(a) != cols(b) || cols(a) != rows(b)) {
            throw new IllegalArgumentException("dims must match " + pp(a, b));
        }
    }

    private String pp(int[][] a, int[][] b) {
        return String.format("Multiplying (%d x %d) and (%d x %d)", rows(a), cols(a), rows(b), cols(b));
    }
}

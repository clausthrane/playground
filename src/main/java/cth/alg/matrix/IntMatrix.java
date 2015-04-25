package cth.alg.matrix;

import org.apache.commons.lang.math.RandomUtils;

import java.util.function.Function;

/**
 * Created by thrane on 24/04/15.
 */
public class IntMatrix {

    public static MatrixBuilder build(int rows, int cols) {
        return new MatrixBuilder(rows, cols);
    }


    static class MatrixBuilder {
        final int[][] matrix;

        public MatrixBuilder(int rows, int cols) {
            matrix = new int[rows][cols];
        }

        public MatrixBuilder defaultValue(int v) {
            forEach(matrix, value -> v);
            return this;
        }

        public MatrixBuilder random() {
            forEach(matrix, value -> RandomUtils.nextInt());
            return this;
        }

        public int[][] get() {
            return matrix;
        }
    }

    public static void forEach(int[][] matrix, Function<Integer, Integer> consumer) {
        for (int rowIdx = 0; rowIdx < rows(matrix); rowIdx++) {
            for (int colIdx = 0; colIdx < cols(matrix); colIdx++) {
                Integer result = consumer.apply(matrix[rowIdx][colIdx]);
                if(result != null) {
                    matrix[rowIdx][colIdx] = result;
                }
            }
        }
    }

    public static int rows(int[][] matrix) {
        return matrix.length;
    }

    public static int cols(int[][] matrix) {
        return matrix[0].length;
    }


}

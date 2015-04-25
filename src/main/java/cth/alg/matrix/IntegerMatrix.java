package cth.alg.matrix;

import com.google.common.collect.HashBasedTable;

/**
 * Created by thrane on 23/04/15.
 */
public class IntegerMatrix {

    HashBasedTable<Integer, Integer, Integer> matrix;

    public IntegerMatrix(int rows, int columns) {
        matrix = HashBasedTable.create(rows, columns);
    }

    int get(int row, int col) {
        return matrix.get(row, col);
    }
}

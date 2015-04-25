package cth.alg.matrix;

import junit.framework.Assert;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.apache.commons.lang.math.RandomUtils.nextInt;

/**
 * Created by thrane on 24/04/15.
 */
public class SimpleMultiplierTest {

    @Test
    public void testForEach() throws Exception {
        int[][] a = new int[2][2];
        assertAllValues(a, 0);
        IntMatrix.forEach(a, v -> 1);
        assertAllValues(a, 1);
    }

    // only take 2x2 matrix
    private void assertAllValues(int[][] a, int value) {
        Assert.assertEquals(value, a[0][0]);
        Assert.assertEquals(value, a[0][1]);
        Assert.assertEquals(value, a[1][0]);
        Assert.assertEquals(value, a[1][1]);
    }

    @Test
    public void testIdentity() throws Exception {
        int[][] a = IntMatrix.build(2, 2).defaultValue(1).get();
        int[][] b = IntMatrix.build(2, 2).defaultValue(1).get();
        SimpleMultiplier multiplier = new SimpleMultiplier(false);
        int[][] d = multiplier.mult(a, b);
        IntMatrix.forEach(d, v -> {
            Assert.assertEquals(1, v.intValue());
            return null;
        });
    }

    @Test
    public void testRandomSquare() throws Exception {

        int v1 = nextInt(4) + 1;
        int v2 = nextInt(4) + 1;
        int dim = nextInt(4) + 1;

        int[][] a = IntMatrix.build(dim, dim).defaultValue(v1).get();
        int[][] b = IntMatrix.build(dim, dim).defaultValue(v2).get();

        SimpleMultiplier multiplier = new SimpleMultiplier(true);

        int[][] mult = multiplier.mult(a, b);

        IntMatrix.forEach(mult, v -> {
            Assert.assertEquals(dim * v1 * v2, v.intValue());
            return null;
        });

        System.out.println(Arrays.deepToString(mult));
    }

    @Test
    public void testRandom() throws Exception {

        int dim = nextInt(40) + 1;

        int[][] a = IntMatrix.build(dim, dim).random().get();
        int[][] b = IntMatrix.build(dim, dim).random().get();

        SimpleMultiplier multiplier = new SimpleMultiplier(true);

        int[][] mult = multiplier.mult(a, b);

        System.out.println(Arrays.deepToString(mult));
    }

    @Test
    public void test() throws Exception {

        int[][] b = {{2,3},{4,5},{6,7},{8,9}};
        int[][] a = {{1,1,1,1},{1,1,1,1}};

        SimpleMultiplier multiplier = new SimpleMultiplier(true);

        int[][] mult = multiplier.mult(a, b);



        System.out.println(Arrays.deepToString(mult));
    }
}

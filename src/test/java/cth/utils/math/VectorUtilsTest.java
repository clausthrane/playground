package cth.utils.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

import static cth.utils.math.VectorUtils.newVector;

public class VectorUtilsTest {

    @Test
    public void testNewVector() throws Exception {
        Vector<Double> vector = newVector(1d, 3d);
        Assert.assertEquals(2, vector.size());
    }

    @Test
    public void testDistance() throws Exception {

        Vector<Double> z = newVector(0d, 0d);
        Vector<Double> p = newVector(15d, 15d);
        Vector<Double> q = newVector(20d, 10d);

        int h = VectorUtils.euclideanDistance(p, z);
        int i = VectorUtils.euclideanDistance(p, q);
        int j = VectorUtils.euclideanDistance(q, z);

        Assert.assertTrue(h + i >= j);
    }

    @Test
    public void testSubtraction() throws Exception {
        Vector<Double> expectedDifference = newVector(-5d, 5d);
        Vector<Double> p = newVector(15d, 15d);
        Vector<Double> q = newVector(20d, 10d);

        Vector<Double> difference = VectorUtils.subtract(p, q);
        Assert.assertEquals(expectedDifference, difference);
    }

    @Test
    public void testAddVectors() throws Exception {
        Vector<Double> expectedDifference = newVector(35d, 25d);
        Vector<Double> p = newVector(15d, 15d);
        Vector<Double> q = newVector(20d, 10d);

        Vector<Double> difference = VectorUtils.add(p, q);
        Assert.assertEquals(expectedDifference, difference);

    }
}

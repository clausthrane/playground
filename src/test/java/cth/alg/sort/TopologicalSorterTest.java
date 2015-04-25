package cth.alg.sort;

import cth.alg.graph.jgrapht.GraphBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TopologicalSorterTest {

    @Test
    public void testEmptyGraph() throws Exception {
        TopologicalSorter sorter = new TopologicalSorter();
        List<String> result = sorter.topSort(GraphBuilder.buildDAG());
        System.out.println(result);
        Assert.assertNotNull(result);
    }


}

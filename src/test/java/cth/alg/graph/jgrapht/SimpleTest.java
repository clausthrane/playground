package cth.alg.graph.jgrapht;

import org.jgrapht.GraphPath;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import java.util.List;

import static cth.alg.graph.jgrapht.GraphBuilder.buildGraph;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;


public class SimpleTest {

    @Test
    public void testDegree() throws Exception {
        UndirectedGraph<String, DefaultEdge> g = buildGraph();
        assertEquals(2, g.degreeOf("v1"));
    }

    @Test
    public void testDijkstraShortestPath() throws Exception {
        UndirectedGraph<String, DefaultEdge> g = buildGraph();

        List<DefaultEdge> path = DijkstraShortestPath.findPathBetween(g, "v1", "v2");
        assertEquals(1, path.size());
    }

    @Test
    public void testDijkstaShortestPathInstance() throws Exception {
        DijkstraShortestPath<String, DefaultEdge> alg =
                new DijkstraShortestPath<String, DefaultEdge>(buildGraph(),"v1", "v3");
        GraphPath<String, DefaultEdge> path = alg.getPath();
        assertEquals(2, path.getEdgeList().size());
    }


    @Test
    public void testMinimumSpanningTree() throws Exception {
        KruskalMinimumSpanningTree<String, DefaultEdge> spanningTree =
                new KruskalMinimumSpanningTree<String, DefaultEdge>(buildGraph());

        fail();
    }


}

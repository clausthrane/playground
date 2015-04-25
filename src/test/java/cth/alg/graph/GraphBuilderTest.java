package cth.alg.graph;

import org.junit.Test;

public class GraphBuilderTest {


    @Test
    public void testAdjMatrixCanBeBuild() throws Exception {
        GraphBuilder b = GraphBuilder.builder().withEdge(Node.of("foo"), Node.of("bar"));
        AdjMatrix adjMatrix = b.newAdjMatix();
    }
}

package cth.alg.graph;

/**
 * Created by thrane on 22/04/15.
 */
public class GraphBuilder {

    private GraphBuilder(){

    }

    public static GraphBuilder builder() {
        return new GraphBuilder();
    }

    public GraphBuilder withNode(Node node) {
        return this;
    }

    public GraphBuilder withEdge(Node from, Node to) {
        return this;
    }

    public AdjMatrix newAdjMatix() {
        return null;
    }
}

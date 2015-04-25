package cth.alg.graph.jgrapht;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphBuilder {

    public static UndirectedGraph<String, DefaultEdge> buildGraph() {
        UndirectedGraph<String, DefaultEdge> g = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
        populate(g);
        return g;
    }

    public static DirectedGraph<String, DefaultEdge> buildDIG() {
        DirectedGraph<String, DefaultEdge> dig = new DefaultDirectedGraph(DefaultEdge.class);
        populate(dig);
        return dig;
    }

    public static DirectedGraph<String, DefaultEdge> buildDAG() {
        DirectedGraph<String, DefaultEdge> dig = new DefaultDirectedGraph(DefaultEdge.class);
        populate(dig);
        dig.removeEdge("v4", "v1");
        return dig;
    }

    private static void populate(Graph<String, DefaultEdge> graph) {
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";


        // add the vertices
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);
        graph.addVertex(v4);

        // add edges to create a circuit
        graph.addEdge(v1, v2);
        graph.addEdge(v2, v3);
        graph.addEdge(v3, v4);
        graph.addEdge(v4, v1);
    }
}

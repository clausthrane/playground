package cth.alg.sort;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * http://en.wikipedia.org/wiki/Topological_sorting
 * algorithms are known for constructing a topological ordering of any DAG in linear time.
 */
public class TopologicalSorter {

    /**
     *
     *  L ← Empty list that will contain the sorted elements
        S ← Set of all nodes with no incoming edges

        while S is non-empty do
            remove a node n from S
            add n to tail of L
            for each node m with an edge e from n to m do
                remove edge e from the graph
                if m has no other incoming edges then
                    insert m into S

        if graph has edges then
            return error (graph has at least one cycle)
        else
            return L (a topologically sorted order)


     * @param graph
     * @return
     */
    public List<String> topSort(DirectedGraph<String, DefaultEdge> graph) {
        List<String> orderedResult = Lists.newArrayList();

        Queue<String> q = Lists.newLinkedList(sinkNodes(graph));
        while (!q.isEmpty()) {
            String vertex = q.remove();
            orderedResult.add(vertex);
            Iterator<DefaultEdge> edges = outEdgeIterator(graph, vertex);
            while(edges.hasNext()){
                DefaultEdge e = edges.next();
                String target = graph.getEdgeTarget(e);
                graph.removeEdge(e);
                if (isSink(graph, target)) {
                    q.add(target);
                }
            }
        }

        checkPostCondition(graph);
        return orderedResult;
    }

    private Iterator<DefaultEdge> outEdgeIterator(DirectedGraph<String, DefaultEdge> graph, String vertex) {
        return Lists.newArrayList(graph.outgoingEdgesOf(vertex)).iterator();
    }

    private void checkPostCondition(DirectedGraph<String, DefaultEdge> graph) {
        if(!graph.edgeSet().isEmpty()) {
            throw new IllegalArgumentException("Graph had cycles");
        }
    }

    private boolean isSink(DirectedGraph<String, DefaultEdge> graph, String target) {
        return graph.incomingEdgesOf(target).isEmpty();
    }

    private Set<String> sinkNodes(DirectedGraph<String, DefaultEdge> graph) {
        Set<String> sinks = Sets.newHashSet();
        for (String s : graph.vertexSet()) {
            if(isSink(graph, s)) {
                sinks.add(s);
            }
        }
        return sinks;
    }
}

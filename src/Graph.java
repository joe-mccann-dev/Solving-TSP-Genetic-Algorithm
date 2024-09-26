import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    // Node => [Edge1, Edge2, Edge3 . . ., EdgeN]
    private HashMap<Node, List<Edge>> graph;

    Graph() {
        this.graph = new HashMap<>();
    }

    public void addEdge(Node src, Node dest) {
        Edge edge1 = new Edge(src, dest);
        graph.computeIfAbsent(src, key -> new ArrayList<>()).add(edge1);

        // prevent recomputing edgeWeight in constructor
        Edge edge2 = new Edge(dest, src, edge1.getWeight());
        graph.computeIfAbsent(dest, key -> new ArrayList<>()).add(edge2);
    }

    public List<Edge> getEdges(Node node) {
        return graph.getOrDefault(node, new ArrayList<>());
    }
}

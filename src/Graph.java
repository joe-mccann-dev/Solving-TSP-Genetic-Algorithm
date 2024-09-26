import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    // Node => [Edge1, Edge2, Edge3 . . ., EdgeN]
    private HashMap<Node, List<Edge>> graph;

    Graph() {
        this.graph = new HashMap<>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Node> nodes = new ArrayList<>(this.graph.keySet());
        for (Node node : nodes) {
            System.out.println("\nNode " + node + ": " + this.getEdges(node));
        }

        return sb.toString();
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

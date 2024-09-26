import java.util.HashMap;

public class Graph {
    // edgeObject => edgeObject.getWeight()
    private HashMap<Edge, Integer> graph;

    Graph(HashMap<Edge, Integer> graph) {
        this.graph = graph;
    }

    public void addEdge(Edge edge) {
        if (!graph.containsKey(edge)) {
            graph.put(edge, edge.getWeight());
        } else {
            System.out.println("Edge from " + edge.getSource() + " to " + edge.getDest() + " already exists.");
        }
    }
}

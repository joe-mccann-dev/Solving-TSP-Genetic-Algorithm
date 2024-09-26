import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public Integer getGraphEdge(Edge edge) {
        return graph.get(edge);
    }

    public List<Edge> getGraphEdges() {
        List<Edge> result = new ArrayList<>(graph.keySet());

        return result;
    }
}

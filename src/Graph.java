import java.util.HashMap;

public class Graph {
    // edgeObject => edgeObject.getWeight()
    private HashMap<Edge, Integer> graph;

    Graph(HashMap<Edge, Integer> graph) {
        this.graph = graph;
    }
}

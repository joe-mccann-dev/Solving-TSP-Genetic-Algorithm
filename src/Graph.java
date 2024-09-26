import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    public List<Node> getNodes() {
        return new ArrayList<>(graph.keySet());
    }

    public Node getNodeByName(String name) {
        List<Node> nodes = this.getNodes();
        for (Node node : nodes) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        return null;
    }

    public List<Node> getAllNodesExcept(Node node) {
        List<Node> result = new ArrayList<>();
        for (Node n : this.getNodes()) {
            if (!n.equals(node)) {
                result.add(n);
            }
        }
        return result;
    }

    // Python example uses a String but I setup using node objects, so I process
    // them in a queue
    public int getPathCost(List<Node> nodes) {
        int pathCost = 0;
        Queue<Node> nodeQueue = new LinkedList<Node>(nodes);

        Node currentNode = nodeQueue.poll();
        while (!nodeQueue.isEmpty()) {
            Node nextNode = nodeQueue.poll();
            List<Edge> nodeEdges = this.getEdges(currentNode);
            Edge edge = findEdge(nodeEdges, nextNode);
            if (edge != null) {
                System.out.println("Edge from " + currentNode + " to " + nextNode + " has weight: " + edge.getWeight());
                pathCost += edge.getWeight();
            }
            currentNode = nextNode;
        }

        return pathCost;
    }

    private Edge findEdge(List<Edge> edges, Node targetNode) {
        for (Edge edge : edges) {
            if (edge.getDest().equals(targetNode)) {
                return edge;
            }
        }
        return null;
    }

}

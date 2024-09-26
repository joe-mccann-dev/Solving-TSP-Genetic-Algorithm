import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Initialize Graph graph
        Graph graph = new Graph(new HashMap<>());

        // Create Node objects with given x and y coordinates
        Node A = new Node(100, 300, "A");
        Node B = new Node(200, 130, "B");
        Node C = new Node(300, 500, "C");
        Node D = new Node(500, 390, "D");
        Node E = new Node(700, 300, "E");
        Node F = new Node(900, 600, "F");
        Node G = new Node(800, 950, "G");
        Node H = new Node(600, 560, "H");
        Node I = new Node(350, 550, "I");
        Node J = new Node(270, 350, "J");

        // Add edges, weights calculated internally in Edge class
        // Objects.equals and Objects.hashCode overridden to ensure bidirectionality
        // A
        List<Edge> edges = Arrays.asList(
                new Edge(A, B),
                new Edge(A, C),
                new Edge(A, D),
                new Edge(A, E),
                new Edge(A, G),
                new Edge(A, H),
                new Edge(A, I),
                new Edge(A, J),
                new Edge(B, C),
                new Edge(B, D),
                new Edge(B, E),
                new Edge(B, F),
                new Edge(B, G),
                new Edge(B, H),
                new Edge(B, I),
                new Edge(B, J),
                new Edge(C, D),
                new Edge(C, E),
                new Edge(C, F),
                new Edge(C, G),
                new Edge(C, H),
                new Edge(C, I),
                new Edge(C, J),
                new Edge(D, E),
                new Edge(D, F),
                new Edge(D, G),
                new Edge(D, H),
                new Edge(D, I),
                new Edge(D, J),
                new Edge(E, F),
                new Edge(E, G),
                new Edge(E, H),
                new Edge(E, I),
                new Edge(E, J),
                new Edge(F, G),
                new Edge(F, H),
                new Edge(F, I),
                new Edge(F, J),
                new Edge(G, H),
                new Edge(G, I),
                new Edge(G, J),
                new Edge(H, J),
                new Edge(I, J));

        for (Edge edge : edges) {
            graph.addEdge(edge);
        }

        // Add edge weights to graph
        // Initialize GeneticAlgorithm as gaTSP,
        // Find optimal path and path cost by calling gaTSP.exec();

        // output a text file indicating shortest path
        // Ex:
        // Shortest distance value: 3961.67 miles
        // Sequence order of 10 cities: A -> B -> J -> C -> I -> D -> H -> G -> F -> E
        // -> A
    }
}

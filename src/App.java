import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        // Initialize Graph graph
        Graph graph = new Graph(new HashMap<>());
        // Create Node objects with given x and y coordinates
        Node A = new Node(100, 300);
        Node B = new Node(200, 130);
        Node C = new Node(300, 500);
        Node D = new Node(500, 390);
        Node E = new Node(700, 300);
        Node F = new Node(900, 600);
        Node G = new Node(800, 950);
        Node H = new Node(600, 560);
        Node I = new Node(350, 550);
        Node J = new Node(270, 350);
        // calculate edge weights
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

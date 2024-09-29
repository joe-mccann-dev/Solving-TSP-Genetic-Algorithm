public class App {
    public static void main(String[] args) throws Exception {
        // Initialize Graph graph
        Graph graph = new Graph();

        // Create Node objects with given x and y coordinates
        // Desmos graph with coords divided by 10:
        // https://www.desmos.com/calculator/7pa6xcpo2j
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
        // bidirectional edge handled in Graph#addEdge
        graph.addEdge(A, B);
        graph.addEdge(A, C);
        graph.addEdge(A, D);
        graph.addEdge(A, E);
        graph.addEdge(A, F);
        graph.addEdge(A, G);
        graph.addEdge(A, H);
        graph.addEdge(A, I);
        graph.addEdge(A, J);
        graph.addEdge(B, C);
        graph.addEdge(B, D);
        graph.addEdge(B, E);
        graph.addEdge(B, F);
        graph.addEdge(B, G);
        graph.addEdge(B, H);
        graph.addEdge(B, I);
        graph.addEdge(B, J);
        graph.addEdge(C, D);
        graph.addEdge(C, E);
        graph.addEdge(C, F);
        graph.addEdge(C, G);
        graph.addEdge(C, H);
        graph.addEdge(C, I);
        graph.addEdge(C, J);
        graph.addEdge(D, E);
        graph.addEdge(D, F);
        graph.addEdge(D, G);
        graph.addEdge(D, H);
        graph.addEdge(D, I);
        graph.addEdge(D, J);
        graph.addEdge(E, F);
        graph.addEdge(E, G);
        graph.addEdge(E, H);
        graph.addEdge(E, I);
        graph.addEdge(E, J);
        graph.addEdge(F, G);
        graph.addEdge(F, H);
        graph.addEdge(F, I);
        graph.addEdge(F, J);
        graph.addEdge(G, H);
        graph.addEdge(G, I);
        graph.addEdge(G, J);
        graph.addEdge(H, I);
        graph.addEdge(H, J);
        graph.addEdge(I, J);

        System.out.println("Graph contains the following nodes: " + graph.getNodes());

        // Initialize GeneticAlgorithm
        GeneticAlgorithm gaTSP = new GeneticAlgorithm(graph, 500, 50, 0.1, 2);
        gaTSP.setStartNode("A");
        gaTSP.findOptimalPath();

    }
}

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

        // Add edges, weights calculated internally in Edge class
        // Objects.equals and Objects.hashCode overridden to ensure bidirectionality
        // A
        Edge A_to_B = new Edge(A, B);
        Edge A_to_C = new Edge(A, C);
        Edge A_to_D = new Edge(A, D);
        Edge A_to_E = new Edge(A, E);
        Edge A_to_F = new Edge(A, F);
        Edge A_to_G = new Edge(A, G);
        Edge A_to_H = new Edge(A, H);
        Edge A_to_I = new Edge(A, I);
        Edge A_to_J = new Edge(A, J);
        // B
        Edge B_to_C = new Edge(B, C);
        Edge B_to_D = new Edge(B, D);
        Edge B_to_E = new Edge(B, E);
        Edge B_to_F = new Edge(B, F);
        Edge B_to_G = new Edge(B, G);
        Edge B_to_H = new Edge(B, H);
        Edge B_to_I = new Edge(B, I);
        Edge B_to_J = new Edge(B, J);
        // C
        Edge C_to_D = new Edge(C, D);
        Edge C_to_E = new Edge(C, E);
        Edge C_to_F = new Edge(C, F);
        Edge C_to_G = new Edge(C, G);
        Edge C_to_H = new Edge(C, H);
        Edge C_to_I = new Edge(C, I);
        Edge C_to_J = new Edge(C, J);
        // D
        Edge D_to_E = new Edge(D, E);
        Edge D_to_F = new Edge(D, F);
        Edge D_to_G = new Edge(D, G);
        Edge D_to_H = new Edge(D, H);
        Edge D_to_I = new Edge(D, I);
        Edge D_to_J = new Edge(D, J);
        // E
        Edge E_to_F = new Edge(E, F);
        Edge E_to_G = new Edge(E, G);
        Edge E_to_H = new Edge(E, H);
        Edge E_to_I = new Edge(E, I);
        Edge E_to_J = new Edge(E, J);
        // F
        Edge F_to_G = new Edge(F, G);
        Edge F_to_H = new Edge(F, H);
        Edge F_to_I = new Edge(F, I);
        Edge F_to_J = new Edge(F, J);
        // G
        Edge G_to_H = new Edge(G, H);
        Edge G_to_I = new Edge(G, I);
        Edge G_to_J = new Edge(G, J);
        // H
        Edge H_to_I = new Edge(H, I);
        Edge H_to_J = new Edge(H, J);
        // I
        Edge I_to_J = new Edge(I, J);

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

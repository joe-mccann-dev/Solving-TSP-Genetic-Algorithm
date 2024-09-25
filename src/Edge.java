public class Edge {

    private char src;
    private char dest;
    private int weight;

    Edge(char src, char dest) {
        this.src = src;
        this.dest = dest;
    }

    public void setEdgeWeight(Node node1, Node node2) {
        this.weight = calculateEdgeWeight(node1, node2);
    }

    // use pythagorean theorem to calculate hypotenuse
    // given nodes' x and y coordinates
    private int calculateEdgeWeight(Node node1, Node node2) {
        return 0;
    }
}

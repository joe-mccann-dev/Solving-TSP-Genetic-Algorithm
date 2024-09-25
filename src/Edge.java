public class Edge {

    private Node src;
    private Node dest;
    private double weight;

    Edge(Node src, Node dest) {
        this.src = src;
        this.dest = dest;
        setEdgeWeight(src, dest);
    }

    private void setEdgeWeight(Node node1, Node node2) {
        this.weight = calculateEdgeWeight(node1, node2);
    }

    // use pythagorean theorem to calculate hypotenuse
    // given nodes' x and y coordinates
    private double calculateEdgeWeight(Node node1, Node node2) {
        int a = Math.abs(node1.x - node2.x);
        int b = Math.abs(node1.y - node2.y);
        double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        return c;
    }
}

import java.util.Objects;

public class Edge {

    private Node src;
    private Node dest;
    private int weight;

    Edge(Node src, Node dest) {
        this.src = src;
        this.dest = dest;
        setEdgeWeight(src, dest);
    }

    private void setEdgeWeight(Node node1, Node node2) {
        this.weight = calculateEdgeWeight(node1, node2);
    }

    // calculate distance between node1 and node2 given nodes' x and y coordinates
    private int calculateEdgeWeight(Node node1, Node node2) {
        int a = Math.abs(node1.x - node2.x);
        int b = Math.abs(node1.y - node2.y);
        double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        return (int) c;
    }

    public int getWeight() {
        return this.weight;
    }

    // for keys of graph, make A_to_B interpreted the same as B_to_A
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;

        Edge edge = (Edge) object;
        return (Objects.equals(this.src, edge.src) && Objects.equals(this.dest, edge.dest)) ||
                (Objects.equals(this.src, edge.dest) && Objects.equals(this.dest, edge.src));
    }

    // override hashCode so that HashMap views hashCodes as identical
    @Override
    public int hashCode() {
        return Objects.hash(this.src, this.dest) + Objects.hash(this.dest, this.src);
    }
}

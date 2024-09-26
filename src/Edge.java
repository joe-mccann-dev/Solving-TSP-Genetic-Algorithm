public class Edge {

    private Node src;
    private Node dest;
    private int weight;

    Edge(Node src, Node dest) {
        this.src = src;
        this.dest = dest;
        setEdgeWeight(src, dest);
    }

    Edge(Node src, Node dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\n%s -> %s: %d\n", this.src, this.dest, this.weight));
        return sb.toString();
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

    public Node getSource() {
        return this.src;
    }

    public Node getDest() {
        return this.dest;
    }
}

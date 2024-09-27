public class Node {

    public int x;
    public int y;
    public String name;

    Node(int x, int y, String name) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    Node() {
        this.name = "-1";
        this.x = Integer.MAX_VALUE;
        this.y = Integer.MAX_VALUE;
    }

    public String toString() {
        return this.name;
    }
}

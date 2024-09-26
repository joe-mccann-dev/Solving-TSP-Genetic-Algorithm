public class Node {

    public int x;
    public int y;
    public String name;

    Node(int x, int y, String name) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return this.name;
    }

}

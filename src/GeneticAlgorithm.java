import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private int generations;
    private int populationSize;
    private float mutationRate;
    private Graph graph;
    private Node startNode;

    GeneticAlgorithm(Graph graph, int generations, int populationSize, float mutationRate) {
        this.graph = graph;
        this.generations = generations;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
    }

    public void perform() {
        System.out.println(generatePopulation(this.getStartNode()));
    }

    public void setStartNode(String name) {
        this.startNode = graph.getNodeByName(name);
    }

    public Node getStartNode() {
        return this.startNode;
    }

    // create N permutations of genomes
    private List<List<Node>> generatePopulation(Node startNode) {
        List<List<Node>> result = new ArrayList<>();

        List<Node> nodes = graph.getAllNodesExcept(startNode);
        for (int i = 0; i < populationSize; i++) {
            List<Node> copy = new ArrayList<>(nodes);
            Collections.shuffle(copy, new Random());
            // prepend startNode
            copy.add(0, startNode);
            // append startNode
            copy.add(startNode);
            result.add(copy);
        }
        return result;
    }
}

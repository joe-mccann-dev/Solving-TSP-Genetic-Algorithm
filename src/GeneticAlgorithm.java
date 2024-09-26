import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    private int generations;
    private int populationSize;
    private float mutationRate;
    private Graph graph;

    GeneticAlgorithm(Graph graph, int generations, int populationSize, float mutationRate) {
        this.graph = graph;
        this.generations = generations;
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
    }

    public void perform() {
        System.out.println(generatePopulation());
    }

    // create N permutations of genomes
    private List<List<Node>> generatePopulation() {
        List<List<Node>> result = new ArrayList<>();
        List<Node> nodes = graph.getNodes();

        for (int i = 0; i < populationSize; i++) {
            List<Node> copy = new ArrayList<>(nodes);
            Collections.shuffle(copy, new Random());
            result.add(copy);
        }

        return result;
    }
}

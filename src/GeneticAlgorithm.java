import java.util.List;

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

    public List<Object> perform() {
        return null;
    }
}

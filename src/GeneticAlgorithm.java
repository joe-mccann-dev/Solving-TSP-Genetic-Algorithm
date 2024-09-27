import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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

    // > sum_of_fitness = 0

    // > for each genome in population:
    // calculate its fitness with a Fitness Function
    // sum_of_fitness += genome_fitness

    // > for each genome in population:
    // probability = genome_fitness/sum_of_fitness

    // > choose a random genome with the calculated probabilities

    public void findOptimalPath() {
        List<List<Node>> population = this.createPopulation(this.getStartNode());
        for (int i = 0; i < generations; i++) {
            System.out.println("\nGeneration: " + (i + 1));
            System.out.println("Randomized paths: " + population);

            List<List<Node>> newPopulation = new ArrayList<>();

            // create an array of path costs, smaller cost is better
            List<Integer> pathCosts = this.determinePathCosts(population);
            System.out.println("\nCost for each path: " + pathCosts);

            // find minimum path cost index
            int minPathCostIndex = IntStream.range(0, pathCosts.size())
                    .reduce((c1, c2) -> pathCosts.get(c1) < pathCosts.get(c2) ? c1 : c2)
                    .orElse(-1);

            System.out.println(String.format("Route with smallest travel cost: %s: %d",
                    population.get(minPathCostIndex), pathCosts.get(minPathCostIndex)));

        }
    }

    public void setStartNode(String name) {
        this.startNode = this.graph.getNodeByName(name);
    }

    public Node getStartNode() {
        return this.startNode;
    }

    // create N permutations of genomes
    private List<List<Node>> createPopulation(Node startNode) {
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

    private List<Integer> determinePathCosts(List<List<Node>> population) {
        List<Integer> result = new ArrayList<>();
        for (List<Node> path : population) {
            int cost = this.graph.getPathCost(path);
            result.add(cost);
        }
        return result;
    }
}

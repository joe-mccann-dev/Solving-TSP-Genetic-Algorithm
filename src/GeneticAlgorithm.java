import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm {

    private int generations;
    private int populationSize;
    private double mutationRate;
    private Graph graph;
    private Node startNode;
    private int elitismRate;

    GeneticAlgorithm(Graph graph, int generations, int populationSize, double mutationRate, int elitismRate) {
        this.graph = graph;
        this.generations = generations;
        this.populationSize = populationSize;
        this.elitismRate = elitismRate;
    }

    public Graph getGraph() {
        return this.graph;
    }

    public void setStartNode(String name) {
        this.startNode = this.graph.getNodeByName(name);
    }

    public Node getStartNode() {
        return this.startNode;
    }

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

            // preparing for Elitism setup
            Collections.sort(pathCosts);
            population.sort(Comparator.comparingInt(path -> graph.getPathCost(path)));
            for (int j = 0; j < this.elitismRate; j++) {
                newPopulation.add(population.get(j));
            }

            System.out.println("New population with elites: " + newPopulation);

            // Begin Selection Process using Roulette Selection
            for (int _genomeIndex = this.elitismRate; _genomeIndex < this.populationSize; _genomeIndex++) {
                List<Node> parent1 = this.rouletteSelection(population, pathCosts);
                List<Node> parent2 = this.rouletteSelection(population, pathCosts);
                List<Node> offspring = this.recombination(parent1, parent2);
                // System.out.println("parent1: " + parent1);
                // System.out.println("parent2: " + parent2);
                // System.out.println("offspring: " + offspring);
                newPopulation.add(offspring);
            }

            // perform mutations, update new population
            for (int genomeIndex = 0; genomeIndex < newPopulation.size(); genomeIndex++) {
                List<Node> currentGenome = newPopulation.get(genomeIndex);
                List<Node> mutatedGenome = this.mutate(currentGenome);
                newPopulation.set(genomeIndex, mutatedGenome);
            }

            population = newPopulation;

            if (this.isConverged(population)) {
                System.out.println("\nAlgorithm has converged.");
                List<Node> path = population.get(0);
                System.out.println("Path cost: " + graph.getPathCost(path));
                System.out.println("Path determined: " + path);
                break;

            }
        }
    }

    private List<Integer> determinePathCosts(List<List<Node>> population) {
        return population.stream().map(nodeList -> this.graph.getPathCost(nodeList)).collect(Collectors.toList());
    }

    // use inverse of cost to ensure a lower cost path is considered more fit
    // calculate inverse of each cost,
    // then sum so probability using an inverse scales properly
    // https://www.baeldung.com/cs/genetic-algorithms-roulette-selection
    private List<Node> rouletteSelection(List<List<Node>> population, List<Integer> pathCosts) {

        double randomValue = new Random().nextDouble();
        double probabilitiesSum = 0;
        int indexToSelect = -1;

        for (int i = 0; i < pathCosts.size(); i++) {
            double cost = pathCosts.get(i);
            double sumOfInversePathCosts = pathCosts.stream().mapToDouble((c) -> 1.0 / c).sum();
            double probability = (1.0 / cost) / (sumOfInversePathCosts);
            probabilitiesSum += probability;

            if (probabilitiesSum >= randomValue) {
                indexToSelect = i;
                break;
            }
        }
        return population.get(indexToSelect);
    }

    // using two-point crossover
    // https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)
    private List<Node> recombination(List<Node> parent1, List<Node> parent2) {
        List<Node> offspring = new ArrayList<>(Collections.nCopies(parent1.size(), null));

        Node startNode = parent1.get(0);
        offspring.set(0, startNode);
        offspring.set(offspring.size() - 1, startNode);

        int[] indices = this.computeIndicesToSwap(parent1);
        int left = indices[0];
        int right = indices[1];

        for (int i = left; i < right; i++) {
            offspring.set(i, parent1.get(i));
        }

        List<Integer> availableIndices = new ArrayList<>();
        for (int i = 1; i < offspring.size() - 1; i++) {
            if (offspring.get(i) == null) {
                availableIndices.add(i);
            }

        }

        for (Node node : parent2) {
            if (!offspring.contains(node)) {
                int selectedIndex = availableIndices.remove(0);
                offspring.set(selectedIndex, node);
            }

            if (availableIndices.isEmpty()) {
                break;
            }
        }

        return offspring;
    }

    // only mutate if random generated double is less than mutationRate
    private List<Node> mutate(List<Node> currentGenome) {
        if (new Random().nextDouble() < mutationRate) {
            int[] indices = this.computeIndicesToSwap(currentGenome);
            // perform swap
            currentGenome = this.swap(currentGenome, indices[0], indices[1]);
        }
        return currentGenome;
    }

    private int[] computeIndicesToSwap(List<Node> currentGenome) {
        int[] indices = new int[2];
        int genomeLength = currentGenome.size();
        int left = this.getRandomInt(0, genomeLength);
        int right = this.getRandomInt(left, genomeLength);
        while (right - left > (genomeLength / 2)) {
            left = this.getRandomInt(0, genomeLength);
            right = this.getRandomInt(left, genomeLength);
        }
        indices[0] = left;
        indices[1] = right;
        return indices;
    }

    // min inclusive, max exclusive
    private int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private List<Node> swap(List<Node> currentGenome, int left, int right) {
        Node leftAllele = currentGenome.get(left);
        Node rightAllele = currentGenome.get(right);
        Node temp = leftAllele;
        currentGenome.set(left, rightAllele);
        currentGenome.set(right, temp);
        return currentGenome;
    }

    private boolean isConverged(List<List<Node>> population) {
        return population.stream().allMatch(genome -> genome.equals(population.get(0)));
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
}

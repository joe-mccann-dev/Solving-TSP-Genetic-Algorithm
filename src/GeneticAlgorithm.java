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

            // Begin Selection Process using Roulette Selection
            for (int g = 0; g < populationSize; g++) {
                List<Node> parent1 = this.rouletteSelection(population, pathCosts);
                List<Node> parent2 = this.rouletteSelection(population, pathCosts);
                List<Node> offspring = this.recombination(parent1, parent2);
                System.out.println("parent1: " + parent1);
                System.out.println("parent2: " + parent2);
                System.out.println("offspring: " + offspring);
                newPopulation.add(offspring);
            }

        }
    }

    private List<Node> rouletteSelection(List<List<Node>> population, List<Integer> pathCosts) {

        double randomValue = new Random().nextDouble();
        double probabilitiesSum = 0;
        int indexToSelect = -1;

        // use inverse of cost to ensure a lower cost path is considered more fit
        // calculate inverse of each cost,
        // then sum so probability using an inverse scales properly
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

    // use single point crossover (recombination)
    // https://en.wikipedia.org/wiki/Crossover_(genetic_algorithm)
    private List<Node> recombination(List<Node> parent1, List<Node> parent2) {
        List<Node> offspring = new ArrayList<>();
        int separatorIndex = new Random().nextInt(parent1.size());
        offspring.addAll(parent1.subList(0, separatorIndex));
        offspring.addAll(parent2.subList(separatorIndex, parent2.size()));
        return offspring;
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

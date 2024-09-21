package graph;

import priorityqueue.PriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Prim {
    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph<V, L> graph) {
        Collection<AbstractEdge<V, L>> msf = new ArrayList<>();
        HashMap<V, Double> labels = new HashMap<>();
        HashMap<V, V> parents = new HashMap<>();
        PriorityQueue<V> queue = new PriorityQueue<>(Comparator.comparingDouble(labels::get));

        for (V node : graph.getNodes()) {
            labels.put(node, Double.MAX_VALUE);
            parents.put(node, null);
        }

        V firstNode = graph.getNodes().iterator().next();
        labels.put(firstNode, 0.0);

        for (V node : labels.keySet()) {
            queue.push(node);
        }

        V u;
        while (!queue.empty()) {
            u = queue.top();
            queue.pop();

            for (V node : graph.getNeighbours(u)) {
                if (queue.contains(node) && (double) (graph.getLabel(u, node)) < labels.get(node)) { // Se l'etichetta tra u e node e' minore del valore salvato in labels settato a +∞
                    parents.put(node, u);
                    labels.put(node, (double) (graph.getLabel(u, node)));
                    queue.increasePriority(node);
                }
            }
        }

        for (V node : parents.keySet()) {
            if (parents.get(node) != null) {
                if (!graph.isDirected()) {
                    msf.add(new Edge(node, parents.get(node), labels.get(node)));
                }
                msf.add(new Edge(parents.get(node), node, labels.get(node)));
            }
        }

        return msf;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph<String, Double> g = new Graph<>(false, true);
        ArrayList<String> records = new ArrayList<>();
        String file_path = "src/italian_dist_graph.csv";

        importFromCSV(args[0], records);
        populateGraph(g, records);

        Collection<? extends AbstractEdge<String, Double>> msf = minimumSpanningForest(g);
        Edge[] result = msf.toArray(new Edge[0]);

        double totWeight = 0;
        for (Edge edge : result) {
            System.out.println(edge.getStart() + "," + edge.getEnd() + "," + edge.getLabel());
            totWeight += (double) edge.getLabel();
        }
        System.out.println("Minimum spanning tree edges number = " + msf.size() / 2);
        System.out.println("Minimum spanning tree total weight = " + (totWeight / 2) / 1000);
    }

    private static void importFromCSV(String path, ArrayList<String> records) throws FileNotFoundException {
        File f = new File(path);
        Scanner input = new Scanner(f);
        while (input.hasNextLine()) {
            records.add(input.nextLine());
        }
        input.close();
    }

    private static void populateGraph(Graph<String, Double> graph, ArrayList<String> records) {
        for (String record : records) {
            String[] field = record.split(",");
            graph.addEdge(field[0], field[1], Double.parseDouble(field[2]));
        }
    }
}

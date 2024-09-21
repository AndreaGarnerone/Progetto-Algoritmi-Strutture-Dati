package graph;

import priorityqueue.PriorityQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Prim1 {
    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph1<V, L> graph) {
        // Necessary data structures
        Collection<AbstractEdge<V, L>> msf = new ArrayList<>();
        HashMap<V, Double> labels = new HashMap<>();
        HashMap<V, V> parents = new HashMap<>();
        PriorityQueue<V> queue = new PriorityQueue<>(Comparator.comparingDouble(labels::get));

        // Inizializza tutti i nodi a infinito, dato che sono tutti fuori dal msf
        for (V node : graph.getNodes()) {
            labels.put(node, Double.MAX_VALUE);
            parents.put(node, null);
        }

        // Il primo nodo a 0
        V firstNode = graph.getNodes().iterator().next();
        labels.put(firstNode, 0.0);

        // Mette tutti i nodi nella priority queue, che vengono ordinati in base alla loro etichetta
        for (V node : labels.keySet()) {
            queue.push(node);
        }

        V u;
        while (!queue.empty()) {
            // Estrae il nodo in cima alla lista (cioe' quello col valore minimo essendo una priority queue)
            // Estrae tutti i nodi uno dopo l'altro per processarli tutti, e si ferma solo quando alla fine avra' analizzato tutti i nodi
            u = queue.top();
            queue.pop();

            // Itera sui nodi adiacenti a u e controlla se sono nella coda: se si' vuol dire che non sono ancora stati processati
            for (V node : graph.getNeighbours(u)) {
                if (queue.contains(node) && (double) (graph.getLabel(u, node)) < labels.get(node)) { // Se l'etichetta tra u e node e' minore del valore salvato in labels settato a +∞
                    parents.put(node, u);
                    labels.put(node, (double) (graph.getLabel(u, node)));
                    queue.increasePriority(node);
                }
            }
        }

        // Costruisce l'effettivo msf partendo da parent e aggiungendo gli archi
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
        // Crea le strutture
        Graph1<String, Double> g = new Graph1<>(false, true);
        ArrayList<String> records = new ArrayList<>();
        String file_path = "src/graph/italian_dist_graph.csv";

        importFromCSV(file_path, records);
        populateGraph(g, records);

        // Chiama l'algoritmo e una volta eseguito ne riporta i risultati
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

    private static void populateGraph(Graph1<String, Double> graph, ArrayList<String> records) {
        for (String record : records) {
            String[] field = record.split(",");
            graph.addEdge(field[0], field[1], Double.parseDouble(field[2]));
        }
    }
}

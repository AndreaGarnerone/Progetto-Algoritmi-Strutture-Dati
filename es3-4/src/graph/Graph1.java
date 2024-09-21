package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Implementation of a graph structure. It supports directed or undirected edges,
 * and optional labels for edges.
 *
 * @param <V> The type of the vertices in the graph.
 * @param <L> The type of the labels for edges in the graph.
 * @author Andrea Garnerone
 */
public class Graph1<V, L> implements AbstractGraph<V, L> {

    // For indicating if the graph is directed or undirected.
    private final boolean directed;

    // For indicating if the graph allows labelled edges.
    private final boolean labelled;

    // Store vertices and their corresponding list of edges in an adjacency list.
    private final HashMap<V, ArrayList<Edge<V, L>>> map;

    /**
     * Constructor of the graph
     *
     * @param directed Is true if the graph is directed, false otherwise.
     * @param labelled Is true if the graph supports labelled edges, false otherwise.
     */
    public Graph1(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labelled = labelled;
        this.map = new HashMap<V, ArrayList<Edge<V, L>>>();
    }

    /**
     * Checks if the graph is directed.
     *
     * @return True if the graph is directed, false for an undirected graph.
     */
    public boolean isDirected() {
        return directed;
    } // is Directed

    /**
     * Checks if the graph supports labelled edges.
     *
     * @return True if the graph supports labelled edges, false otherwise.
     */
    public boolean isLabelled() {
        return labelled;
    } // isLabelled

    /**
     * Adds a new node to the graph.
     *
     * @param a The vertex to be added.
     * @return True if the vertex is successfully added, false otherwise (it already exists in the graph).
     */
    public boolean addNode(V a) {
        if (!containsNode(a)) {
            map.put(a, new ArrayList<Edge<V, L>>());
            return true;
        } else return false;
    } // addNode

    /**
     * Add a new edge between two vertices, with a specified label.
     *
     * @param a The starting vertex of the edge.
     * @param b The ending vertex of the edge.
     * @param l The label between the edges.
     * @return True if the edge is successfully added, false otherwise.
     */
    public boolean addEdge(V a, V b, L l) {
        if (map != null) {
            boolean isAdded = false;

            if (!containsNode(a)) addNode(a);
            if (!containsNode(b)) addNode(b);
            if (!containsEdge(a, b)) {
                map.get(a).add(new Edge<>(a, b, l));
                isAdded = true;
            }
            if (!isDirected() && !containsEdge(b, a)) map.get(b).add(new Edge<>(b, a, l));

            return isAdded;
        } else return false;

    } //addEdge

    /**
     * Add a new edge between two vertices, without a specified label.
     *
     * @param a The starting vertex of the edge.
     * @param b The ending vertex of the edge.
     * @return True if the edge is successfully added, false otherwise.
     */
    public boolean addEdge(V a, V b) {
        return addEdge(a, b, null);
    } // addEdge

    /**
     * Checks if the graph contains a specified vertex.
     *
     * @param a The vertex to be checked.
     * @return True if the graph contains the vertex, false otherwise.
     */
    public boolean containsNode(V a) {
        return map.containsKey(a);
    } // containsNode

    /**
     * Checks if the graph contains an edge between two specified vertices.
     *
     * @param a The starting vertex of the edge.
     * @param b The ending vertex of the edge.
     * @return True if the edge exists, false otherwise.
     */
    public boolean containsEdge(V a, V b) {
        if (containsNode(a) && containsNode(b)) {
            boolean contains = false;
            for (Edge<V, L> edge : map.get(a)) {
                if (b.equals(edge.getEnd())) {
                    contains = true;
                    break;
                }
            }
            return contains;
        } else return false;
    } // containsEdge


    public boolean removeNode(V a) { //----------------------------------------------------------------------------
        if (containsNode(a)) {
            if (directed) {
                for (V i : map.keySet()) {
                    if (containsEdge(i, a)) removeEdge(i, a);
                }
                for (int i = 0; i < map.get(a).size(); i++) {
                    removeEdge(a, (map.get(a).get(i).getEnd()));
                }
            } else {
                for (int i = 0; i < map.get(a).size(); i++) {
                    removeEdge(a, (map.get(a).get(i).getEnd()));
                    removeEdge((map.get(a).get(i).getEnd()), a);
                }
            }
            map.remove(a);
            return true;
        } else {
            // System.out.println("Node " + a + " was not contained in the graph"); //debug
            return false;
        }
    }

    /**
     * Removes the edge between two vertices.
     *
     * @param a The starting vertex of the edge.
     * @param b The ending vertex of the edge.
     * @return True if the edge is successfully removed, false otherwise (it doesn't exist in the graph).
     */
    public boolean removeEdge(V a, V b) {
        if (containsEdge(a, b)) {
            boolean removed = false;
            ArrayList<Edge<V, L>> edges = map.get(a);
            for (Edge<V, L> edge : edges) {
                if (b.equals(edge.getEnd())) {
                    edges.remove(edge);
                    removed = true;
                    break;
                }
            }
            if (!directed) removeEdge(b, a);
            return removed;
        } else {
            return false;
        }
    } // removeEdge


    public int numNodes() { //---------------------------------------------------------------------
        return map.size();
    }

    /**
     * Retrieves the number of edges in the graph.
     *
     * @return The number of edges in the graph, 0 if the graph is not initialized.
     */
    public int numEdges() {
        int n = 0;
        for (ArrayList<Edge<V, L>> i : map.values()) {
            n += i.size();
        }
        return n;
    } // numEdges


    public Collection<V> getNodes() { //--------------------------------------------------------------------
        return map.keySet();
    }

    /**
     * Retrieves a collection of all edges in the graph.
     *
     * @return A collection of edges in the graph, empty if the graph is not initialized.
     */
    public Collection<? extends AbstractEdge<V, L>> getEdges() {
        ArrayList<AbstractEdge<V, L>> allEdges = new ArrayList<>();
        for (ArrayList<Edge<V, L>> i : map.values()) {
            allEdges.addAll(i);
        }
        return allEdges;
    } // getEdges


    public Collection<V> getNeighbours(V a) { //------------------------------------------------------------------------
        ArrayList<V> adjSet = new ArrayList<>();
        if (containsNode(a)) {
            for (int i = 0; i < map.get(a).size(); i++) {
                adjSet.add(map.get(a).get(i).getEnd());
            }
        }
        return adjSet;
    }

    /**
     * Retrieves the label of the edge between the two vertices.
     *
     * @param a The starting vertex of the edge.
     * @param b The ending vertex of the edge.
     * @return The label of the edge, or null if the edge is not present.
     */
    public L getLabel(V a, V b) {
        L label = null;
        if (containsEdge(a, b)) {
            boolean found = false;
            for (int i = 0; !found && i < map.get(a).size(); i++) { // map.get(a).size prende la dimensione della lista di archi di A
                found = b.equals(map.get(a).get(i).getEnd()); // get(i) prende l'i-esimo elemento della lista (li scorre tutti) e getEnd prende il vertice associato a quell'elemento
                if (found) label = map.get(a).get(i).getLabel(); // prende l'etichetta dell'i-esimo elemento della lista
            }
        }
        return label;
    } // getLabel


}

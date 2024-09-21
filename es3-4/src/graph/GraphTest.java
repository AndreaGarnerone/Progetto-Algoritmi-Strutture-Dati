package graph;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testGraphProperties() {
        Graph<String, Double> g = new Graph<>(false, false);
        assertFalse(g.isDirected());
        assertFalse(g.isLabelled());
        g = new Graph<>(true, true);
        assertTrue(g.isDirected());
        assertTrue(g.isLabelled());
    }

    @Test
    public void testEmptyGraph() {
        Graph<String, Double> g = new Graph<>(false, false);
        assertEquals(0, g.numEdges());
        assertEquals(0, g.numNodes());
    }

    @Test
    public void testAddNode() {
        Graph<String, Double> g = new Graph<>(true, true);
        assertTrue(g.addNode("torino"));
        assertFalse(g.addNode("torino"));
        assertEquals(1, g.numNodes());
    }

    @Test
    public void testRemoveNode() {
        Graph<String, Double> g = new Graph<>(false, false);
        g.addEdge("torino", "rivoli");
        g.addEdge("cuneo", "torino");
        assertTrue(g.containsEdge("torino", "rivoli"));
        assertTrue(g.containsEdge("cuneo", "torino"));
        assertTrue(g.removeNode("torino"));
        assertFalse(g.containsEdge("torino", "rivoli"));
        assertFalse(g.containsEdge("cuneo", "torino"));
        assertEquals(0, g.numEdges());
        assertFalse(g.removeNode("torino"));
    }

    @Test
    public void testAddEdge() {
        Graph<String, Double> g = new Graph<>(true, true);
        assertTrue(g.addEdge("torino", "rivoli", 40000.5));
        assertFalse(g.addEdge("torino", "rivoli", 40000.5));
        assertEquals(1, g.numEdges());
        assertEquals(2, g.numNodes());
    }

    @Test
    public void testRemoveEdge() {
        Graph<String, Double> g = new Graph<>(false, false);
        g.addEdge("torino", "rivoli");
        assertTrue(g.removeEdge("torino", "rivoli"));
        assertFalse(g.removeEdge("torino", "rivoli"));
    }

    @Test
    public void testRemoveEdgeDirected() {
        Graph<String, Double> g = new Graph<>(true, false);
        g.addEdge("torino", "rivoli");
        assertFalse(g.containsEdge("rivoli", "torino"));
        assertTrue(g.containsEdge("torino", "rivoli"));
        assertTrue(g.removeEdge("torino", "rivoli"));
        assertFalse(g.containsEdge("torino", "rivoli"));
    }

    @Test
    public void testGetNodes() {
        Graph<String, Double> g = new Graph<>(true, false);
        g.addEdge("torino", "rivoli");
        g.addEdge("cuneo", "torino");
        Collection<String> expectedNodes = Arrays.asList("torino", "rivoli", "cuneo");
        Collection<String> actualNodes = g.getNodes();
        assertEquals(expectedNodes.size(), actualNodes.size());
        assertTrue(actualNodes.containsAll(expectedNodes));
    }

    @Test
    public void testGetEdges() {
        Graph<String, Double> g = new Graph<>(true, true);
        g.addEdge("torino", "rivoli", 40000.5);
        g.addEdge("cuneo", "torino", 30000.5);
        ArrayList<AbstractEdge<String, Double>> edgeSet = (ArrayList<AbstractEdge<String, Double>>) g.getEdges();
        ArrayList<AbstractEdge<String, Double>> expectedSet = new ArrayList<>();
        expectedSet.add(new Edge<>("torino", "rivoli", 40000.5));
        expectedSet.add(new Edge<>("cuneo", "torino", 30000.5));
        assertTrue(edgeSet.containsAll(expectedSet));
    }

    @Test
    public void testGetLabel() {
        Graph<String, Double> g = new Graph<>(true, true);
        g.addEdge("torino", "rivoli", 40000.5);
        assertEquals(40000.5, g.getLabel("torino", "rivoli"), 0.0);
        g = new Graph<>(true, false);
        g.addEdge("torino", "rivoli");
        assertNull(g.getLabel("torino", "rivoli"));
    }

    @Test
    public void testGetNeighbours() {
        Graph<String, Double> g = new Graph<>(false, true);
        g.addEdge("torino", "rivoli", 40000.5);
        g.addEdge("cuneo", "torino", 30000.5);
        Collection<String> expectedNeighbours = Arrays.asList("rivoli", "cuneo");
        Collection<String> actualNeighbours = g.getNeighbours("torino");
        assertEquals(expectedNeighbours.size(), actualNeighbours.size());
        assertTrue(actualNeighbours.containsAll(expectedNeighbours));
    }

    @Test
    public void testNumNodes() {
        Graph<String, Double> g = new Graph<>(true, true);
        g.addNode("torino");
        assertEquals(1, g.numNodes());
        g.removeNode("torino");
        assertEquals(0, g.numNodes());
    }

    @Test
    public void testNumEdges() {
        Graph<String, Double> g = new Graph<>(true, true);
        g.addEdge("torino", "rivoli", 40000.5);
        g.addEdge("cuneo", "torino", 30000.5);
        assertEquals(2, g.numEdges());
        g.removeEdge("torino", "rivoli");
        assertEquals(1, g.numEdges());
        g.removeEdge("cuneo", "torino");
        assertEquals(0, g.numEdges());
    }

    @Test
    public void testContainsNode() {
        Graph<String, Double> g = new Graph<>(true, true);
        g.addNode("torino");
        assertTrue(g.containsNode("torino"));
        assertFalse(g.containsNode("rivoli"));
    }

    @Test
    public void testContainsEdge() {
        Graph<String, Double> g = new Graph<>(true, true);
        g.addEdge("torino", "rivoli", 40000.5);
        assertTrue(g.containsEdge("torino", "rivoli"));
        assertFalse(g.containsEdge("rivoli", "torino"));  // Directed graph
    }

    @Test
    public void testContainsEdgeUndirected() {
        Graph<String, Double> g = new Graph<>(false, true);
        g.addEdge("torino", "rivoli", 40000.5);
        assertTrue(g.containsEdge("torino", "rivoli"));
        assertTrue(g.containsEdge("rivoli", "torino"));  // Undirected graph
    }
}

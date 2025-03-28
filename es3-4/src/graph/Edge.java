package graph;

import java.util.Objects;

public class Edge<V, L> implements AbstractEdge<V, L> {

    private final V start;
    private final V end;
    private final L label;

    public Edge(V start, V end, L label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }

    @Override
    public V getStart() {
        return start;
    }

    @Override
    public V getEnd() {
        return end;
    }

    @Override
    public L getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?, ?> edge = (Edge<?, ?>) o;
        return Objects.equals(start, edge.start) && Objects.equals(end, edge.end) && Objects.equals(label, edge.label);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", label=" + label +
                '}';
    }

//    public String toString() {
//        String startString = (start != null) ? start.toString() : "null";
//        String endString = (end != null) ? end.toString() : "null";
//
//        if (label != null) {
//            return "\n" + startString + " -> " + endString + " - " +
//                    label.toString() + "\n";
//        } else {
//            return "\n" + startString + " -> " + endString + "\n";
//        }
//    }
}

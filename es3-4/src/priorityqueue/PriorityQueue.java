package priorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * A priority queue implementation for generics based on binary min-heap.
 * The elements in the queue are stored based on their priority, determined by a comparator.
 *
 * @param <E> The type of elements stored in the priority queue.
 * @author Andrea Garnerone
 */
public class PriorityQueue<E> implements AbstractQueue<E> {

    // Comparator used to compare the elements and determine the priority of the elements
    private final Comparator<E> comparator;
    // List to store elements in the order they are added.
    private final ArrayList<E> list;
    // Map to store elements and their corresponding indices in the list.
    private final HashMap<E, Integer> map;

    /**
     * Constructor of the class that create a PriorityQueue.
     *
     * @param comparator The comparator used to determine the priority of elements.
     */
    public PriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
    } // PriorityQueue

    /**
     * Verifies if the queue is empty or not.
     *
     * @return True if map is null or no elements are stored, true otherwise.
     */
    public boolean empty() {
        return map.isEmpty();
        //return arrayList.isEmpty();
    } // empty

    /**
     * Add an element to the queue.
     *
     * @param e The element to be added.
     * @return True if the element is successfully added, false if it already exists in the queue.
     */
    public boolean push(E e) {
        if (contains(e)) {
            // System.out.println("Impossible to store specified element for it is" + "already in the queue"); //debug
            return false;
        } else {
            map.put(e, map.size());
            list.add(e);
            if (map.size() > 1) {
                heapifyUp(map.size() - 1); //map.get(e) ??
            }
            return true;
        }
    } // push

    /**
     * Check if an element is already in the queue.
     *
     * @param e The element to be checked.
     * @return True if the element is in the queue, false otherwise.
     */
    public boolean contains(E e) {
        return map.containsKey(e);
    } // contains

    /**
     * Access the element with the highest priority in the queue.
     *
     * @return The element with the highest priority, or null if the queue is empty.
     */
    public E top() {
        if (empty()) return null;
        return list.get(0);
    } // top

    /**
     * Removes the element with the highest priority from the queue.
     */
    public void pop() {
        swap(0, map.size() - 1); // hashMap.size()-1 e non list.size()-1 perche' non e' detto che l'ultimo elemento della lista sia quello con priorita' minore
        if (remove(list.get(list.size() - 1)) && map.size() > 1) { // adesso invece posso usare list.size()-1 perche ho swappato gli elementi
            heapifyDown(0); // parte da zero perche' l'elemento swappato e' andato in posizione 0, e quindi va' spostato in basso dall'indice 0
        }
    } // pop

    /**
     * Remove the specified element, if in the queue.
     *
     * @param e The element to be removed.
     * @return True if the element is successfully removed, false if there wasn't in the queue.
     */
    public boolean remove(E e) {
        if (contains(e)) {
            int indexToRemove = map.get(e);
            if (indexToRemove != map.size() - 1) {
                swap(map.get(e), map.size() - 1);
            }
            list.remove(e);
            map.remove(e);
            heapifyDown(indexToRemove);

            return true;
        } else return false;
    } // remove


    /**
     * Swaps two elements in the list and updates their indices in the hashMap.
     *
     * @param i Index of the first element.
     * @param j Index of the second element.
     */
    public void swap(int i, int j) {
        E tmpEl = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmpEl);

        map.replace(list.get(i), i); /* replace(key, value) sostituisce con value il valore corrispondente alla chiave key.
                                            Nel nostro caso mette "i" come valore (indice) dell'elemento list.get(i) */
        map.replace(list.get(j), j);
    }

    /**
     * Increases the priority of the specified element by removing and re-adding it to the queue.
     *
     * @param e The element whose priority needs to be increased.
     */
    public void increasePriority(E e) {
        if (contains(e)) {
            remove(e);
            push(e);
        }
    }

    /**
     * Moves the specified element down the heap to its correct position
     * to keep the list ordered.
     *
     * @param i The index of the element to be moved down.
     */
    private void heapifyDown(int i) {
        int left = left(i);
        int right = right(i);
        int max = i;

        if (left < map.size() && comparator.compare(list.get(max), list.get(left)) > 0) {
            max = left;
        }
        if (right < map.size() && comparator.compare(list.get(max), list.get(right)) > 0) {
            max = right;
        }

        if (max != i) {
            swap(i, max);
            heapifyDown(max);
        }
    }

    private void heapifyUp(int i) {
        int parent = parent(i);
        while (i > 0 && comparator.compare(list.get(parent), list.get(i)) > 0) {
            swap(parent, i);
            i = parent;
            parent = parent(i);
        }
    }


    /**
     * Retrieve the parent index of the given element index
     *
     * @param i The index of the child.
     * @return The index of the parent.
     */
    private int parent(int i) {
        return (i - 1) / 2;
    } // parent

    /**
     * Retrieve the left child index of the given element index
     *
     * @param i The index of the parent.
     * @return The index of the left child.
     */
    private int left(int i) {
        return 2 * i + 1;
    } // left

    /**
     * Retrieve the right child index of the given element index
     *
     * @param i The index of the parent.
     * @return The index of the right child.
     */
    private int right(int i) {
        return 2 * i + 2;
    } // right
}

package priorityqueue;

import java.util.Comparator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Andrea Garnerone
 */
public class PriorityQueueTest {

    @Test
    public void testEmptyQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        assertTrue(queue.empty());
    }

    @Test
    public void testPushOneElem() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(1);
        assertFalse(queue.empty());
    }

    @Test
    public void testPush() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(5);
        queue.push(3);
        queue.push(8);
        assertEquals(Integer.valueOf(3), queue.top());
    }

    @Test
    public void testContains() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(5);
        assertTrue(queue.contains(5));
    }

    @Test
    public void testContainsFalse() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(5);
        assertTrue(queue.contains(5));
        assertFalse(queue.contains(2));
    }

    @Test
    public void testRemove() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(2);
        queue.push(1);
        queue.push(3);
        assertTrue(queue.remove(2));
        assertFalse(queue.contains(2));
    }

    @Test
    public void testPop() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(2);
        queue.push(1);
        queue.push(3);
        queue.pop();
        assertEquals(Integer.valueOf(2), queue.top());
    }

    @Test
    public void testPopAll() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(2);
        queue.push(1);
        queue.push(3);
        queue.pop();
        queue.pop();
        queue.pop();
        assertTrue(queue.empty());
        assertNull(queue.top());
    }

    @Test
    public void testStringComparator() {
        Comparator<String> stringComparator = Comparator.comparingInt(String::length);
        PriorityQueue<String> queue = new PriorityQueue<>(stringComparator);
        queue.push("banana");
        queue.push("apple");
        queue.push("orange");
        assertEquals("apple", queue.top());
    }

    @Test
    public void testDuplicateElements() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(1);
        queue.push(1);
        queue.push(2);
        queue.push(2);
        queue.push(3);
        assertEquals(Integer.valueOf(1), queue.top());
    }

    @Test
    public void testNullElements() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(null);
        assertTrue(queue.contains(null));
    }

    @Test
    public void testEdgeCases() {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.naturalOrder());
        queue.push(Integer.MAX_VALUE);
        queue.push(Integer.MIN_VALUE);

        PriorityQueue<String> stringQueue = new PriorityQueue<>(Comparator.comparingInt(String::length));
        stringQueue.push("");
        stringQueue.push("!@#");
        assertEquals("", stringQueue.top());

        stringQueue.push("  test  ");
        stringQueue.push(" test ");
    }

}
/**
 * Represents a single node in a singly linked list
 */
public class Node<T> {
    /**
     * The data stored in the node and the last reference to the next node
     */
    T data;
    Node<T> next;

    /**
     * Constructs a Node with the given data
     *
     * @param data The data to store in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}


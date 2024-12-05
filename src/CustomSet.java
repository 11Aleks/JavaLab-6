import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * Custom implementation of the {@link Set}
 *
 * @param <T> the type of elements maintained by this set
 */
public class CustomSet<T> implements Set<T> {
    private Node<T> head;
    private int size;

    /**
     * Default constructor for an empty set
     */
    public CustomSet() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Constructor that initializes the set with a single element
     *
     * @param element the initial element to add to the set
     */
    public CustomSet(T element) {
        this();
        add(element);
    }

    /**
     * Constructor that initializes the set with elements from a collection
     *
     * @param collection a collection of elements
     */
    public CustomSet(Collection<? extends T> collection) {
        this();
        for (T element : collection) {
            add(element);
        }
    }

    /**
     * Returns the number of elements in this set
     *
     * @return the size of the set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the set is empty
     *
     * @return {@code true} if the set contains no elements, but if somethin goes wrong  {@code false}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the set contains a specific element
     *
     * @param o the element whose presence is to be tested
     * @return {@code true} if the set contains the specified element,but if something goes wrong {@code false}
     */
    @Override
    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this set
     *
     * @return an {@link Iterator} over the elements in this set
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Returns an array containing all elements in this set
     *
     * @return an array
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
     * Returns an array containing all elements in this set, with runtime type of the specified array
     *
     * @param a the array into which the elements of the set are to be stored
     * @param <E> the runtime type of the array to contain the collection
     * @return an array containing all elements in this set
     */
    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            a[index++] = (E) current.data;
            current = current.next;
        }
        return a;
    }

    /**
     * Adds the specified element to the set if it is not already present
     *
     * @param t element to be added
     * @return {@code true} if the set did not already contain the specified element, but if something goes wrong  {@code false}
     */
    @Override
    public boolean add(T t) {
        if (contains(t)) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    /**
     * Removes the specified element from this set, if it is present
     *
     * @param o element to be removed
     * @return {@code true} if the set contained the specified element, but if somethin goes wrong  {@code false}
     */
    @Override
    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(o)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(o)) {
            current = current.next;
        }
        if (current.next == null) {
            return false;
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    /**
     * Checks if this set contains all elements in the specified collection
     *
     * @param c the collection to check for containment
     * @return {@code true} if this set contains all elements in the collection, but if somethin goes wrong  {@code false}
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds all elements from the specified collection to this set
     *
     * @param c the collection containing elements to be added
     * @return {@code true} if the set was modified as a result of this operation
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the elements in this set that are contained in the specified collection
     *
     * @param c the collection containing elements to retain
     * @return {@code true} if the set was modified as a result of this operation
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node<T> current = head;
        Node<T> prev = null;
        while (current != null) {
            if (!c.contains(current.data)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                modified = true;
            } else {
                prev = current;
            }
            current = current.next;
        }
        return modified;
    }

    /**
     * Removes all elements from this set that are contained in the specified collection
     *
     * @param c the collection containing elements to be removed
     * @return {@code true} if the set was modified as a result of this operation
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            if (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all elements from this set
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns a string representation of the set
     *
     * @return a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            builder.append(current.data);
            if (current.next != null) {
                builder.append(", ");
            }
            current = current.next;
        }
        builder.append("]");
        return builder.toString();
    }
}

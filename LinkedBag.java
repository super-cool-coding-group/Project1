import java.util.ArrayList;

/**
 * This is the LinkedBag class which implements the BagInterface interface.
 *
 * With this class, we can create Bags (unordered lists which allow duplicates), find the union, intersection, and difference of two bags.
 *
 * The bag is implemented using a linked chain of Nodes.
 * 
 * Each method is documented separately.
 *
 * @author Pierlorenzo Peruzzo
 * @version 2.0
 */
public class LinkedBag<T> implements BagInterface<T> {

    // #region Private Fields

    /**
     * The first node in a LinkedBag
     */
    private Node<T> head;

    /**
     * The number of elements currently in a LinkedBag.
     */
    private int size = 0;

    // #endregion

    // #region Constructors

    /**
     * Default constructor, initalize the first node to null.
     */
    public LinkedBag() {
        this.head = null;
    }

    /**
     * Copy constructor to initialize this LinkedBag with the data from another BagInterface.
     * @param other The BagInterface from which we want to copy the data.
     */
    public LinkedBag(BagInterface<T> other) {
        // Check argument
        if (other == null || other.isEmpty()) {
            this.head = null;
            return;
        }

        // Use the .toArray() interface method to get an array, and add all the individual items to this LinkedBag.
        T[] res = other.toArray();
        for (int i = 0; i < res.length; i++) {
            add(res[i]);
        }
    }

    /**
     * Copy constructor to initialize thid LinedBag with the data from an ArrayList.
     * @param otherList The ArrayList from which we want to copy the data
     */
    public LinkedBag(ArrayList<T> otherList) {
        // Check argument
        if (otherList == null || otherList.isEmpty()) {
            this.head = null;
            return;
        }

        // Loop through the ArrayList and add each item to this LinkedBag.
        for (int i = 0; i < otherList.size(); i++) {
            add(otherList.get(i));
        }
    }

    // #endregion

    // #region Private Methods

    /**
     * Finds a given entry within this bag.
     *
     * @param anEntry The entry to search
     * @return A reference to the searched node, otherwise null
     */
    private Node<T> search(T anEntry) {
        Node<T> currentNode = head;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                break;
            } else {
                currentNode = currentNode.next;
            }
        }
        return currentNode;
    }

    // #endregion

    // #region Implement BagInterface

    /**
     * Gets the current number of entries in this LinkedBag.
     * 
     * @return The integer number of entries currently in the bag.
     */
    @Override
    public int getCurrentSize() { return size; }

    /**
     * Check whether this LinkedBag is empty.
     * 
     * @return True if the bag is empty, or false if not.
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Adds a new entry to this LinkedBag.
     * 
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, or false if not.
     */
    @Override
    public boolean add(T newEntry) {
        // Create a new node with the given data, and assign it as the head of the list
        Node<T> newNode = new Node<T>(newEntry);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

    /**
     * Removes the first entry from this bag, if possible.
     * 
     * @return Either the removed entry, if the removal was successful, or null.
     */
    @Override
    public T remove() {
        T res = null;
        if (head != null) {
            res = head.data;
            head = head.next;
            size--;
        }

        return res;
    }

    /**
     * Removes one occurrence of a given entry from this bag, if possible.
     * 
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    @Override
    public boolean remove(T anEntry) {
        
        // We use the search helper method to find this entry in the LinkedBag.
        Node<T> node = search(anEntry);

        // If we found the entry, we remove it from the LinkedBag.
        if (node != null) {
            // We move the data from the current head to the searched node
            // then we remove head.
            node.data = head.data;
            head = head.next;
            size--;

            return true;
        }

        return false;
    }

     /**
     * Removes all entries from this bag.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Counts the number of times a given entry appears in this LinkedBag.
     * 
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the bag.
     */
    @Override
    public int getFrequencyOf(T anEntry) {
        int freq = 0;
        int counter = 0;
        Node<T> currentNode = head;
        // Loop through the LinkedBag
        while ((counter < size) && currentNode != null) {
            // If the current data equals the given data (ie: anEntry), we increment the frequency
            if (anEntry.equals(currentNode.data)) {
                freq++;
            }
            counter++;
            currentNode = currentNode.next;
        }
        return freq;
    }

    /**
     * Tests whether this LinkedBag contains a given entry.
     * 
     * @param anEntry The entry to find.
     * @return True if the bag contains anEntry, or false if not.
     */
    @Override
    public boolean contains(T anEntry) {
        Node<T> currentNode = head;

        // Loop through the LinkedList
        while (currentNode != null) {
            // If the value of the currentNode is th same as the passed parameter anEntry, we return true
            if (anEntry.equals(currentNode.data)) {
                return true;
            // Otherwise we just check the next node
            } else {
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    /**
     * Retrieves all entries that are in this LinkedBag.
     * 
     * @return A newly allocated array of all the entries in the LinkedBag. Note: If the
     *         bag is empty, the returned array is empty.
     */
    @Override
    public T[] toArray() {

        // Suppress warning "unchecked" for cast to T
        // Create an array of same size of this LinkedBag
        @SuppressWarnings("unchecked")
        T[] res = (T[]) new Object[size];

        int index = 0;
        Node<T> currentNode = head;
        // Loop through all the items in this LinkedBag, and add each item to the array.
        // We use the index counter to help us keep track of the current array index.
        while ((index < size) && (currentNode != null)) {
            res[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }

        return res;
    }

    /**
     * Finds the Union of two bags (T).
     *
     * A union is a collection which consists of all of the contents of the two
     * collections that make it up.
     *
     * @param other The BagInterface we are adding on to this LinkedBag.
     * @return A new LinkedBag object of the two bags unionized.
     */
    @Override
    public BagInterface<T> union(BagInterface<T> other) {

        // Check other arg and return accordingly
        if (other == null || other.isEmpty()) {
            return this;
        }

        // Check this bag and return accordingly.
        if (this.isEmpty()) {
            return other;
        }

        LinkedBag<T> unionized = new LinkedBag<T>();

        // Add all the elments of this LinkedBag to the unionized LinkedBag
        Node<T> currentNode = head;
        while (currentNode != null) {
            unionized.add(currentNode.data);
            currentNode = currentNode.next;
        }

        // Convert the other BagInterface to array, and add all the elments of the array to the unionized LinkedBag.
        T[] otherArr = other.toArray();
        for (int i = 0; i < otherArr.length; i++) {
            unionized.add(otherArr[i]);
        }

        return unionized;
    }

     /**
     * Finds the Intersection of two bags (T).
     *
     * An intersection is a collection which consists of the items that exist in
     * both bags that make it up.
     *
     * @param other The BagInterface we are intersecting with this LinkedBag.
     * @return A new LinkedBag object of the two bags' intersection.
     */
    @Override
    public BagInterface<T> intersection(BagInterface<T> other) {

        LinkedBag<T> intersected = new LinkedBag<T>();

        // Check this bag and the other bag
        if (this.isEmpty() || other == null || other.isEmpty())
            return intersected;

        // Since we can't affect the contents of either bag, we copy the other bag's content
        // Copying the bag allows us to account for duplicates between our bags
        // If the value X is in Bag1 5 times, and is in Bag2 2 times, then it is in the
        // intersection bag 2 times (the lower of the two counts)
        LinkedBag<T> otherCopy = new LinkedBag<T>(other);

         // We go through each item in our list
        Node<T> currentNode = head;
        while (currentNode != null) {
            var currentData = currentNode.data;
             // If the copied list contains the item, we remove it and add it to the intersection list
            if (otherCopy.remove(currentData)) {
                intersected.add(currentData);
            }
            currentNode = currentNode.next;
        }

        return intersected;
    }

    /**
     * Finds the Difference of two bags (T).
     *
     * A difference is a collection which consists of the items that exist in this
     * bag but not the other bag.
     *
     * @param other The BagInterface we are subtracting from this LinkedBag.
     * @return A new LinkedBag object of the two bags' difference.
     */
    @Override
    public BagInterface<T> difference(BagInterface<T> other) {
        LinkedBag<T> diff = new LinkedBag<T>();
        if (other == null || other.isEmpty() || this.isEmpty())
            return diff;

        // Since we can't affect the contents of either bag, we copy the other bag's content
        // Copying the bag allows us to account for duplicates between our bags
        // If the value X is in Bag1 5 times, and is in Bag2 2 times, then it is in the
        // difference bag 3 times (the difference of the two counts)
        LinkedBag<T> otherCopy = new LinkedBag<T>(other);

        // We go through each item in our list
        Node<T> currentNode = head;
        while (currentNode != null) {
            var currentData = currentNode.data;
             // If the copied list doesn't contains the item, we remove it and add it to the difference list
            if (!otherCopy.remove(currentData)) {
                diff.add(currentData);
            }
            currentNode = currentNode.next;
        }

        return diff;
    }

    // #endregion

    // #region Public Methods

    /**
     * Create a String representation of all the items in the LinkedBag.
     * @return The string with all the items in the LinkedBag.
     */
    @Override
    public String toString() {
        String res = "[";

        if (isEmpty()) {
            res += "]";
            return res;
        }
        Node<T> currentNode = head;
        while (currentNode.next != null) {
            res += String.valueOf(currentNode.data) + ", ";
            currentNode = currentNode.next;
        }

        res += String.valueOf(currentNode.data);
        res += "]";
        return res;
    }

    // #endregion
}

/**
 * This is the Node class.
 * 
 * We use this class to hold data relative to a chained item in the LinkedBag.
 */
class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}
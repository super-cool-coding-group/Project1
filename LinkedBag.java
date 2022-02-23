import java.util.ArrayList;
import java.util.Currency;

class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Implement the three methods, union, intersection, and difference, for the
// class LinkedBag.
// Note: the class LinkedBag presents an implementation of the ADT bag using a
// linked chain.
public class LinkedBag<T> implements BagInterface<T> {

    // #region Private Fields

    private Node<T> head;
    private int size = 0;

    // #endregion

    // #region Constructors

    public LinkedBag() {
        this.head = null;
    }

    public LinkedBag(BagInterface<T> other) {
        if (other == null || other.isEmpty()) {
            this.head = null;
            return;
        }

        T[] res = other.toArray();
        for (int i = 0; i < res.length; i++) {
            add(res[i]);
        }
    }

    public LinkedBag(ArrayList<T> otherList) {
        if (otherList == null || otherList.isEmpty()) {
            this.head = null;
            return;
        }

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
     * @return A reference to the node, otherwise null
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

    @Override
    public int getCurrentSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean add(T newEntry) {
        Node<T> newNode = new Node<T>(newEntry);
        newNode.next = head;
        head = newNode;
        size++;
        return true;
    }

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

    @Override
    public boolean remove(T anEntry) {
        Node<T> node = search(anEntry);

        if (node != null) {
            node.data = head.data;
            head = head.next;
            size--;

            return true;
        }

        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int freq = 0;
        int counter = 0;
        Node<T> currentNode = head;
        while ((counter < size) && currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                freq++;
            }
            counter++;
            currentNode = currentNode.next;
        }
        return freq;
    }

    @Override
    public boolean contains(T anEntry) {
        Node<T> currentNode = head;

        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                return true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    @Override
    public T[] toArray() {

        @SuppressWarnings("unchecked")
        T[] res = (T[]) new Object[size];

        int index = 0;
        Node<T> currentNode = head;
        while ((index < size) && (currentNode != null)) {
            res[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }

        return res;
    }

    @Override
    public BagInterface<T> union(BagInterface<T> other) {

        if (other == null || other.isEmpty()) {
            return this;
        }

        if (this.isEmpty()) {
            return other;
        }

        LinkedBag<T> unionized = new LinkedBag<T>();

        // Add this
        Node<T> currentNode = head;
        while (currentNode != null) {
            unionized.add(currentNode.data);
            currentNode = currentNode.next;
        }

        // Add other
        T[] otherArr = other.toArray();
        for (int i = 0; i < otherArr.length; i++) {
            unionized.add(otherArr[i]);
        }

        return unionized;
    }

    @Override
    public BagInterface<T> intersection(BagInterface<T> other) {

        LinkedBag<T> intersected = new LinkedBag<T>();
        if (this.isEmpty() || other == null || other.isEmpty())
            return intersected;

        LinkedBag<T> otherCopy = new LinkedBag<T>(other);

        Node<T> currentNode = head;
        while (currentNode != null) {
            var currentData = currentNode.data;
            if (otherCopy.remove(currentData)) {
                intersected.add(currentData);
            }
            currentNode = currentNode.next;
        }

        return intersected;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> other) {
        LinkedBag<T> diff = new LinkedBag<T>();
        if (other == null || other.isEmpty())
            return diff;

        if (this.isEmpty()) {
            return diff;
        }

        LinkedBag<T> otherCopy = new LinkedBag<T>(other);

        Node<T> currentNode = head;
        while (currentNode != null) {
            var currentData = currentNode.data;
            if (!otherCopy.remove(currentData)) {
                diff.add(currentData);
            }
            currentNode = currentNode.next;
        }

        return diff;
    }

    // #endregion

    // #region Public Methods

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

    // // #region Private Fields

    // // #endregion

    // // #region Private Methods

    // public int search(T key) {
    // int index = -1;
    // Node<T> current = head;
    // int counter = 0;
    // while (current != null) {
    // if (current.data.equals(key)) {
    // index = counter;
    // return index;
    // }
    // current = current.next;
    // counter++;
    // }
    // return index;
    // }

    // // #endregion

    // // #region Public Methods

    // public void add(T data) {
    // Node<T> newNode = new Node<T>(data);
    // if (this.head == null) {
    // head = newNode;
    // } else {
    // getLastItem().next = newNode;
    // }
    // length++;
    // }

    // void addAt(int index, T data) {
    // if (index < 0 || index > length + 1) {
    // throw new IndexOutOfBoundsException("Invalid position provided to the
    // LinkedBag");
    // }

    // Node<T> newNode = new Node<T>(data);

    // if (index == 0) {
    // var temp = head;
    // head = newNode;
    // head.next = temp;
    // length++;
    // return;
    // }

    // var prev = head;
    // for (int i = 0; ((prev != null) && (i < index - 1)); i++) {
    // prev = prev.next;
    // }

    // var temp = prev.next;
    // prev.next = newNode;
    // newNode.next = temp;
    // length++;
    // }

    // public void addBeginning(T data) {
    // // insert the data
    // Node<T> newNode = new Node<T>(data);
    // newNode.next = head;
    // head = newNode;
    // length++;
    // }

    // public boolean remove(T data) {
    // if (isEmpty()) {
    // return false;
    // }

    // int index = search(data);
    // if (index != -1) {
    // removeAt(index);
    // return true;
    // }

    // return false;
    // }

    // public void removeAt(int index) {

    // if (index < 0 || index > length + 1) {
    // throw new IndexOutOfBoundsException("Invalid position provided to the
    // LinkedBag");
    // }

    // Node<T> temp = head;

    // if (index == 0) {
    // head = temp.next;
    // length--;
    // return;
    // }

    // for (int i = 0; ((temp != null) && (i < index - 1)); i++) {
    // temp = temp.next;
    // }

    // Node<T> next = temp.next.next;
    // temp.next = next;
    // length--;
    // }

    // public T get(int index){
    // if (index < 0 || index > length + 1) {
    // throw new IndexOutOfBoundsException("Invalid position provided to the
    // LinkedBag");
    // }

    // if (index == 0){
    // return head.data;
    // }

    // Node<T> currentNode = head;
    // int counter = 0;
    // while (currentNode.next != null){
    // if (counter == index)
    // return currentNode.data;
    // currentNode = currentNode.next;
    // counter++;
    // }

    // if (counter == index)
    // return currentNode.data;

    // return null;
    // }

    // public void clear() {
    // head = null;
    // length = 0;
    // }

    // public boolean isEmpty() {
    // if (head == null) {
    // return true;
    // }
    // return false;
    // }

    // public int length() {
    // return this.length;
    // }

    // Node<T> currentNode = head;
    // while (currentNode.next != null) {
    // res += String.valueOf(currentNode.data) + ", ";
    // currentNode = currentNode.next;
    // }

    // res += String.valueOf(currentNode.data);
    // res += "]";
    // return res;
    // }

    // // #endregion

    // @Override
    // @SuppressWarnings("unchecked")
    // public BagInterface<T> union(BagInterface<T> other) {

    // if (other == null || other.isEmpty())
    // {
    // return (BagInterface<T>)this;
    // }

    // if (this.isEmpty()) {
    // return other;
    // }

    // LinkedBag<T> unionized = new LinkedBag<T>();

    // Node<T> currentNode = this.head;
    // unionized.add(currentNode.data);
    // while (currentNode.next != null) {
    // currentNode = currentNode.next;
    // unionized.add(currentNode.data);
    // }

    // currentNode = other.head;
    // unionized.add(currentNode.data);
    // while (currentNode.next != null) {
    // currentNode = currentNode.next;
    // unionized.add(currentNode.data);
    // }

    // return unionized;
    // }

    // @Override
    // public BagInterface<T> intersection(BagInterface<T> other) {

    // LinkedBag<T> intersected = new LinkedBag<T>();
    // if (other == null || other.isEmpty())
    // return intersected;

    // if (this.isEmpty()) {
    // return intersected;
    // }

    // LinkedBag<T> otherCopy = new LinkedBag<T>(other);

    // Node<T> currentNode = this.head;
    // while (currentNode.next != null) {
    // var currentData = currentNode.data;
    // if (otherCopy.remove(currentData)) {
    // intersected.add(currentData);
    // }
    // currentNode = currentNode.next;
    // }

    // var currentData = currentNode.data;
    // if (otherCopy.remove(currentData)) {
    // intersected.add(currentData);
    // }
    // currentNode = currentNode.next;

    // return intersected;
    // }

    // @Override
    // public LinkedBaBagInterface<T> difference(BagInterface<T> other) {
    // LinkedBag<T> intersected = new LinkedBag<T>();
    // if (other == null || other.isEmpty())
    // return intersected;

    // if (this.isEmpty()) {
    // return intersected;
    // }

    // LinkedBag<T> otherCopy = new LinkedBag<T>(other);

    // Node<T> currentNode = this.head;
    // while (currentNode.next != null) {
    // var currentData = currentNode.data;
    // if (!otherCopy.remove(currentData)) {
    // intersected.add(currentData);
    // }
    // currentNode = currentNode.next;
    // }

    // var currentData = currentNode.data;
    // if (!otherCopy.remove(currentData)) {
    // intersected.add(currentData);
    // }

    // return intersected;
    // }
}

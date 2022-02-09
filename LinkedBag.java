import java.util.ArrayList;

// Implement the three methods, union, intersection, and difference, for the class LinkedBag. 
// Note: the class LinkedBag presents an implementation of the ADT bag using a linked chain.
public class LinkedBag<T> implements BagInterface<LinkedBag<T>> {

    // #region Private Fields

    private Node<T> head;
    private int length = 0;

    // #endregion

    // #region Constructors

    public LinkedBag() {
        this.head = null;
    }

    public LinkedBag(LinkedBag<T> other) {
        if (other == null || other.isEmpty()) {
            this.head = null;
            return;
        }
        this.head = copyNodes(other.head);
    }

    public LinkedBag(ArrayList<T> otherList){
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

    private Node<T> copyNodes(Node<T> node) {
        if (node == null) {
            return null;
        }
        Node<T> newNode = new Node<T>(node.data);
        newNode.next = copyNodes(node.next);
        return newNode;
    }

    private Node<T> getLastItem() {
        Node<T> currentNode = head;

        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public int search(T key) {
        int index = -1;
        Node<T> current = head;
        int counter = 0;
        while (current != null) {
            if (current.data.equals(key)) {
                index = counter;
                return index;
            }
            current = current.next;
            counter++;
        }
        return index;
    }

    // #endregion

    // #region Public Methods

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (this.head == null) {
            head = newNode;
        } else {
            getLastItem().next = newNode;
        }
        length++;
    }

    void addAt(int index, T data) {
        if (index < 0 || index > length + 1) {
            throw new IndexOutOfBoundsException("Invalid position provided to the LinkedBag");
        }

        Node<T> newNode = new Node<T>(data);

        if (index == 0) {
            var temp = head;
            head = newNode;
            head.next = temp;
            length++;
            return;
        }

        var prev = head;
        for (int i = 0; ((prev != null) && (i < index - 1)); i++) {
            prev = prev.next;
        }

        var temp = prev.next;
        prev.next = newNode;
        newNode.next = temp;
        length++;
    }

    public void addBeginning(T data) {
        // insert the data
        Node<T> newNode = new Node<T>(data);
        newNode.next = head;
        head = newNode;
        length++;
    }

    public boolean remove(T data) {
        if (isEmpty()) {
            return false;
        }

        int index = search(data);
        if (index != -1) {
            removeAt(index);
            return true;
        }

        return false;
    }

    public void removeAt(int index) {

        if (index < 0 || index > length + 1) {
            throw new IndexOutOfBoundsException("Invalid position provided to the LinkedBag");
        }

        Node<T> temp = head;

        if (index == 0) {
            head = temp.next;
            length--;
            return;
        }

        for (int i = 0; ((temp != null) && (i < index - 1)); i++) {
            temp = temp.next;
        }

        Node<T> next = temp.next.next;
        temp.next = next;
        length--;
    }

    public T get(int index){
        if (index < 0 || index > length + 1) {
            throw new IndexOutOfBoundsException("Invalid position provided to the LinkedBag");
        }

        if (index == 0){
            return head.data;
        }

        Node<T> currentNode = head;
        int counter = 0;
        while (currentNode.next != null){
            if (counter == index)
                return currentNode.data;
            currentNode = currentNode.next;
            counter++;
        }

        if (counter == index)
            return currentNode.data;
        
            return null;
    }

    public void clear() {
        head = null;
        length = 0;
    }

    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    public int length() {
        return this.length;
    }

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

    // #region Implement BagInterface

    @Override
    public LinkedBag<T> union(LinkedBag<T> other) {

        if (other == null || other.isEmpty())
            return this;

        if (this.isEmpty()) {
            return other;
        }

        LinkedBag<T> unionized = new LinkedBag<T>();

        Node<T> currentNode = this.head;
        unionized.add(currentNode.data);
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            unionized.add(currentNode.data);
        }

        currentNode = other.head;
        unionized.add(currentNode.data);
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            unionized.add(currentNode.data);
        }

        return unionized;
    }

    @Override
    public LinkedBag<T> intersection(LinkedBag<T> other) {

        LinkedBag<T> intersected = new LinkedBag<T>();
        if (other == null || other.isEmpty())
            return intersected;

        if (this.isEmpty()) {
            return intersected;
        }

        LinkedBag<T> otherCopy = new LinkedBag<T>(other);

        Node<T> currentNode = this.head;
        while (currentNode.next != null) {
            var currentData = currentNode.data;
            if (otherCopy.remove(currentData)) {
                intersected.add(currentData);
            }
            currentNode = currentNode.next;
        }

        var currentData = currentNode.data;
        if (otherCopy.remove(currentData)) {
            intersected.add(currentData);
        }
        currentNode = currentNode.next;

        return intersected;
    }

    @Override
    public LinkedBag<T> difference(LinkedBag<T> other) {
        LinkedBag<T> intersected = new LinkedBag<T>();
        if (other == null || other.isEmpty())
            return intersected;

        if (this.isEmpty()) {
            return intersected;
        }

        LinkedBag<T> otherCopy = new LinkedBag<T>(other);

        Node<T> currentNode = this.head;
        while (currentNode.next != null) {
            var currentData = currentNode.data;
            if (!otherCopy.remove(currentData)) {
                intersected.add(currentData);
            }
            currentNode = currentNode.next;
        }

        var currentData = currentNode.data;
        if (!otherCopy.remove(currentData)) {
            intersected.add(currentData);
        }

        return intersected;
    }

    // #endregion

}

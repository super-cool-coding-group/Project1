/**
 * This is the ResizeableArrayBag class which implements the BagInterface interface.
 *
 * With this class, we can create Bags (unordered lists which allow duplicates), find the union, intersection, and difference of two bags.
 *
 * Each method is documented separately.
 *
 * @author George Matta
 * @version 3.0
 */
public class ResizeableArrayBag<E> implements BagInterface<E>{

    /**
     * An Array which is the original bag that we have.
     */
    private E[] bag;

     /**
      * The amount of entries that have been placed into a bag
     */
    private int numOfEntries;

    /**
     * An integrity boolean to make sure the constructor was called
     */
    private boolean integrityOk = false;

    /**
     * A maximum size for the Array
     */
    private static final int MAX_CAPACITY = 10000;

    /**
     * The default capacity of any bag
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Creates a new ResizeableArrayBag given an array.
     * @param array The array we are creating a RAB from
     */
    public ResizeableArrayBag(E[] array){
        checkCapacity(array.length);
        this.bag = cleanBag(array);
        this.numOfEntries = bag.length;
        integrityOk = true;
    }

    /**
     * Creates a new ResizeableArrayBag given a capacity.
     * @param capacity The capacity we are using to create a bag.
     */
    public ResizeableArrayBag(int capacity){
        checkCapacity(capacity);
        this.numOfEntries = 0;
        this.bag = createBag(capacity);
        integrityOk = true;
    }

    /**
     * Creates a new ResizeableArrayBag with the default capacity.
     */
    public ResizeableArrayBag(){
        this.numOfEntries = 0;
        this.bag = createBag(DEFAULT_CAPACITY);
        integrityOk = true;
    }

    /**
     * Creates an array of generics of a variable capacity
     * @param capacity The maximum capacity of the Array
     */
    @SuppressWarnings("unchecked")
    private E[] createBag(int capacity){
        return (E[]) new Object[capacity];
    }

    /**
     * A method to double the capacity of an array
     */
    private void doubleCapacity(){
        int newCapacity = bag.length * 2;
        checkCapacity(newCapacity);
        E[] newBag = createBag(newCapacity);
        bag = copyEntries(newBag);
    }

    /**
     * A method to copy all the entries from this bag into a new bag
     * @param newBag The bag to copy the entries into
     * @return The bag with the copied entries
     */
    private E[] copyEntries(E[] newBag){
        for(int i = 0; i < numOfEntries; i++){
            if(newBag[i] == null){
                continue;
            }
            newBag[i] = bag[i];
        }
        return newBag;
    }

    /**
     * A method to return an array without any null entries given an array
     * @param toClean The bag we're cleaning of null entries
     * @return An Array of the clean bag
     */
    private E[] cleanBag(E[] toClean){
        // Calculate the size of the array and create ot
        int totalEntries = calculateNumOfEntries(toClean);
        E[] cleaned = createBag(totalEntries);
        // Loop through our unclean array and copy the values over if they aren't null
        for(int i = 0; i < totalEntries; i++){
            if(toClean[i] == null){
                continue;
            }
            cleaned[i] = toClean[i];
        }
        // Return the clean array
        return cleaned;
    }

    /**
     * A method to throw an error if the integrity of the ResizeableArrayBag is invalid
     */
    private void checkIntegrity(){
        if(!integrityOk){
            throw new SecurityException("ResizeableArrayBag object is corrupt or was not initialized properly.");
        }
    }

    /**
     * A method to throw an error if the capacity of the bag is too large or too small
     */
    private void checkCapacity(int capacity){
        String errorMessage = "Attempted to create a bag with a capcity (" + capacity + ") which is too ";
        if (capacity >= MAX_CAPACITY){
            errorMessage += "large";
            throw new IllegalStateException(errorMessage);
        }
        if (capacity < 0){
            errorMessage += "small";
            throw new IllegalStateException(errorMessage);
        }
    }

    /**
     * A method to check if the array is full
     * @return A boolean denoting whether or not the array is full
     */
    private boolean isFull(){
        return numOfEntries == bag.length;
    }

    /**
     * A method to return the number of entries in this ResizeableArrayBag
     * @return An int denoting the number of entries
     */
    @Override
    public int getCurrentSize() {
        return numOfEntries;
    }

    /**
     * A method to check if the ResizeableArrayBag is empty
     */
    @Override
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    /**
     * A method to count the number of non-null entries in array
     * @return An int denoting the amount of entries
     */
    private int calculateNumOfEntries(E[] array){
        int count = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                count++;
            }
        }
        return count;
    }

    /**
     * A method to add an entry to our bag. We add it to the last position (order doesn't matter in a bag)
     * @param newEntry A generic item to add to the array
     * @return True
     */
    @Override
    public boolean add(E newEntry) {
        checkIntegrity();
        if (isFull()){
            doubleCapacity();
        }

        bag[numOfEntries] = newEntry;
        numOfEntries++;

        return true;
    }

    /**
     * A method to remove an unspecified entry from the bag.
     * @return A generic item which is the item we removed
     */
    @Override
    public E remove() {
        checkIntegrity();
        E removed = removeIndex(numOfEntries - 1);
        return removed;
    }

    /**
     * A method to remove a specified entry from the bag
     * @return A boolean denoting whether or not the item was found and removed
     */
    @Override
    public boolean remove(E anEntry) {
        checkIntegrity();
        int indexOf = getIndexOf(anEntry);
        E removed = removeIndex(indexOf);
        if(removed == null){
            return false;
        }
        return removed.equals(anEntry);
    }

    /**
     * A private method to remove and return a specified entry associated with an index
     * @param index The index of the item to remove
     * @return The item we removed from the array
     */
    private E removeIndex(int index){
        E removed = null;
        // Check that our array isn't empty and the index is within the bounds
        if(isEmpty() || index < 0 || index > numOfEntries){
            return removed;
        }

        removed = bag[index]; // Set our removed item
        bag[index] = bag[numOfEntries - 1]; // Replace it with the final item in the array
        bag[numOfEntries - 1] = null; // Remove the final item in the array

        numOfEntries--; // Decrease the number of entries
        return removed; // Return the removed item
    }

    /**
     * A private method to get the index of a specified entry
     * @param anEntry The entry we are looking for in the bag
     * @return The index of the entry
     */
    private int getIndexOf(E anEntry){
        // Loop through our bag to see if we can find the entry
        for(int i = 0; i < numOfEntries; i++){
            if(bag[i].equals(anEntry)){
                return i; // Return its index
            }
        }

        return -1; // We return -1 if we don't find the entry
    }

    /**
     * A method to clear the ArrayBag of all its items
     */
    @Override
    public void clear(){
        checkIntegrity();
        // While the list isn't empty, keep removing
        while(!isEmpty())
            remove();
    }

    /**
     * A method to count how many times an entry occurs in a ResizeableArrayBag
     * @param anEntry The entry we are getting the frequency of
     * @return An int denoting the frequency of an item
     */
    @Override
    public int getFrequencyOf(E anEntry) {
        checkIntegrity();
        int count = 0;

        // Loop through all our items, if we find one which is the same as the entry increase the counter
        for(int i = 0; i < numOfEntries; i++){
            if(anEntry.equals(bag[i])){
                count++;
            }
        }

        return count; // Return the final count
    }

    /**
     * A method to see if an item is in the array
     * @param anEntry The entry we are checking for if it exists
     * @return A boolean denoting the existence of the item
     */
    @Override
    public boolean contains(E anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) >= 0;
    }

    /**
     * A method to return an array representation of the ResizeableArrayBag
     * @return An array of generic type Es of the entries in this ResizeableArrayBag
     */
    @Override
    public E[] toArray() {
        checkIntegrity();
        E[] arrayBag = createBag(numOfEntries);
        for(int i = 0; i < numOfEntries; i++){
            arrayBag[i] = bag[i];
        }
        return arrayBag;
    }

    /**
     * A method to return the union of two BagInterface objects.
     * This object is a ResizeableArrayBag, but we can find the union with a LinkedBag
     * @param other Any object that implements the BagInterface object that we find the union of
     * @return The unionized ResizeableArrayBag
     */
    @Override
    public BagInterface<E> union(BagInterface<E> other) {
        checkIntegrity();

        // Check that we have entries in our bag
        if(isEmpty()){
            return other;
        }
        // Check that we have a valid other bag
        if(other == null || other.isEmpty()){
            return this;
        }

        int otherSize = other.getCurrentSize();

        // Create the unionized bag
        ResizeableArrayBag<E> unionized = new ResizeableArrayBag<E>(numOfEntries + otherSize);

        // Add everything from our current bag
        for(int i = 0; i < numOfEntries; i++){
            unionized.add(bag[i]);
        }

        // Add everything from the other bag
        E[] otherArray = other.toArray();
        for(int i = 0; i < otherSize; i++){
            unionized.add(otherArray[i]);
        }

        return unionized;
    }

    /**
     * A method to return the intersection of two BagInterface objects.
     * This object is a ResizeableArrayBag, but we can find the intersection with a LinkedBag
     * @param other Any object that implements the BagInterface object that we find the intersection of
     * @return The intersected ResizeableArrayBag
     */
    @Override
    public BagInterface<E> intersection(BagInterface<E> other){
        checkIntegrity();
        // Create the intersected ResizeableArrayBag
        ResizeableArrayBag<E> intersected = new ResizeableArrayBag<E>();
        // Check that we don't have empty bags
        if (isEmpty() || other == null || other.isEmpty()){
            return intersected;
        }

        // Create a copy of the other bag since we can't change its contents
        ResizeableArrayBag<E> otherCopy = new ResizeableArrayBag<E>(other.toArray());

        // We go through each item in our list
        E currItem;
        for (int i = 0; i < this.numOfEntries; i++){
            currItem = this.bag[i];
            // If the copied list contains the item, we remove it and add it to the intersection list
            if (otherCopy.remove(currItem)){
                intersected.add(currItem);
            }
        }
        // Return the intersected ResizeableArrayBag object
        return intersected;
    }

    /**
     * A method to return the difference of two BagInterface objects.
     * This object is a ResizeableArrayBag, but we can find the difference with a LinkedBag
     * @param other Any object that implements the BagInterface object that we find the difference of
     * @return The difference ResizeableArrayBag
     */
    @Override
    public BagInterface<E> difference(BagInterface<E> other) {
        checkIntegrity();
        // Create the difference ResizeableArrayBag
        ResizeableArrayBag<E> difference = new ResizeableArrayBag<E>();
        // Check that we don't have empty bags
        if (isEmpty() || other == null || other.isEmpty()){
            return difference;
        }

        // Create a copy of the other bag since we can't change its contents
        ResizeableArrayBag<E> otherCopy = new ResizeableArrayBag<E>(other.toArray());

        // We go through each item in our list
        E currItem;
        for (int i = 0; i < this.numOfEntries; i++){
            currItem = this.bag[i];
            // If the copied list contains the item, we remove it and add it to the intersection list
            if (!otherCopy.remove(currItem)){
                difference.add(currItem);
            }
        }
        // Return the difference ResizeableArrayBag object
        return difference;
    }

    /**
     * A method to return a String representation of the array
     */
    public String toString(){
        String result = "[";

        for(int i = 0; i < numOfEntries; i++){
            result += bag[i];
            if(i != numOfEntries - 1){
                result += ", ";
            }
        }
        result += "]";

        return result;
    }

}
import java.util.ArrayList;

/**
 * This is the ResizeableArrayBag class which implements the BagInterface interface.
 *
 * With this class, we can create Bags (unordered lists which allow duplicates), find the union, intersection, and difference of two bags.
 *
 * Each method is documented separately.
 *
 * @author George Matta
 * @version 2.0
 */
public class ResizeableArrayBag<E> implements BagInterface<E>{

    /**
     * The default capacity of any bag
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * An Array which is the original bag that we have.
     */
    private E[] bag;

     /**
      * The amount of entries that have been placed into a bag
     */
    private int numOfEntries;

    /**
     * Creates a new ResizeableArrayBag given an Array.
     * @param bag The Array we are using to create a bag.
     */
    public ResizeableArrayBag(E[] bag){
        this.bag = bag;
        this.numOfEntries = calculateNumOfEntries();
    }

    /**
     * Clones a ResizeableArrayBag.
     * @param bag The Array we are using to create a bag.
     */
    public ResizeableArrayBag(ResizeableArrayBag<E> resizeableArrayBag){
        this.bag = resizeableArrayBag.bag.clone();
        this.numOfEntries = resizeableArrayBag.numOfEntries;
    }

    /**
     * Creates a new ResizeableArrayBag if we do not have a starting bag but we have a capacity
     * @param capacity The capacity of the bag we are creating
     * @throws IllegalArgumentException Indicates that the capacity is too low (0 or negative capacity).
     */
    public ResizeableArrayBag(int capacity) throws IllegalArgumentException{
        if(capacity > 0){
            this.bag = createBag(capacity);
            this.numOfEntries = 0;
        }
        else {
            throw new IllegalArgumentException("The capacity provided was lower than 0.");
        }
    }

    /**
     * Creates a new ResizeableArrayBag if we don't have any starting parameters (we use the default capacity)
     */
    public ResizeableArrayBag(){
        this.bag = createBag(DEFAULT_CAPACITY);
        this.numOfEntries = 0;
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
     * Finds the Union of two ResizeableArrayBags.
     *
     * A union is a collection which consists of all of the contents of the two collections that make it up.
     *
     * @param other The bag we are adding on to this bag.
     * @return A new ResizeableArrayBag object of the two bags unionized.
     */
    @Override
    public BagInterface<E> union(BagInterface<E> other){
        ResizeableArrayBag<E> otherBag = (ResizeableArrayBag<E>) other;
        // Calculate the length of two bag unionized together.
        // We subtract two because index starts at 0, but numOfEntries starts at 1
        int unionLength = this.numOfEntries + otherBag.numOfEntries - 2;
        // We create a ResizeableArrayBag from that bag so that we can add to it
        ResizeableArrayBag<E> unionized = new ResizeableArrayBag<E>(unionLength);

        // We go through each element in our list and add it to the union list
        for(int t = 0; t < this.numOfEntries; t++){
            unionized.add(this.bag[t]);
        }
        // We go through each element in the other list and add it to the union list
        for(int o = 0; o < otherBag.numOfEntries; o++){
            unionized.add(otherBag.bag[o]);
        }

        // Return the final ResizeableArrayBag
        return unionized;
    }

    /**
     * Finds the Intersection of two ResizeableArrayBags.
     *
     * An intersection is a collection which consists of the items that exist in both bags that make it up.
     *
     * @param other The bag we are intersecting with this bag.
     * @return A new ResizeableArrayBag object of the two bags' intersection.
     */
    @Override
    public BagInterface<E> intersection(BagInterface<E> other){
        ResizeableArrayBag<E> otherBag = (ResizeableArrayBag<E>) other;
        // An empty list for the intersecion of the two bags
        ResizeableArrayBag<E> intersected = new ResizeableArrayBag<E>();
        // Since we can't affect the contents of either bag, we copy the other bag's content
        // Copying the bag allows us to account for duplicates between our bags
        // If the value X is in Bag1 5 times, and is in Bag2 2 times, then it is in the
        // intersection bag 2 times (the lower of the two counts)
        ResizeableArrayBag<E> otherCopy = new ResizeableArrayBag<E>(otherBag);

        // We go through each item in our list
        E currItem;
        for (int i = 0; i < this.numOfEntries; i++){
            currItem = this.bag[i];
            if (currItem == null){
                continue;
            }
            // If the copied list contains the item, we remove it and add it to the intersection list
            if (otherCopy.remove(currItem)){
                intersected.add(currItem);
            }
        }
        // Return the intersected ResizeableArrayBag object
        return intersected;
    }

    /**
     * Finds the Difference of two ResizeableArrayBags.
     *
     * A difference is a collection which consists of the items that exist in this bag but not the other bag.
     *
     * @param other The bag we are subtracting from this bag.
     * @return A new ResizeableArrayBag object of the two bags' difference.
     */
    @Override
    public BagInterface<E> difference(BagInterface<E> other){
        ResizeableArrayBag<E> otherBag = (ResizeableArrayBag<E>) other;
        // An empty list for the difference of the two bags
        ResizeableArrayBag<E> diff = new ResizeableArrayBag<E>();
        // Since we can't affect the contents of either bag, we copy the other bag's content
        // Copying the bag allows us to account for duplicates between our bags
        // If the value X is in Bag1 5 times, and is in Bag2 2 times, then it is in the
        // difference bag 3 times (the difference of the two counts)
        ResizeableArrayBag<E> otherCopy = new ResizeableArrayBag<E>(otherBag);

        // We go through each item in our list
        E currItem;
        for (int i = 0; i < this.numOfEntries; i++){
            currItem = this.bag[i];
            if (currItem == null){
                continue;
            }
            // If the copied list doesn't contains the item, we remove it and add it to the difference list
            if (!otherCopy.remove(currItem)){
                diff.add(currItem);
            }
        }
        // Return the difference ResizeableArrayBag object
        return diff;
    }

    /**
     * A getter method to retrieve the original Bag Array.
     *
     * @return This ResizeableArrayBag bag array.
     */
    public E[] getBag(){
        return this.bag;
    }

    /**
     * A getter method for the default capacity of a ResizeableArrayBag
     * @return The DEFAULT_CAPACITY final static int attribute
     */
    public static int getDefaultCapacity(){
        return DEFAULT_CAPACITY;
    }

    /**
     * A getter method for the capacity of a bag
     * @return The int of the maximum capacity
     */
    public int getCapacity(){
        return this.bag.length;
    }

    /**
     * A getter method for the amount of entires that have been placed into a bag
     * @return The int of the number of entries
     */
    public int getNumOfEntries(){
        return this.numOfEntries;
    }

    /**
     * A getter method for the max length between a variable amoutn of bags
     * @return The maximum length
     */
    @SuppressWarnings("unchecked")
    public int getMaxEntries(ResizeableArrayBag<E>... bags){
        // We start the maximum at the length of the first bag
        int max = bags[0].numOfEntries;
        // An empty variable so we can change which bag we're looking at
        int currLength;
        // We loop through each bag that was passed in the vararg
        for(int i = 0; i < bags.length; i++){
            currLength = bags[i].numOfEntries;
            // If the length of the current bag is larger than our max, we update it
            if(currLength > max){
                max = currLength;
            }
        }
        // Return the maximum length
        return max;
    }

    /**
     * A method to calculate the amount of entires in a bag
     * @param toCount
     * @return An int denoting the amount of non-null entries in the bag
     */
    public int calculateNumOfEntries(){
        int count = 0;
        for(int i = 0; i < bag.length; i++){
            if (bag[i] != null)
                count++;
        }
        return count;
    }

    /**
     * A method to add an item to a ResizeableArrayBag's bag
     * @param toAdd
     */
    public void add(E toAdd){
        if(this.numOfEntries >= this.bag.length){
            this.bag = resizeBag();
        }

        bag[numOfEntries] = toAdd;
        numOfEntries++;
    }

    /**
     * A method to remove the last item from a ResizeableArrayBag's bag
     * @return The item we removed from the array
     */
    public E remove(){
        return remove(numOfEntries - 1);
    }

    /**
     * A method to remove an item from a ResizeableArrayBag's bag given an index
     * @param indexToRemove The index of the item to remove
     * @return The item we removed from the array
     */
    public E remove(int index){
        E removed = bag[index];
        bag[index] = bag[--numOfEntries];

        return removed;
    }

    /**
     * A method to remove a specified item from a ResizeableArrayBag's bag
     * @param toRemove The item to remove from the array
     * @return A boolean denoting if we removed an item or not
     */
    public boolean remove(E toRemove){
        int indexOf = getIndexOf(toRemove);
        if(indexOf < 0){
            return false;
        }
        return remove(indexOf).equals(toRemove);
    }


    /**
     * A method to return the first index of a given entry
     * @param target The targetted item to search for
     * @return An int denoting the index of the entry or -1 if it doesn't exist
     */
    public int getIndexOf(E target){
        // Loop through every item in the bag
        for(int i = 0; i < numOfEntries; i++){
            // If we found the item, return its index
            if(this.bag[i].equals(target)){
                return i;
            }
        }

        // If we don't find the item, return -1
        return -1;
    }

    /**
     * A method to double the size of the bag array and copy the elements over
     * @return The new double-size array
     */
    public E[] resizeBag(){
        // Create a new array which is double the length
        E[] resized = createBag(this.bag.length * 2);

        // Copy each entry over
        for(int i = 0; i < numOfEntries; i++){
            resized[i] = this.bag[i];
        }

        // Return the new array
        return resized;
    }

    /**
     * A setter method which changes the contents of the original ResizeableArrayBag Array.
     * This setter also returns the new bag array.
     *
     * @param toSet The Array we are setting our bag to
     * @return The Array we set our bag to
     */
    public E[] setBag(E[] toSet){
        this.bag = toSet;

        return toSet;
    }

    /**
     * A method which creates an ArrayList out of our bag Array
     * @return An ArrayList of our Array
     */
    public ArrayList<E> toArray(){
        // Create an ArrayList object of the same length as the number of entires
        ArrayList<E> arrL = new ArrayList<E>(numOfEntries);

        // Loop through every entry and add it to our ArrayList
        for(int i = 0; i < numOfEntries; i++){
            arrL.add(bag[i]);
        }

        // Return the ArrayList
        return arrL;
    }

    /**
     * A method to return a sorted ArrayList of our bag Array
     * @return A sorted ArrayList of our Array
     */
    public ArrayList<E> toSortedArrayList(){
        // Get the ArrayList
        ArrayList<E> sortedArrayList = toArray();
        // Sort the ArrayList
        sortedArrayList.sort(null);

        // Return the ArrayList
        return sortedArrayList;
    }

    /**
     * Overriding the default toString method so we can read the ResizeableArrayBags that we create.
     *
     * We sort the original list for readability, but the order of the elements does not matter in a bag.
     *
     * @return A String of this sorted ArrayList
     */
    public String toString(){
        // Get the sorted ArrayList
        ArrayList<E> sortedlist = toSortedArrayList();

        // Return it as a prettified string
        return sortedlist.toString();
    }
}

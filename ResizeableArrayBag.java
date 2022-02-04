import java.util.ArrayList;

/**
 * This is the ResizeableArrayBag class which implements the BagInterface interface.
 *
 * With this class, we can create Bags (unordered lists which allow duplicates), find the union, intersection, and difference of two bags.
 *
 * Each method is documented separately.
 *
 * @author George Matta
 * @version 1.0
 */
public class ResizeableArrayBag<E> implements BagInterface<ResizeableArrayBag<E>>{

    /**
     * An ArrayList which is the original bag that we have.
     */
    private ArrayList<E> originalList;

    /**
     * Creates a new ResizeableArrayBag given an Arraylist of Es.
     * @param originalList The list we are using to create a bag.
     */
    public ResizeableArrayBag(ArrayList<E> originalList){
        this.originalList = originalList;
    }

    /**
     * Finds the Union of two Bags.
     *
     * A union is a collection which consists of all of the contents of the two collections that make it up.
     *
     * @param other The bag we are adding on to this bag.
     * @return A new ResizeableArrayBag object of the two bags unionized.
     */
    @Override
    public ResizeableArrayBag<E> union(ResizeableArrayBag<E> other){
        ArrayList<E> unionized = new ArrayList<E>(); // An empty list for the union of the two bags

        // We go through each element in our list and add it to the union list
        for(E t : this.getOriginalList()){
            unionized.add(t);
        }
        // We go through each element in the other list and add it to the union list
        for(E o : other.getOriginalList()){
            unionized.add(o);
        }

        // Simply create a ResizeableBagArray from our union list and return it
        return new ResizeableArrayBag<E>(unionized);
    }

    /**
     * Finds the Intersection of two Bags.
     *
     * An intersection is a collection which consists of the items that exist in both bags that make it up.
     *
     * @param other The bag we are intersecting with this bag.
     * @return A new ResizeableArrayBag object of the two bags' intersection.
     */
    @Override
    public ResizeableArrayBag<E> intersection(ResizeableArrayBag<E> other){
        ArrayList<E> intersected = new ArrayList<E>(); // An empty list for the intersecion of the two bags
        // Since we can't affect the contents of either bag, we copy the other bag's content
        // Copying the bag allows us to account for duplicates between our bags
        // If the value X is in Bag1 5 times, and is in Bag2 2 times, then it is in the
        // intersection bag 2 times (the lower of the two counts)
        ArrayList<E> otherCopy = new ArrayList<E>(other.getOriginalList());

        // We go through each item in our list
        for(E t : this.getOriginalList()){
            // If the copied list contains the item, we remove it and add it to the intersection list
            if (otherCopy.remove(t)){
                intersected.add(t);
            }
        }

        // Simply create a ResizeableBagArray from our intersection list and return it
        return new ResizeableArrayBag<E>(intersected);
    }

    /**
     * Finds the Difference of two Bags.
     *
     * A difference is a collection which consists of the items that exist in this bag but not the other bag.
     *
     * @param other The bag we are subtracting from this bag.
     * @return A new ResizeableArrayBag object of the two bags' difference.
     */
    @Override
    public ResizeableArrayBag<E> difference(ResizeableArrayBag<E> other){
        ArrayList<E> diff = new ArrayList<E>(); // An empty list for the difference of the two bags
        // Since we can't affect the contents of either bag, we copy the other bag's content
        // Copying the bag allows us to account for duplicates between our bags
        // If the value X is in Bag1 5 times, and is in Bag2 2 times, then it is in the
        // difference bag 3 times (the difference of the two counts)
        ArrayList<E> otherCopy = new ArrayList<E>(other.getOriginalList());

        // We go through each item in our list
        for(E t : this.getOriginalList()){
            // If the copied list does not contain the item, we add it to our difference.
            // If the item was in the list, we remove it
            if (!otherCopy.remove(t)){
                diff.add(t);
            }
        }

        // Simply create a ResizeableBagArray from our difference list and return it
        return new ResizeableArrayBag<E>(diff);
    }

    /**
     * A getter method to retrieve the original ArrayList.
     *
     * @return An ArrayList of Es of the original ArrayList.
     */
    public ArrayList<E> getOriginalList(){
        return this.originalList;
    }

    /**
     * A setter method which changes the contents of the original bag.
     * This setter also returns the new bag.
     *
     * @param toSet The ArrayList we are setting our bag to
     * @return The ArrayList we set our bag to
     */
    public ArrayList<E> setOriginalList(ArrayList<E> toSet){
        this.originalList = toSet;

        return toSet;
    }

    /**
     * Overriding the basic toE method so we can read the Bags that we create
     *
     * We sort the original list for readability, but the order of the elements does not matter in a bag
     *
     * @return A String of this sorted ArrayList
     */
    public String toString(){
        ArrayList<E> sortedlist = getOriginalList();
        sortedlist.sort(null);

        return sortedlist.toString();
    }

}

/**
 * This is the BagInterface interface which defines the Union, Intersection, and Difference methods.
 *
 *
 * @author George Matta
 * @version 1.1
 */
public interface BagInterface<T>{

    /**
     * Finds the Union of two bags (T).
     *
     * A union is a collection which consists of all of the contents of the two collections that make it up.
     *
     * @param other The bag we are adding on to this bag.
     * @return A new bag (T) object of the two bags unionized.
     */
    public BagInterface<T> union(BagInterface<T> other);

    /**
     * Finds the Intersection of two bags (T).
     *
     * An intersection is a collection which consists of the items that exist in both bags that make it up.
     *
     * @param other The bag we are intersecting with this bag.
     * @return A new bag (T) object of the two bags' intersection.
     */
    public BagInterface<T> intersection(BagInterface<T> other);

    /**
     * Finds the Difference of two bags (T).
     *
     * A difference is a collection which consists of the items that exist in this bag but not the other bag.
     *
     * @param other The bag we are subtracting from this bag.
     * @return A new bag (T) object of the two bags' difference.
     */
    public BagInterface<T> difference(BagInterface<T> other);

}
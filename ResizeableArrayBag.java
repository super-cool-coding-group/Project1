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
public class ResizeableArrayBag implements BagInterface{

    /**
     * An ArrayList which is the original bag that we have.
     */
    private ArrayList<String> originalList;

    /**
     * Creates a new ResizeableArrayBag given an Arraylist of Strings.
     * @param originalList The list we are using to create a bag.
     */
    public ResizeableArrayBag(ArrayList<String> originalList){
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
    public ResizeableArrayBag union(ResizeableArrayBag other){
        ArrayList<String> unionized = new ArrayList<String>();

        for(String t : this.getOriginalList()){
            unionized.add(t);
        }
        for(String o : other.getOriginalList()){
            unionized.add(o);
        }

        return new ResizeableArrayBag(unionized);
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
    public ResizeableArrayBag intersection(ResizeableArrayBag other){
        ArrayList<String> intersected = new ArrayList<String>();

        for(String t : this.getOriginalList()){
            for (String o : other.getOriginalList()){
                if (t.equals(o)){
                    intersected.add(t);
                    break;
                }
            }
        }

        return new ResizeableArrayBag(intersected);
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
    public ResizeableArrayBag difference(ResizeableArrayBag other){
        ArrayList<String> diff = new ArrayList<String>();

        for(String t : this.getOriginalList()){
            for (String o : other.getOriginalList()){
                if (t.equals(o)){
                    continue;
                }
                diff.add(t);
            }
        }

        return new ResizeableArrayBag(diff);
    }

    /**
     * A getter method to retrieve the original ArrayList.
     *
     * @return An ArrayList of Strings of the original ArrayList.
     */
    public ArrayList<String> getOriginalList(){
        return this.originalList;
    }

    /**
     * A setter method which changes the contents of the original bag.
     * This setter also returns the new bag.
     *
     * @param toSet The ArrayList we are setting our bag to
     * @return The ArrayList we set our bag to
     */
    public ArrayList<String> setOriginalList(ArrayList<String> toSet){
        this.originalList = toSet;

        return toSet;
    }

    public String toString(){
        return getOriginalList().toString();
    }

}

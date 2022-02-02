/**
 * This is the BagInterface interface which defines the Union, Intersection, and Difference methods.
 *
 *
 * @author George Matta
 * @version 1.0
 */
public interface BagInterface{

    public ResizeableArrayBag union(ResizeableArrayBag other);
    public ResizeableArrayBag intersection(ResizeableArrayBag other);
    public ResizeableArrayBag difference(ResizeableArrayBag other);

}
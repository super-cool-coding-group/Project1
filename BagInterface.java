/**
 * This is the BagInterface interface which defines the Union, Intersection, and Difference methods.
 *
 *
 * @author George Matta
 * @version 1.0
 */
public interface BagInterface<T>{

    public T union(T other);
    public T intersection(T other);
    public T difference(T other);

}
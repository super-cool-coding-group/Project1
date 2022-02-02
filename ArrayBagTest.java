import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the where we test the functionality of our ResizeableArrayBag class.
 *
 * We have a main method where we run the Union, Intersection, and Difference methods.
 *
 *
 * @author George Matta
 * @version 1.0
 */
public class ArrayBagTest{

    public static void main(String[] args){
        ArrayList<String> listOne = new ArrayList<>(Arrays.asList("a", "b", "c"));
        ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("b", "b", "d", "e"));

        ResizeableArrayBag bagOne = new ResizeableArrayBag(listOne);
        ResizeableArrayBag bagTwo = new ResizeableArrayBag(listTwo);

        // Order is unimportant
        System.out.println(bagOne.union(bagTwo)); // a b b b c d e
        System.out.println(bagOne.intersection(bagTwo)); // b
        System.out.println(bagOne.difference(bagTwo)); // b d e


    }

}
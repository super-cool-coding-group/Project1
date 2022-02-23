import java.util.ArrayList;
import java.util.Arrays;

public class LinkedBagTest {
    
    public static void main(String[] args) {
        
        ArrayList<String> listOne = new ArrayList<>(Arrays.asList("a", "b", "c")); // The first list that we create a bag from
        ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("b", "b", "d", "e")); // The second list that we create a bag from

        LinkedBag<String> bagOne = new LinkedBag<String>(listOne); // Our first bag
        LinkedBag<String> bagTwo = new LinkedBag<String>(listTwo); // Our second bag

        System.out.println("Union");
        System.out.println(bagOne.union(bagTwo)); // a b b b c d e
        System.out.println(bagTwo.union(bagOne)); // a b b b c d e

        // Intersection
        System.out.println("\nIntersection");
        System.out.println(bagOne.intersection(bagTwo)); // b
        System.out.println(bagTwo.intersection(bagOne)); // b

        // Difference
        System.out.println("\nDifference");
        System.out.println(bagOne.difference(bagTwo)); // a c
        System.out.println(bagTwo.difference(bagOne)); // b d e
    }
}

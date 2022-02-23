import java.util.ArrayList;
import java.util.Arrays;

public class bagDriver {

    public static void main(String[] args) {
        String[] arrayOne = { "a", "b", "c" }; // The first list that we create a bag from
        String[] arrayTwo = { "b", "b", "d", "e" }; // The second list that we create a bag from

        ResizeableArrayBag<String> bagOne = new ResizeableArrayBag<String>(arrayOne); // Our first bag
        ResizeableArrayBag<String> bagTwo = new ResizeableArrayBag<String>(arrayTwo); // Our second bag

        checkStuff(bagOne);

        // Order is unimportant
        // Union
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

        ArrayList<String> listOne = new ArrayList<>(Arrays.asList("a", "b", "c")); // The first list that we create a
                                                                                   // bag from
        ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("b", "b", "d", "e")); // The second list that we
                                                                                        // create a bag from
        LinkedBag<String> linkedBagOne = new LinkedBag<String>(listOne); // Our first bag
        LinkedBag<String> linkedBagTwo = new LinkedBag<String>(listTwo); // Our second bag

        System.out.println("Union");
        System.out.println(linkedBagOne.union(linkedBagTwo)); // a b b b c d e
        System.out.println(linkedBagTwo.union(linkedBagOne)); // a b b b c d e

        // Intersection
        System.out.println("\nIntersection");
        System.out.println(linkedBagOne.intersection(linkedBagTwo)); // b
        System.out.println(linkedBagTwo.intersection(linkedBagOne)); // b

        // Difference
        System.out.println("\nDifference");
        System.out.println(linkedBagOne.difference(linkedBagTwo)); // a c
        System.out.println(linkedBagTwo.difference(linkedBagOne)); // b d e

    }

    public static void checkStuff(ResizeableArrayBag<String> bag) {
        System.out.println(bag);
        System.out.println(bag.getNumOfEntries());
        bag.add("1");
        System.out.println(bag);
        System.out.println(bag.getNumOfEntries());
        bag.add("1");
        System.out.println(bag);
        System.out.println(bag.getNumOfEntries());
        bag.remove("1");
        System.out.println(bag);
        System.out.println(bag.getNumOfEntries());
        bag.remove("1");
        System.out.println(bag);
        System.out.println(bag.getNumOfEntries());
    }
}

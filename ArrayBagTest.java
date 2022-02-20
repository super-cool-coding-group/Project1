/**
 * This is the where we test the functionality of our ResizeableArrayBag class.
 *
 * We have a main method where we run the Union, Intersection, and Difference methods.
 *
 *
 * @author George Matta
 * @version 1.1
 */
public class ArrayBagTest{
    /**
     * The main method in which we test our Union, Intersection, and Difference methods on permutations of two Bags
     *
     * The two bags we are using are a bag that contains the elements a, b, and c and the second contains b, b, d, and e
     *
     * @param args
     */
    public static void main(String[] args){
        String[] arrayOne = {"a", "b", "c"}; // The first list that we create a bag from
        String[] arrayTwo = {"b", "b", "d", "e"}; // The second list that we create a bag from

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
    }

    public static void checkStuff(ResizeableArrayBag<String> bag){
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
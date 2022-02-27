import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * This is the where we test the functionality of our ResizeableArrayBag class.
 *
 * We have a main method where we run the Union, Intersection, and Difference methods.
 *
 *
 * @author George Matta
 * @author Angelica Arteaga
 * @version 1.2
 */
public class ArrayBagTest{

    BagInterface<Character> testBag = new ResizeableArrayBag<Character>();
    BagInterface<Character> testBag2 = new ResizeableArrayBag<Character>();
    BagInterface<Character> outcome;
    BagInterface<Character> outcome2;

    /**
     * Creates a new ResizeableArrayBag given an Array.
     * @param bag The Array we are using to create a bag.
     */
    @Test
    public void testResizeableArrayBag(){
        Character[] testArray = {'a', 'b', 'c'};
        testBag = new ResizeableArrayBag<Character>(testArray);
        assertTrue(testBag.contains('a'));
        assertTrue(testBag.contains('b'));
        assertTrue(testBag.contains('c'));
        int expectedSize = 3;
        assertEquals(expectedSize, testBag.getCurrentSize());
    }

    /**
     * Creates a new ResizeableArrayBag with the default capacity.
     */
    @Test
    public void testResizeableArrayBag2(){
        testBag = new ResizeableArrayBag<Character>();
        int expectedCapacity = 10;
        assertEquals(expectedCapacity, testBag.getNumOfEntries());
    }

    /**
     * Creates an array of generics of a variable capacity
     * @param capacity The maximum capacity of the Array
     */
    @Test
    public void testResizeableArrayBag3(){

    }

    @Test
    public void testGetCurrentSize() {
        testBag.add('a');
        testBag.add('b');
        int expected = 2;
        assertEquals(expected, testBag.getCurrentSize());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(testBag.isEmpty());
        testBag.add('a');
        assertFalse(testBag.isEmpty());
    }
     
    @Test
    public void testAdd(){
        testBag.add('a');
        assertTrue(testBag.contains('a'));
    }

    @Test // A method to remove the last item from a ResizeableArrayBag's bag
    public void testRemove(){
        testBag.add('a');
        testBag.add('b');
        testBag.remove();
        assertFalse(testBag.contains('b'));
        assertTrue(testBag.contains('a'));
    }

    @Test // A method to remove a specified item from a ResizeableArrayBag's bag
    public void testRemove2(){
        testBag.add('a');
        testBag.add('b');
        testBag.remove('b');
        assertFalse(testBag.contains('b'));
        assertTrue(testBag.contains('a'));
    }

    @Test
    public void testClear(){
        testBag.add('a');
        testBag.add('b');
        testBag.clear();
        assertEquals(0, testBag.getCurrentSize());
    }

    @Test
    public void testContains() {
        testBag.add('a');
        assertTrue(testBag.contains('a'));
        assertFalse(testBag.contains('d'));
    }

    @Test
    public void testToArray(){
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        Character[] expected = {'a', 'b', 'c'};
        assertArrayEquals(expected, testBag.toArray());
    }

    @Test
    public void testToString(){
        testBag.add('a');
        testBag.add('b');
        String expected = "[a, b]";
        assertEquals(expected, testBag.toString());
    }

    @Test
    public void testUnion(){
        testBag.add('a');
        testBag.add('b');
        testBag2.add('c');
        testBag2.add('d');
       outcome = testBag.union(testBag2);
        assertTrue(outcome.contains('a'));
        assertTrue(outcome.contains('b'));
        assertTrue(outcome.contains('c'));
        assertTrue(outcome.contains('d'));
        //System.out.println(testBag.union(testBag2));
    }

    @Test
    public void testDifference(){
        testBag.add('a');
        testBag.add('b');
        testBag2.add('b');
        testBag2.add('c');
        outcome = testBag.difference(testBag2);
        outcome2 = testBag2.difference(testBag);
        assertTrue(outcome.contains('a'));
        assertTrue(outcome2.contains('c'));
        assertFalse(outcome.contains('b'));
        assertFalse(outcome2.contains('b'));
        //System.out.println(testBag.difference(testBag2));
        //System.out.println(testBag2.difference(testBag));
    }

    @Test
    public void testIntersection(){
        testBag.add('a');
        testBag.add('b');
        testBag2.add('b');
        testBag2.add('c');
        outcome = testBag.intersection(testBag2);
        assertTrue(outcome.contains('b'));
        assertFalse(outcome.contains('a'));
        //System.out.println(testBag.intersection(testBag2));
    }
}
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.Test;
/**
 * This is the where we test the functionality of our LinkedBag class.
 * We have a main method where we run the Union, Intersection, and Difference methods.
 * @author Angelica Arteaga
 * @version 1.2
 */

public class LinkedBagTest {

    BagInterface<Character> testBag = new LinkedBag<Character>();
    BagInterface<Character> testBag2 = new LinkedBag<Character>();

    @Test //Done
    public void testAdd() {
        testBag.add('a');
        assertTrue(testBag.contains('a'));
    }

    @Test //Done
    public void testContains() {
        testBag.add('a');
        assertTrue(testBag.contains('a'));
        assertFalse(testBag.contains('d'));
    }

    @Test //Done
    public void testToArray() {
        testBag.add('c');
        testBag.add('b');
        testBag.add('a');
        Character[] expected = {'a', 'b', 'c'};
        assertArrayEquals(expected, testBag.toArray());
    }

    @Test //Done
    public void testClear() {
        testBag.add('a');
        testBag.add('b');
        int expected = 2;
        assertEquals(expected, testBag.getCurrentSize());
        testBag.clear();
        int expected2 = 0;
        assertEquals(expected2, testBag.getCurrentSize());
    }

    @Test //Done
    public void testGetCurrentSize() {
        testBag.add('a');
        testBag.add('b');
        int expected = 2;
        assertEquals(expected, testBag.getCurrentSize());
    }

    @Test //Done
    public void testGetFrequencyOf() {
        testBag.add('a');
        testBag.add('a');
        testBag.add('b');
        int expected = 2;
        assertEquals(expected, testBag.getFrequencyOf('a'));
        int expected2 = 1;
        assertEquals(expected2, testBag.getFrequencyOf('b'));
    }

    @Test //Done
    public void testIsEmpty() {
        assertTrue(testBag.isEmpty());
        testBag.add('a');
        assertFalse(testBag.isEmpty());
    }

    @Test //Done
    public void testRemove() {
        testBag.add('a');
        assertTrue(testBag.contains('a')); 
        testBag.remove('a');
        assertFalse(testBag.contains('a')); 
    }

    @Test //Work on remove 2
    public void testRemove2() {
        
    }
    @Test //Fix toString test
    public void testToString() {
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        System.out.println(testBag.toString());
    }
    
    @Test //Done
    public void testUnion() {
        testBag.add('a');
        testBag.add('b');
        testBag2.add('c');
        testBag2.add('d');
        BagInterface<Character> outcome = testBag.union(testBag2);
        assertTrue(outcome.contains('a'));
        assertTrue(outcome.contains('b'));
        assertTrue(outcome.contains('c'));
        assertTrue(outcome.contains('d'));
        //System.out.println(testBag.union(testBag2)); 
    }

    @Test //Done
    public void testDifference() {
        testBag.add('a');
        testBag.add('b');
        testBag2.add('b');
        testBag2.add('c');
        BagInterface<Character> outcome = testBag.difference(testBag2);
        BagInterface<Character> outcome2 = testBag2.difference(testBag);
        assertTrue(outcome.contains('a'));
        assertTrue(outcome2.contains('c'));
        assertFalse(outcome.contains('b'));
        assertFalse(outcome2.contains('b'));
        //System.out.println(testBag.difference(testBag2));
        //System.out.println(testBag2.difference(testBag));
    }

    @Test //Done
    public void testIntersection() {
        testBag.add('a');
        testBag.add('b');
        testBag2.add('b');
        testBag2.add('c');
        BagInterface<Character> outcome = testBag.intersection(testBag2);
        assertTrue(outcome.contains('b'));
        assertFalse(outcome.contains('a'));
        //System.out.println(testBag.intersection(testBag2));
    }

}

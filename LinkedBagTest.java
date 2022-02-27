import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import java.util.ArrayList;
import org.junit.Test;
/**
 * This is the where we test the functionality of our LinkedBag class.
 * We have a main method where we run the Union, Intersection, and Difference methods.
 * @author Pierlorenzo Peruzzo
 * @author Angelica Arteaga
 * @version 2.0
 */

public class LinkedBagTest {

    BagInterface<Character> testBag = new LinkedBag<Character>();
    BagInterface<Character> testBag2 = new LinkedBag<Character>();
    BagInterface<Character> outcome;
    BagInterface<Character> outcome2;

    @Test
    //initialized with the contents of another bag
    public void testLinkedBag(){
        testBag.add('a');
        testBag.add('b');
        testBag2 = new LinkedBag<Character>(testBag);
        assertTrue(testBag2.contains('a'));
        assertTrue(testBag2.contains('b'));
        int expectedSize = 2;
        assertEquals(expectedSize, testBag2.getCurrentSize());
    }

    @Test
    // initialized with the contents of an ArrayList
    public void testLinkedBag2(){
        ArrayList<Character> testArray = new ArrayList<Character>();
        testArray.add('a');
        testArray.add('b');
        testBag = new LinkedBag<Character>(testArray);
        assertTrue(testBag.contains('a'));
        assertTrue(testBag.contains('b'));
        int expectedSize = 2;
        assertEquals(expectedSize, testBag.getCurrentSize());
    }

    @Test
    public void testAdd() {
        testBag.add('a');
        assertTrue(testBag.contains('a'));
    }

    @Test
    public void testContains() {
        testBag.add('a');
        assertTrue(testBag.contains('a'));
        assertFalse(testBag.contains('d'));
    }

    @Test
    public void testToArray() {
        testBag.add('c');
        testBag.add('b');
        testBag.add('a');
        Character[] expected = {'a', 'b', 'c'};
        assertArrayEquals(expected, testBag.toArray());
    }

    @Test
    public void testClear() {
        testBag.add('a');
        testBag.add('b');
        testBag.clear();
        assertEquals(0, testBag.getCurrentSize());
    }

    @Test
    public void testGetCurrentSize() {
        testBag.add('a');
        testBag.add('b');
        int expected = 2;
        assertEquals(expected, testBag.getCurrentSize());
    }

    @Test
    public void testGetFrequencyOf() {
        testBag.add('a');
        testBag.add('a');
        testBag.add('b');
        int expected = 2;
        assertEquals(expected, testBag.getFrequencyOf('a'));
        int expected2 = 1;
        assertEquals(expected2, testBag.getFrequencyOf('b'));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(testBag.isEmpty());
        testBag.add('a');
        assertFalse(testBag.isEmpty());
    }

    @Test
    public void testSearch(){
        testBag.add('a');
        testBag.add('b');
        int expected = 1;
        assertEquals(expected, testBag.search('b'));
        assertEquals(null, testBag.search('c'));
    }

    @Test
    // remove method w/o parameters
    public void testRemove() {
        testBag.add('a');
        testBag.add('b');
        testBag.remove();
        assertFalse(testBag.contains('b'));
    }

    @Test
    // remove method w/ parameters
    public void testRemove2() {
        testBag.add('a');
        testBag.remove('a');
        assertFalse(testBag.contains('a'));
    }

    @Test
    public void testToString() {
        testBag.add('b');
        testBag.add('a');
        String expected = "[a, b]";
        assertEquals(expected, testBag.toString());
    }

    @Test
    public void testUnion() {
        testBag.add('a');
        testBag.add('b');
        testBag2.add('c');
        testBag2.add('d');
        outcome = testBag.union(testBag2);
        assertTrue(outcome.contains('a'));
        assertTrue(outcome.contains('b'));
        assertTrue(outcome.contains('c'));
        assertTrue(outcome.contains('d'));
    }

    @Test
    public void testDifference() {
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
    }

    @Test
    public void testIntersection() {
        testBag.add('a');
        testBag.add('b');
        testBag2.add('b');
        testBag2.add('c');
        outcome = testBag.intersection(testBag2);
        assertTrue(outcome.contains('b'));
        assertFalse(outcome.contains('a'));
        assertFalse(outcome.contains('c'));
    }

}

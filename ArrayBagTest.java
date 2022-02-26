import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * This is the where we test the functionality of our ResizeableArrayBag class.
 *
 * We have a main method where we run the Union, Intersection, and Difference methods.
 *
 *
 * @author George Matta, Angelica Arteaga
 * @version 1.2
 */
public class ArrayBagTest{

    BagInterface<Character> testBag = new ResizeableArrayBag<Character>();
    BagInterface<Character> testBag2 = new ResizeableArrayBag<Character>();

    @Test
    public void testUnion(){
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

    @Test
    public void testDifference(){
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

    @Test
    public void testIntersection(){
        testBag.add('a');
        testBag.add('b');
        testBag2.add('b');
        testBag2.add('c');
        BagInterface<Character> outcome = testBag.intersection(testBag2);
        assertTrue(outcome.contains('b'));
        assertFalse(outcome.contains('a'));
        //System.out.println(testBag.intersection(testBag2));
    }

    @Test
    public void testSetBag(){

    }
    
    @Test
    public void testGetBag(){

    }

    @Test
    public void testGetDefaultCapacity(){

    }

    @Test
    public void testCapacity(){

    }

    @Test
    public void testGetNumOfEntries(){

    }

    @Test
    public void testGetMaxEntries(){

    }

    @Test
    public void testCalculateNumOfEntries(){

    }

    @Test
    public void testAdd(){
        testBag.add('a');
        assertTrue(testBag.contains('a'));
    }

    @Test
    public void testRemove(){
        testBag.add('a');
        assertTrue(testBag.contains('a')); 
        testBag.remove('a');
        assertFalse(testBag.contains('a')); 
    }

    @Test
    public void testRemove2(){
        testBag.add('a');
        testBag.add('b');
        testBag.remove();
        assertFalse(testBag.contains('a'));
    }

    @Test
    public void testRemove3(){
        
    }

    @Test
    public void testGetIndexOf(){

    }

    @Test
    public void testResizeBag(){

    }

    @Test
    public void testToArray(){
        testBag.add('c');
        testBag.add('b');
        testBag.add('a');
        Character[] expected = {'a', 'b', 'c'};
        assertArrayEquals(expected, testBag.toArray());
    }

    @Test
    public void testToSortedArrayList(){

    }

    @Test
    public void testToString(){

    }

}
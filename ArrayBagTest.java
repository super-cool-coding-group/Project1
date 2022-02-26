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
 * @author George Matta, Angelica Arteaga
 * @version 1.2
 */
public class ArrayBagTest{

    BagInterface<Character> testBag = new ResizeableArrayBag<Character>();
    BagInterface<Character> testBag2 = new ResizeableArrayBag<Character>();

    @Test
    public void testAdd(){
        testBag.add('a');
        assertTrue(testBag.contains('a'));
    }

    @Test
    public void testSetBag(){
        Character[] testArray = {'d', 'e', 'f'};
        ((ResizeableArrayBag<Character>) testBag).setBag(testArray);
    }
    
    @Test
    public void testGetBag(){

    }

    @Test
    public void testGetDefaultCapacity(){
        int expected = 10;
        assertEquals(expected, testBag.getDefaultCapacity());
    }

    @Test
    public void testGetCapacity(){
        int expected;
        assertEquals(expected, testBag.getCapacity());
    }

    @Test
    public void testGetNumOfEntries(){
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        int expected = 3;
        assertEquals(expected, testBag.getNumOfEntries());
    }

    @Test
    public void testGetMaxEntries(){

    }

    @Test
    public void testCalculateNumOfEntries(){
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        int expected = 3;
        assertEquals(expected, testBag.calculateNumOfEntries());
    }

    @Test // A method to remove the last item from a ResizeableArrayBag's bag
    public void testRemove(){
        testBag.add('a');
        testBag.add('b');
        testBag.remove();
        assertFalse(testBag.contains('b')); 
        assertTrue(testBag.contains('a'));
    }

    @Test // A method to remove an item from a ResizeableArrayBag's bag given an index
    public void testRemove2(){
        testBag.add('a');
        testBag.add('b');
        testBag.remove(1);
        assertFalse(testBag.contains('b'));
        assertTrue(testBag.contains('a'));
    }

    @Test // A method to remove a specified item from a ResizeableArrayBag's bag
    public void testRemove3(){
        testBag.add('a');
        testBag.add('b');
        testBag.remove('b');
        assertFalse(testBag.contains('b'));
        assertTrue(testBag.contains('a'));
    }

    @Test
    public void testGetIndexOf(){
        testBag.add('a');
        testBag.add('b');
        testBag.add('b');
        int expected = 1;
        assertEquals(testBag.getIndexOf('b'));
    }

    @Test
    public void testResizeBag(){

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
    public void testToSortedArrayList(){

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
}
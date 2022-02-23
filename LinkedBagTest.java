import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

public class LinkedBagTest {

    BagInterface<Character> testBag = new LinkedBag<Character>();
    BagInterface<Character> testBag2 = new LinkedBag<Character>();

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
        int expected = 2;
        assertEquals(expected, testBag.getCurrentSize());
        testBag.clear();
        int expected2 = 0;
        assertEquals(expected2, testBag.getCurrentSize());
    }
    
    //fix difference test
    @Test
    public void testDifference() {
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        testBag2.add('c');
        testBag2.add('d');
        testBag2.add('e');
        BagInterface<Character> outcome = testBag.difference(testBag2);
        System.out.println(testBag.difference(testBag2));
        assertTrue(outcome.contains('a'));
        assertTrue(outcome.contains('b'));
        assertFalse(outcome.contains('c'));
        assertFalse(outcome.contains('d'));
        assertFalse(outcome.contains('e'));



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
    }

    @Test
    public void testIntersection() {
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        testBag2.add('b');
        testBag2.add('c');
        testBag2.add('d');
        System.out.println(testBag.intersection(testBag2));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(testBag.isEmpty());
        testBag.add('a');
        assertFalse(testBag.isEmpty());
    }

    @Test
    public void testRemove() {
        testBag.add('a');
        testBag.remove('a');
        assertFalse(testBag.contains('a')); 
    }

    @Test
    public void testRemove2() {
        
    }
    // fix toString test
    @Test
    public void testToString() {
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        System.out.println(testBag.toString());
        
    }
    //fix union test
    @Test
    public void testUnion() {
        testBag.add('a');
        testBag.add('b');
        testBag.add('c');
        testBag2.add('c');
        testBag2.add('d');
        testBag2.add('e');
        System.out.println(testBag.union(testBag2)); 
    }

}

package org.howard.edu.lsp.assignment6;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class IntegerSetTest {
	
	
	@Test
    public void testAdd() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(1); // duplicate
        assertEquals(1, set.length()); // should still be 1
    }
	
	@Test
	public void testClear() {
		IntegerSet set = new IntegerSet();
		set.add(1);
		set.clear();
		assertEquals(0,set.length());
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void testClearEmptySet() {
		IntegerSet set = new IntegerSet();
		set.clear();
		assertEquals(0,set.length());
		assertTrue(set.isEmpty());
	}
	
	@Test
	public void testIsEmpty() {
		IntegerSet set = new IntegerSet();
		assertTrue(set.isEmpty());
		set.add(1);
	    set.add(2);
	    assertFalse(set.isEmpty());
	}
	
	
	@Test
	public void testLength() {
		IntegerSet set = new IntegerSet();
	    set.add(1);
	    set.add(2);
	    assertEquals(2, set.length());
		
	}
	
	@Test
	public void testLengthEmptySet() {
		IntegerSet set = new IntegerSet();
	    assertEquals(0, set.length());
		
	}
	
	@Test
	public void testEquals() {
		IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        setB.add(1);
        setB.add(3);
        assertTrue(setA.equals(setB));
		
	}
	
	@Test
	public void testEqualsDifferentOrder() {
		IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        setB.add(3);
        setB.add(1);
        assertTrue(setA.equals(setB));
	}
	
	@Test
	public void testUnequalSets() {
		IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        setB.add(1);
        setB.add(3);
        setB.add(2);
        assertFalse(setA.equals(setB));
	}
	
	@Test
	public void testContains() {
		IntegerSet testSet = new IntegerSet();
		testSet.add(15);
		testSet.add(100);
		assertTrue(testSet.contains(15));
		assertFalse(testSet.contains(150));
	}
	
	@Test
	public void testLargest() {
		IntegerSet testSet = new IntegerSet();
		testSet.add(15);
		testSet.add(100);
		testSet.add(101);
		assertEquals(101,testSet.largest());
		
	}
	
	@Test
	public void testSmallest() {
		IntegerSet testSet = new IntegerSet();
		testSet.add(15);
		testSet.add(100);
		testSet.add(101);
		testSet.add(-101);
		assertEquals(-101,testSet.smallest());
		
	}
	
	@Test
	public void testLargestSingleElement() {
		IntegerSet testSet = new IntegerSet();
		testSet.add(15);
		assertEquals(15,testSet.largest());
		
	}
	
	@Test
	public void testSmallestSingleElement() {
		IntegerSet testSet = new IntegerSet();
		testSet.add(15);
		assertEquals(15,testSet.smallest());
		
	}
	
	
	
	@Test
	public void testSmallestThrowsException() {
		IntegerSet testSet = new IntegerSet();
		assertThrows(NoSuchElementException.class, () -> {
	        testSet.smallest();
	    });
		
	}
	
	@Test
	public void testLargestThrowsException() {
		IntegerSet testSet = new IntegerSet();
		assertThrows(NoSuchElementException.class, () -> {
	        testSet.largest();
	    });
		
	}

	@Test
	public void testUnion() {
		IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        setB.add(2);
        setB.add(1);
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        assertTrue(expected.equals(setA.union(setB)));
	}
	
	@Test
	public void testUnionEmptySets() {
		IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        IntegerSet expected = new IntegerSet();
        expected.add(1);
        expected.add(3);

        assertTrue(expected.equals(setA.union(setB)));
	}
        
    @Test
    public void testIntersect() {
    	
    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        setB.add(2);
        setB.add(1);
        IntegerSet expected1 = new IntegerSet();
        expected1.add(1);
        assertTrue(expected1.equals(setA.intersect(setB)));
     
    }
    
    @Test
    public void testIntersectDisjoint() {
    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setC = new IntegerSet();
    	IntegerSet expected2 = new IntegerSet();
    	assertTrue(expected2.equals(setA.intersect(setC)));
    }
    
    @Test
    public void testDiff() {

    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        setB.add(2);
        setB.add(1);
        IntegerSet expected = new IntegerSet();
        expected.add(3);
        assertTrue(expected.equals(setA.diff(setB)));
    }
    
    @Test
    public void testDiffSameSet() {

    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet expected = new IntegerSet();
        assertTrue(expected.equals(setA.diff(setA)));
    }
    
    @Test
    public void testComplement() {
    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        setB.add(2);
        setB.add(1);
        IntegerSet expected = new IntegerSet();
        expected.add(2);
        assertTrue(expected.equals(setA.complement(setB)));
    	
    }
    
    @Test
    public void testComplementDisjoint() {
    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        IntegerSet setB = new IntegerSet();
        setB.add(2);
        setB.add(4);
        IntegerSet expected = new IntegerSet();
        expected.add(2);
        expected.add(4);
        assertTrue(expected.equals(setA.complement(setB)));
    	
    }
    
    @Test
    public void testToString() {
    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(3);
        setA.add(2);
        assertEquals("[1, 2, 3]", setA.toString());	
    }
    
    @Test
    public void testToStringEmptyInput() {
    	IntegerSet setA = new IntegerSet();
      
        assertEquals("[]", setA.toString());	
    }
    
    @Test
    public void testRemove() {
    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(2);
        setA.remove(1);
        assertEquals(1,setA.length());
        setA.remove(2);
        assertTrue(setA.isEmpty());
    }
    
    @Test
    public void testRemoveAbsentElement() {
    	IntegerSet setA = new IntegerSet();
        setA.add(1);
        setA.add(2);
        setA.remove(10);   
        assertEquals(2,setA.length());
        
    }
   
	
}


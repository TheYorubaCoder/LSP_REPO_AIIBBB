package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * The IntegerSet class represents a mathematical set of integers.
 * It provides various operations such as union, intersection, and difference.
 * 
 * @author Ibukunoluwa Adeloye
 */

public class IntegerSet {
	
	private ArrayList<Integer> setList = new ArrayList<Integer>();
	
	/**
	 * Clears the internal representation of the set, making it empty.
	 */
	public void clear() {
		setList.clear();
	}
	
	/**
	 * Returns the number of elements in the set.
	 * @return the size of the set.
	 */
	public int length() {
		return setList.size();
	}

	/**
	 * Returns true if the set contains the same elements as the provided IntegerSet.
	 * The order of elements does not matter.
	 * @param b the IntegerSet to compare with.
	 * @return true if the sets are equal, false otherwise.
	 */
	public boolean equals(IntegerSet b) {
		ArrayList<Integer> sortedA = new ArrayList<>(setList);
	    ArrayList<Integer> sortedB = new ArrayList<>(b.setList);
	    Collections.sort(sortedA);
	    Collections.sort(sortedB);
	    return sortedA.equals(sortedB);
	}
	
	/**
	 * Checks whether a specific value is present in the set.
	 * @param value the integer value to check for.
	 * @return true if the value is in the set, false otherwise.
	 */
	public boolean contains(int value) {
		return setList.contains(value);
	}

	/**
	 * Returns the largest item in the set.
	 * @return the maximum integer in the set.
	 * @throws NoSuchElementException if the set is empty.
	 */
	public int largest() {
		if (setList.isEmpty()) throw new NoSuchElementException("Set is empty");
		return Collections.max(setList);
	}

	/**
	 * Returns the smallest item in the set.
	 * @return the minimum integer in the set.
	 * @throws NoSuchElementException if the set is empty.
	 */
	public int smallest() {
		if (setList.isEmpty()) throw new NoSuchElementException("Set is empty");
		return Collections.min(setList);
	}

	/**
	 * Adds an item to the set if it is not already present.
	 * @param item the integer to add.
	 */
	public void add(int item) {
		if (setList.contains(item)){
			return;
		}
		else {
			setList.add(item);
		}
	}

	/**
	 * Removes an item from the set if it exists.
	 * @param item the integer to be removed.
	 */
	public void remove(int item) {
		if (setList.contains(item)){
			setList.remove(Integer.valueOf(item));
		}
	}

	/**
	 * Performs the union of this set and another set.
	 * @param intSetb the set to union with.
	 * @return a new IntegerSet containing all unique elements from both sets.
	 */
	public IntegerSet union(IntegerSet intSetb) {
		IntegerSet result = new IntegerSet();
	    for (int item : setList) {
	        result.add(item);
	    }
	    for (int item : intSetb.setList) {
	        result.add(item);
	    }
	    return result;
	}
	
	/**
	 * Performs the intersection of this set and another set.
	 * @param intSetb the set to intersect with.
	 * @return a new IntegerSet containing only elements present in both sets.
	 */
	public IntegerSet intersect(IntegerSet intSetb) {
		IntegerSet result = new IntegerSet();
		
		for (int item : setList) {
			if(intSetb.contains(item)) {
				result.add(item);
			}
		}
		return result;	
	}
	
	/**
	 * Performs the set difference (this - intSetb).
	 * @param intSetb the set whose elements are to be excluded.
	 * @return a new IntegerSet containing elements in this set but not in intSetb.
	 */
	public IntegerSet diff(IntegerSet intSetb) {
		IntegerSet result = new IntegerSet();
		for (int item : setList) {
			if(!intSetb.contains(item)) {
				result.add(item);
			}
		}
		return result;
	}

	/**
	 * Returns the complement of this set with respect to another set.
	 * @param intSetb the set to compare against.
	 * @return a new IntegerSet containing elements in intSetb that are not in this set.
	 */
	public IntegerSet complement(IntegerSet intSetb) {
		IntegerSet result = new IntegerSet();
		for (int item : intSetb.setList) {
			if(!setList.contains(item)) {
				result.add(item);
			}
		}
		return result;
	}
	
	/**
	 * Returns true if the set contains no elements.
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return setList.isEmpty();
	}
	
	/**
	 * Returns a string representation of the set in sorted order.
	 * @return a string representing the elements of the set.
	 */
	@Override
	public String toString() {
	    Collections.sort(setList);
	    return setList.toString();
	}
}

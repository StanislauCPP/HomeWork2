package containers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**Analog of ArrayList */
public class MyArrayList <E> {
	private Object[] array;

	public MyArrayList(int capacity) { array = new Object[capacity]; }

	/**
	 * Change element from position by index
	 * @param index position in array
	 * @param element set element
	 * @return previous element by position
	 */
	public E set(int index, E element) {
		E e = (E) array[index];
		array[index] = element;

		return e;
	}

	/**
	 * Get element from position by index
	 * @param index position in array
	 * @return element by position
	 */
	public E get(int index) { return ((E) array[index]); }

	/**
	 * Add element in the end of array
	 * @param element addable element
	 * @return answer for result of operation
	 */
	public boolean add(E element) {
		Object[] copy = Arrays.copyOf(array, array.length + 1);
		copy[array.length] = element;
		array = copy;

		return true;
	}

	/**Function for shift right part of array (we get this part by needed index)
	 * to right - for function add, to left - for function remove*/
	private void arrayFromToParts(int newLength, int leftEnd, int rightStart, int commonIndexForRight) {
		Object[] copyLeft = Arrays.copyOfRange(array, 0, leftEnd);
		Object[] copyRight = Arrays.copyOfRange(array, rightStart, array.length);

		array = new Object[newLength];

		System.arraycopy(copyLeft, 0, array, 0, copyLeft.length);
		System.arraycopy(copyRight, 0, array, commonIndexForRight, copyRight.length);
	}

	/**
	 * Add element in the position by index
	 * @param index position in array
	 * @param element addable element
	 */
	public void add(int index, E element) {
		get(index);														//checking out of range

		arrayFromToParts(array.length + 1, index, index, index + 1);
		array[index] = element;
	}

	/**
	 * Remove element in the position by index
	 * @param index position in array
	 * @return removed element
	 */
	public E remove(int index) {
		E removingElement = get(index);					//checking out of range and get removing element

		arrayFromToParts(array.length - 1, index, index + 1, index);
		return removingElement;
	}

	/**
	 * Remove element if it's searched
	 * @param element removable element
	 * @return answer for result of operation
	 */
	public boolean remove(E element) {
		if(element == null) {
			for(int i = 0; i < array.length; ++i)
				if(array[i] == null) {
					remove(i);
					return true;
				}
		}
		else {
			for(int i = 0; i < array.length; ++i)
				if(element.equals(get(i))) {
					remove(i);
					return true;
				}
		}

		return false;
	}

	/**
	 * Add collection at the end of array
	 * @param c adding collection
	 * @return answer for result of operation
	 */
	public boolean addAll(Collection<? extends E> c) {
		Object[] copy = Arrays.copyOf(array, array.length + c.size());

		int i = array.length;
		for(Iterator<? extends E> it = c.iterator(); it.hasNext(); ++i)
			copy[i] = it.next();

		array = copy;

		return true;
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		get(index);														//checking out of range

		arrayFromToParts(array.length + c.size(), index, index, index + c.size());

		int i = index;
		for(Iterator<? extends E> it = c.iterator(); it.hasNext(); ++i)
			array[i] = it.next();

		return true;
	}

}

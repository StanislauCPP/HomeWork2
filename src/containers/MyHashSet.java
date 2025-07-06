package containers;

import java.util.Arrays;

/** Analogue HashSet from util*/
public class MyHashSet<E> {
	private int capacity = 8, hashSize = 0;

	/**Raw forward list*/
	private MyNode<E>[] hash;

	/**
	 * Getting index by hash code from element
	 * @return index
	 * */
	private int indexByElement(E element) { return ((capacity - 1) & element.hashCode()); }

	public MyHashSet() { hash = new MyNode[capacity];	}

	private interface SkeletonStageOperation { void operation(MyNode node);	}

	/**Skeleton for second functions: add, remove, contain. For code optimization.
	 * Before use this function need to check element == null.
	 * @param element 						Searchable element
	 * @param emptyBucket 				Action if hash index point us to empty bucket
	 * @param searchedInBucket		Action if hash index point us to not empty bucket and we found element
	 * @param unSearchedInBucket	Action if hash index point us to not empty bucket and we not found element
	 * */

	/*
	Common skeleton structure

	 			hash index point us to empty bucket
	 			------------------------------------> element not found (return false)
	 			|
	 			|
	 			hash index point us to not empty bucket
	 			--------------
	 										|
	 										|----------------------> element found (return true)
	 										|----------------------> element not found (return true)

	* */

	private boolean SkeletonSearchElement(E element,
																				SkeletonStageOperation emptyBucket,
																				SkeletonStageOperation searchedInBucket,
																				SkeletonStageOperation unSearchedInBucket) {
		int i = indexByElement(element);
		if (hash[i] == null) {
			emptyBucket.operation(null);
			return false;
		}

		for(MyNode<E> node = hash[i];; node = node.next) {
			E v = node.value;

			if((v.hashCode() == element.hashCode()) && v.equals(element)) {
				searchedInBucket.operation(node);
				return true;
			}

			if(node.next == null) {
				unSearchedInBucket.operation(node);
				return false;
			}
		}
	}

	/**
	 * Checking: does hash contain element
	 * @param element Searchable element
	 * @return answer for checking
	 */
	public boolean contain(E element) {
		if (element == null)
			return false;

		return SkeletonSearchElement(element, node -> {}, node -> {}, node -> {});
	}

	/**
	 * Add element to hash
	 * @param element Added element
	 * @return result of adding
	 */
	public boolean add(E element) {
		if (element == null)
			return false;

		if (hashSize == capacity) {																		//Copy part
			capacity = capacity/2 + capacity;
			hash = Arrays.copyOf(hash, capacity);
		}

		return !SkeletonSearchElement(element,
				node -> { hash[indexByElement(element)] = new MyNode<>(element); ++hashSize; },
				node -> { node.value = element; },
				node -> { node.next = new MyNode<>(element);});
	}

	/**
	 * Remove element from hash
	 * @param element removable element
	 * @return result of removing
	 */
	public boolean remove(E element) {
		if (element == null)
			return false;

		return SkeletonSearchElement(element,
				node -> {},
				node -> {
																	int i = indexByElement(element);
																	MyNode<E> backNode = hash[i];
																	E backValue = backNode.value;
																	if(backValue.hashCode() == element.hashCode() && backValue.equals(element))
																		hash[i] = backNode.next;
																	else {																																								/*In this way we go down the list second time (first time occur in SkeletonSearchElement).
																																																												Bad way for optimization, but good way for code optimization*/
																		MyNode<E> it = hash[i];
																		while(!(it.value.hashCode() == element.hashCode() && it.value.equals(element))) {
																			backNode = it;
																			it = it.next;
																		}

																		backNode.next = it.next;																														//reference relinking
																	}

																	},
				node -> {});
	}
}
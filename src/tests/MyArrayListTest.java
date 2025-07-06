package tests;

import containers.MyArrayList;

import java.util.ArrayList;
import java.util.Iterator;

public class MyArrayListTest {
	public static void main(String[] args) {
		MyArrayListTest tester1 = new MyArrayListTest(new MyArrayList<Integer>(5), new Integer[]{ 11, 12, 13});
		tester1.addTest();

		MyArrayListTest tester2 = new MyArrayListTest(new MyArrayList<Integer>(5), new Integer[]{ 101, 102, 103});
		tester2.addByIndexTest();

		MyArrayListTest tester3 = new MyArrayListTest(new MyArrayList<Integer>(5), new Integer[]{ 1, 2});
		tester3.removeByIndexTest();

		MyArrayListTest tester4 = new MyArrayListTest(new MyArrayList<Integer>(5), new Integer[]{ 1, 4});
		tester4.removeByElement();

		MyArrayListTest tester5 = new MyArrayListTest(new MyArrayList<Integer>(5));
		tester5.addAllTest();

		MyArrayListTest tester6 = new MyArrayListTest(new MyArrayList<Integer>(5));
		tester6.addAllByIndexTest();
	}

	private MyArrayList<Integer> mArrList;
	private Integer[] elementsForTest;
	private ArrayList<Integer> collection;

	void setTestableObject() {
		for (int i = 0; i < 5; ++i)
			mArrList.set(i, i);

		System.out.println("\n\n**************************\nTestable objects");
		for (int i = 0; i < 5; ++i)
			System.out.print(mArrList.get(i) + "\t");
	}

	public MyArrayListTest(MyArrayList<Integer> mArrList, Integer[] elementsForTest) {
		this.mArrList = mArrList;
		this.elementsForTest = elementsForTest;

		setTestableObject();

		System.out.println("\nElements for test");
		for (int i = 0; i < elementsForTest.length; ++i)
			System.out.print(elementsForTest[i] + "\t");
	}

	public MyArrayListTest(MyArrayList<Integer> mArrList) {
		this.mArrList = mArrList;

		setTestableObject();

		ArrayList<Integer> collection = new ArrayList<Integer>(5);
		collection.add(201);
		collection.add(202);
		collection.add(203);

		this.collection = collection;

		System.out.println("\nElements for test");
		for (Iterator<Integer> it = collection.iterator(); it.hasNext();)
			System.out.println(it.next());
	}

	public void addTest()	{
		for (int i = 0; i < elementsForTest.length; ++i)
			mArrList.add(elementsForTest[i]);

		printAfterTesting();
	}

	public void addByIndexTest() {
		for (int i = 0; i < elementsForTest.length; ++i)
			mArrList.add(3, elementsForTest[i]);

		printAfterTesting();
	}

	void printAfterTesting() {
		System.out.println("\n\nAfter testing of function");
		for (int i = 0; i < 5 + elementsForTest.length; ++i)
			System.out.print(mArrList.get(i) + "\t");
	}

	public void removeByIndexTest() {
		for (int i = 0; i < elementsForTest.length; ++i)
			mArrList.remove((int) elementsForTest[i]);

		printAfterRemoveTesting();
	}

	public void removeByElement() {
		for (int i = 0; i < elementsForTest.length; ++i)
			mArrList.remove(elementsForTest[i]);

		printAfterRemoveTesting();
	}

	void printAfterRemoveTesting() {
		System.out.println("\n\nAfter testing of function");
		for (int i = 0; i < 5 - elementsForTest.length; ++i)
			System.out.print(mArrList.get(i) + "\t");
	}

	public void addAllTest() {
		mArrList.addAll(collection);

		printAfterAddAllTest();
	}

	public void addAllByIndexTest() {
		mArrList.addAll(3, collection);

		printAfterAddAllTest();
	}

	void printAfterAddAllTest() {
		System.out.println("\nAfter testing of function");
		for (int i = 0; i < 5 + collection.size(); ++i)
			System.out.print(mArrList.get(i) + "\t");
	}
}

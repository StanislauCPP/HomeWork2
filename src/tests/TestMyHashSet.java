package tests;
import containers.MyHashSet;


public class TestMyHashSet {
	public static void main(String[] args) {
		test();
	}

	public static void test() {

		class Tester <E> {
			E[] array;
			MyHashSet<E> hashSet;

			public Tester(E[] array) {
				this.array = array;

				hashSet = new MyHashSet<E>();

				System.out.println("\n" + this.toString());
				System.out.println("add test");
				for(E el: array)
					System.out.print(hashSet.add(el) + "\t");

				System.out.println("\ncontain test");
				for(E el: array)
					System.out.print(hashSet.contain(el) + "\t");

				System.out.println("\nremove test");																	//Supply array element for removing from array end
				for(int i = array.length - 1; i >=0; --i)
					System.out.print(hashSet.remove(array[i]) + "\t");

				System.out.println("\ncontain test after remove");
				for(E el: array)
					System.out.print(hashSet.contain(el) + "\t");

				System.out.println();
			}
		}

		Tester<Integer> tester = new Tester<Integer>(new Integer[]{ 1, 2, 5, 3, 37, 10, 15, 5, 37, 21 });
		Tester<Integer> collisonTester = new Tester<>(new Integer[]{ 5, 37, 21 });


	}
}

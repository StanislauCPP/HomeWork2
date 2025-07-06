import university.Book;
import university.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

	/**Stream API demonstration*/
	public static void main(String[] args) {
		Stream<Student> stream = streamFromStudentsCollection();

		stream.peek(s -> {																																													// Students
			System.out.println("*******************");
			System.out.println(s);

			System.out.println("\tBooks");
			for(Iterator<Book> it = s.books().iterator(); it.hasNext();)
					System.out.println("\t" + it.next());

			System.out.println("*******************");
		})
				.map(s -> s.books()).collect(Collectors.toList()).stream()																		// Books by student
				.flatMap(Collection::stream)																																									// All books
				.sorted((Book bLeft, Book bRight) -> bLeft.pages().compareTo(bRight.pages()))															// Books sorted by pages
				.distinct()																																																						// Only unique books
				.filter(book -> book.yearRelease() > 2000)																															// Filtered by yearRelease > 2000
				.limit(3)																																																			// Limit on 3 books
				.map(Book::yearRelease)																																												// Years
				.findFirst().ifPresentOrElse(i -> {																																			// Short-circuiting method, but why need Optional<Book>?
																													System.out.println("Book is found. Year release: " + i); },					// Book is found
																			() -> { System.out.println("Book isn't found"); });																			// or isn't found
	}

	static Stream<Student> streamFromStudentsCollection() {
		List<Book> booksFirst = sportBooks();
		booksFirst.addAll(detectiveBooks());

		List<Book> booksSecond = detectiveBooks();
		booksSecond.addAll(programmingBooks());

		return Stream.of(new Student("Vasya", "Pupkin", booksFirst),
														 new Student("Andrey", "Kripkin", booksSecond));
	}

	static ArrayList<Book> sportBooks() {
		ArrayList<Book> l = new ArrayList<Book>(2);
		l.add(new Book("Pull up", "Li Chen", 1997, 100));
		l.add(new Book("Football", "Ivan Ivanov", 2010, 1000));

		return l;
	}

	static ArrayList<Book> detectiveBooks() {
		ArrayList<Book> l = new ArrayList<Book>(2);
		l.add(new Book("Crime", "John Vin", 1960, 264));
		l.add(new Book("Blood picture", "Vasiliy Vasiliyev", 2007, 178));

		return l;
	}

	static ArrayList<Book> programmingBooks() {
		ArrayList<Book> l = new ArrayList<Book>(2);
		l.add(new Book("Java", "Petr Javistskiy", 2001, 348));
		l.add(new Book("Only C", "Vasiliy Sharpistskiy", 1980, 1534));
		l.add(new Book("CPP forever", "Viktor Plyusovskiy", 2011, 5630));

		return l;
	}
}
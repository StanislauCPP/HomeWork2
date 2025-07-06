package university;

import java.util.List;

public class Student {
	private String name, secondName;
	private List<Book> bookList;

	public Student(String name, String secondName, List<Book> bookList) {
		this.name = name;
		this.secondName = secondName;
		this.bookList = bookList;
	}

	public String toString() { return (name + "\t" + secondName); }

	public List<Book> books() { return bookList; }

}

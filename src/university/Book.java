package university;

import java.util.Objects;

public class Book {
	private String name_;
	private String author_;
	int yearRelease_;
	int pages_;

	public Book(String name, String author, int yearRelease, int pages) {
		name_ = name;
		author_ = author;
		yearRelease_ = yearRelease;
		pages_ = pages;
	}

	public String name() { return name_; }
	public String author() { return author_; }
	public Integer yearRelease() { return yearRelease_; }
	public Integer pages() { return pages_; }

	public String toString() { return (name_ + "\t" + author_ + "\t" + yearRelease_ + "\t" + pages_); }

	/* Methods equals and hashCode is override for working method distinct in stream */
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;

		if(obj == null || obj.getClass() != this.getClass())
			return false;

		Book other = (Book) obj;

		return (other.name_ != null && other.author_ != null && 													// other.yearRelease_ and other.pages_ not checked (!=null) cause their type are int;
						other.name_.equals(name_) && other.author_.equals(author_) && other.yearRelease_ == yearRelease_ && other.pages_ == pages_);
	}

	@Override
	public int hashCode() { return Objects.hash(name_, author_, yearRelease_, pages_); }
}

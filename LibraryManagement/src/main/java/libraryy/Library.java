package libraryy;
import java.util.*;
public class Library {
	private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("📘 Book added: " + book.getTitle());
    }

    public void searchBook(String keyword) {
        System.out.println("🔍 Search results:");
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                || b.getAuthor().toLowerCase().contains(keyword.toLowerCase())
                || b.getIsbn().equalsIgnoreCase(keyword)) {
                System.out.println(b);
            }
        }
    }
}

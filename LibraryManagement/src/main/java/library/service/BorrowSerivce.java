package library.service;
import libraryy.Book;
import libraryy.Borrow;
import java.time.LocalDate;
public class BorrowSerivce {
	public Borrow borrowBook(Book book) {
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book already borrowed");
        }

        book.setAvailable(false);
        return new Borrow(book, LocalDate.now(), 28);
    }
}

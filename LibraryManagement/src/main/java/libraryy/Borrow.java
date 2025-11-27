package libraryy;
import java.time.LocalDate;
public class Borrow {
	private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    public Borrow(Book book, LocalDate borrowDate, int period) {
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(period);
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isOverdue(LocalDate today) {
        return today.isAfter(dueDate);
    }

    public int getOverdueDays(LocalDate today) {
        if (!isOverdue(today)) {
            return 0;
        }
        return (int) java.time.temporal.ChronoUnit.DAYS.between(dueDate, today);
    }
}

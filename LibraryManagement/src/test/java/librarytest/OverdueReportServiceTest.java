package librarytest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import library.service.OverdueReportService;
import library.service.OverdueReport;
import libraryy.Book;
import libraryy.Borrow;
import libraryy.MediaItem;

public class OverdueReportServiceTest {

    @Test
    public void testNoOverdueItems() {

        OverdueReportService service = new OverdueReportService();
        List<Borrow> loans = new ArrayList<>();

        Book book = new Book("Java", "Masa", "111");
        book.borrow(LocalDate.of(2025, 1, 10));
        loans.add(new Borrow(book, LocalDate.of(2025, 1, 10), 28));

        OverdueReport report = service.generateReport(loans, LocalDate.of(2025, 1, 15));

        assertEquals(0, report.getItems().size());
        assertEquals(0, report.getTotalFine());
    }

    @Test
    public void testOneOverdueItem() {

        OverdueReportService service = new OverdueReportService();
        List<Borrow> loans = new ArrayList<>();

        Book book = new Book("Java", "Masa", "111");
        LocalDate borrowDate = LocalDate.of(2025, 1, 1);

        book.borrow(borrowDate);
        Borrow b = new Borrow(book, borrowDate, 7);
        loans.add(b);

        OverdueReport report = service.generateReport(loans, LocalDate.of(2025, 1, 15));

        assertEquals(1, report.getItems().size());
        assertEquals(book, report.getItems().get(0));

        assertEquals(7 * 28, report.getTotalFine());
    }

    @Test
    public void testMultipleOverdueItems() {

        OverdueReportService service = new OverdueReportService();
        List<Borrow> loans = new ArrayList<>();

        Book b1 = new Book("Java", "A", "111");
        b1.borrow(LocalDate.of(2025, 1, 1));
        loans.add(new Borrow(b1, LocalDate.of(2025, 1, 1), 7));

        Book b2 = new Book("Python", "B", "222");
        b2.borrow(LocalDate.of(2025, 1, 5));
        loans.add(new Borrow(b2, LocalDate.of(2025, 1, 5), 7));

        LocalDate today = LocalDate.of(2025, 1, 15);

        OverdueReport report = service.generateReport(loans, today);

        assertEquals(2, report.getItems().size());

        int expectedFine =
                (7) * 28 +
                (3) * 28;

        assertEquals(expectedFine, report.getTotalFine());
    }
}

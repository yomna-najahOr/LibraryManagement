package librarytest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import library.service.BorrowSerivce;
import libraryy.Book;
import libraryy.CD;
import libraryy.Borrow;
import libraryy.User;
import libraryy.MediaItem;

public class BorrowServiceTest {

    @Test
    public void testBorrowWithUserSuccess() {
        BorrowSerivce service = new BorrowSerivce();
        User u = new User("Masa", "M@mail.com");
        Book b = new Book("Java", "Author", "111");

        List<Borrow> loans = new ArrayList<>();

        Borrow result = service.borrow(u, b, loans);

        assertNotNull(result);
        assertEquals(1, loans.size());
        assertFalse(b.isAvailable());
        assertEquals(b, result.getBook());
    }

    @Test
    public void testBorrowWithUserThrowsIfUnavailable() {
        BorrowSerivce service = new BorrowSerivce();
        User u = new User("Masa", "M@mail.com");
        Book b = new Book("Java", "Author", "111");

        b.borrow(LocalDate.now());

        List<Borrow> loans = new ArrayList<>();

        assertThrows(IllegalStateException.class, () -> {
            service.borrow(u, b, loans);
        });
    }

    @Test
    public void testBorrowItemOnly() {
        BorrowSerivce service = new BorrowSerivce();
        Book b = new Book("Java", "Author", "111");

        service.borrow(b);

        assertFalse(b.isAvailable());
        assertNotNull(b.dueDate);
    }

    @Test
    public void testBorrowItemThrowsIfUnavailable() {
        BorrowSerivce service = new BorrowSerivce();
        Book b = new Book("Java", "Author", "111");

        b.borrow(LocalDate.now());

        assertThrows(IllegalStateException.class, () -> {
            service.borrow(b);
        });
    }

    @Test
    public void testComputeFineForBook() {
        BorrowSerivce service = new BorrowSerivce();
        Book b = new Book("Java", "Author", "111");

        b.borrow(LocalDate.of(2025, 1, 1));

        LocalDate today = LocalDate.of(2025, 2, 10);

        int fine = service.computeFine(b, today);

        assertTrue(fine > 0);
    }

    @Test
    public void testComputeFineForCD() {
        BorrowSerivce service = new BorrowSerivce();
        CD cd = new CD("Music", "CD1");

        cd.borrow(LocalDate.of(2025, 1, 1));
        LocalDate today = LocalDate.of(2025, 1, 10);

        int fine = service.computeFine(cd, today);

        assertTrue(fine >= 0);
    }
}

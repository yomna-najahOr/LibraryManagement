package librarytest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import libraryy.Borrow;
import libraryy.Book;
import libraryy.MediaItem;

public class BorrowTest {

    @Test
    public void testBorrowInitialization() {
        MediaItem item = new Book("Java", "Yumna", "111");
        LocalDate borrowDate = LocalDate.of(2025, 1, 1);

        Borrow b = new Borrow(item, borrowDate, 10);

      
        assertEquals(borrowDate.plusDays(10), b.getDueDate());
        assertEquals(item, b.getItem());
    }

    @Test
    public void testIsNotOverdue() {
        MediaItem item = new Book("Java", "Yumna", "111");
        LocalDate borrowDate = LocalDate.of(2025, 1, 1);

        Borrow b = new Borrow(item, borrowDate, 10);

     
        boolean result = b.isOverdue(LocalDate.of(2025, 1, 5));
        assertFalse(result);
    }

    @Test
    public void testIsOverdue() {
        MediaItem item = new Book("Java", "Yumna", "111");
        LocalDate borrowDate = LocalDate.of(2025, 1, 1);

        Borrow b = new Borrow(item, borrowDate, 5);

       
        boolean result = b.isOverdue(LocalDate.of(2025, 1, 10));
        assertTrue(result);
    }

    @Test
    public void testOverdueDaysWhenNotOverdue() {
        MediaItem item = new Book("Java", "Yumna", "111");
        LocalDate borrowDate = LocalDate.of(2025, 1, 1);

        Borrow b = new Borrow(item, borrowDate, 7);

        int overdue = b.getOverdueDays(LocalDate.of(2025, 1, 5)); 
        assertEquals(0, overdue);
    }

    @Test
    public void testOverdueDaysCorrectCalculation() {
        MediaItem item = new Book("Java", "Yumna", "111");
        LocalDate borrowDate = LocalDate.of(2025, 1, 1);

        Borrow b = new Borrow(item, borrowDate, 3); 
 

        int overdue = b.getOverdueDays(LocalDate.of(2025, 1, 10));
       
        assertEquals(6, overdue);
    }
}

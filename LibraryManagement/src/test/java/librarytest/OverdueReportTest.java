package librarytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import library.service.OverdueReport;
import libraryy.MediaItem;
import libraryy.Book;

public class OverdueReportTest {

    @Test
    public void testOverdueReportStoresItemsAndFineCorrectly() {

        List<MediaItem> items = new ArrayList<>();

        Book b1 = new Book("Java", "Author", "111");
        Book b2 = new Book("Python", "Author2", "222");

        items.add(b1);
        items.add(b2);

        int fine = 150;

        OverdueReport report = new OverdueReport(items, fine);

        assertEquals(2, report.getItems().size());
        assertEquals(b1, report.getItems().get(0));
        assertEquals(b2, report.getItems().get(1));
        assertEquals(150, report.getTotalFine());
    }

    @Test
    public void testEmptyReport() {

        List<MediaItem> items = new ArrayList<>();
        OverdueReport report = new OverdueReport(items, 0);

        assertTrue(report.getItems().isEmpty());
        assertEquals(0, report.getTotalFine());
    }
}
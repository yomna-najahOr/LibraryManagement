package librarytest;
import libraryy.Library;
import libraryy.Book;
import libraryy.CD;
import libraryy.MediaItem;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LibraryTest {
	@Test
	public void testAddBookAndSearch() {
	    Library lib = new Library();
	    lib.addBook(new Book("Java", "A", "444"));

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(out));

	    lib.searchBook("Java");

	    assertTrue(out.toString().contains("Java"));
	}
	@Test
    public void testSearchByTitle() {
        Library lib = new Library();
        lib.addBook(new Book("Java Programming", "Yumna", "444"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        lib.searchBook("Java");

        String output = out.toString();

        assertTrue(output.contains("Java Programming"));
    }

    @Test
    public void testSearchByAuthor() {
        Library lib = new Library();
        lib.addBook(new Book("Networks", "Yumna Dwikat", "555"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        lib.searchBook("Dwikat");

        String output = out.toString();

        assertTrue(output.contains("Networks"));
    }

    @Test
    public void testSearchByISBN() {
        Library lib = new Library();
        lib.addBook(new Book("Electronics", "Masa", "666"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        lib.searchBook("333");

        String output = out.toString();

        assertTrue(output.contains("Electronics"));
    }

    @Test
    public void testSearchCaseInsensitive() {
        Library lib = new Library();
        lib.addBook(new Book("Data Structures", "YUMNA", "777"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        lib.searchBook("yumna"); 

        String output = out.toString();

        assertTrue(output.contains("Data Structures"));
    }

    @Test
    public void testSearchNoMatch() {
        Library lib = new Library();
        lib.addBook(new Book("Java", "Author", "555"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        lib.searchBook("Python");

        String output = out.toString();

        
        assertFalse(output.contains("Java"));}

	@Test
    public void testFindBookExists() {
        Library lib = new Library();

        Book b1 = new Book("Java", "Author1", "111");
        Book b2 = new Book("Python", "Author2", "222");

        lib.addBook(b1);
        lib.addBook(b2);

        Book result = lib.findBook("111");

        assertNotNull(result);
        assertEquals("111", result.getIsbn());
    }

    @Test
    public void testFindBookNotExists() {
        Library lib = new Library();

        lib.addBook(new Book("Java", "Author1", "111"));

        Book result = lib.findBook("999");

        assertNull(result);
    }

    @Test
    public void testFindBookCaseInsensitive() {
        Library lib = new Library();

        Book b = new Book("Java", "Author", "ABC123");
        lib.addBook(b);

        Book result = lib.findBook("abc123"); 

        assertNotNull(result);
        assertEquals("ABC123", result.getIsbn());
    }

    @Test
    public void testGetBooksReturnsAllItems() {
        Library lib = new Library();

        Book b1 = new Book("Java", "A", "111");
        Book b2 = new Book("Python", "B", "222");
        CD cd1 = new CD("Music", "CD1");

        lib.addBook(b1);
        lib.addBook(b2);
       

        List<MediaItem> items = lib.getBooks();

        assertTrue(items.contains(b1));
        assertTrue(items.contains(b2));
    }
}


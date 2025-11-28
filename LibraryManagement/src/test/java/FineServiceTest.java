import library.service.*;
	
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;	
public class FineServiceTest {
	
	@Test
    public void testBookFine() {
        FineStrategy fs = new BookFine();
        assertEquals(50, fs.calculateFine(5));
    }

    @Test
    public void testCDFine() {
        FineStrategy fs = new CDFine();
        assertEquals(40, fs.calculateFine(2));
    }
	
	}


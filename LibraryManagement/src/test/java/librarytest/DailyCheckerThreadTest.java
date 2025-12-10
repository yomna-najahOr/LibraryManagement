
	package librarytest;

	import library.service.DailyCheckerThread;
	import library.service.ReminderService;
	import libraryy.Borrow;
	import libraryy.User;

	import org.junit.jupiter.api.Test;
	import org.mockito.Mockito;

	import java.util.ArrayList;
	import java.util.List;

	import static org.mockito.Mockito.*;
	/**
	 * Background thread that periodically checks a user's overdue items
	 * and sends reminders automatically every fixed interval.
	 *
	 * Simulates a real automated notification system used in libraries.
	 */

	public class DailyCheckerThreadTest {

	    @Test
	    public void testThreadCallsReminderService() throws Exception {

	        
	        User u = new User("Yumna", "y@mail.com");
	        List<Borrow> loans = new ArrayList<>();

	        
	        ReminderService reminderMock = Mockito.mock(ReminderService.class);

	        DailyCheckerThread thread = new DailyCheckerThread(u, loans, reminderMock);

	        
	        thread.start();

	         
	        Thread.sleep(100);  

	       
	        thread.stopChecker();
	        thread.join();  

	        
	        verify(reminderMock, atLeastOnce()).sendReminders(u, loans);
	    }
	}


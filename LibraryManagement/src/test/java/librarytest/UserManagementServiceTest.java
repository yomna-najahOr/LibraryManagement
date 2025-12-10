
package librarytest;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import library.service.UserManagementService;
import library.service.UserUnregisterStatus;
import libraryy.Admin;
import libraryy.Book;
import libraryy.Borrow;
import libraryy.MediaItem;
import libraryy.User;

public class UserManagementServiceTest {

    @Test
    public void testUnregisterUser_AdminNotLoggedIn() {
        UserManagementService service = new UserManagementService();

        User user = new User("Yumna", "yumna@mail.com");
        Admin admin = new Admin("admin", "1234"); 
        List<Borrow> loans = new ArrayList<>();

        UserUnregisterStatus status = service.unregisterUser(user, admin, loans);

        assertEquals(UserUnregisterStatus.ADMIN_NOT_LOGGED_IN, status);
    }

    @Test
    public void testUnregisterUser_UnpaidFines() {
        UserManagementService service = new UserManagementService();

        User user = new User("Yumna", "yumna@mail.com");
        user.addFine(20); 

        Admin admin = new Admin("admin", "1234");
        admin.login("admin", "1234"); 

        List<Borrow> loans = new ArrayList<>();

        UserUnregisterStatus status = service.unregisterUser(user, admin, loans);

        assertEquals(UserUnregisterStatus.UNPAID_FINES, status);
    }

    @Test
    public void testUnregisterUser_UserHasActiveBorrowedItem() {
        UserManagementService service = new UserManagementService();

        User user = new User("Yumna", "yumna@mail.com");

        Admin admin = new Admin("admin", "1234");
        admin.login("admin", "1234"); 

        List<Borrow> loans = new ArrayList<>();

        
        Book book = new Book("Java", "Author", "111");
        book.borrow(LocalDate.now()); 

        Borrow borrow = new Borrow(book, LocalDate.now(), book.getBorrowPeriod());
        loans.add(borrow);

        UserUnregisterStatus status = service.unregisterUser(user, admin, loans);

        assertEquals(UserUnregisterStatus.USER_HAS_ACTIVE_BORROWED_ITEM, status);
    }

    @Test
    public void testUnregisterUser_Success() {
        UserManagementService service = new UserManagementService();

        User user = new User("Yumna", "yumna@mail.com");

        Admin admin = new Admin("admin", "1234");
        admin.login("admin", "1234"); 
        List<Borrow> loans = new ArrayList<>(); 

        UserUnregisterStatus status = service.unregisterUser(user, admin, loans);

        assertEquals(UserUnregisterStatus.USER_UNREGISTERED_SUCCESSFULLY, status);
    }

    

    @Test
    public void testUnregister_ThrowsWhenAdminNotLoggedIn() {
        UserManagementService service = new UserManagementService();

        User user = new User("Yumna", "yumna@mail.com");
        List<Borrow> loans = new ArrayList<>();

        assertThrows(IllegalStateException.class, () -> {
            service.unregister(user, loans, false);
        });
    }

    @Test
    public void testUnregister_ThrowsWhenUserHasUnpaidFines() {
        UserManagementService service = new UserManagementService();

        User user = new User("Yumna", "yumna@mail.com");
        user.addFine(10); 
        List<Borrow> loans = new ArrayList<>();

        assertThrows(IllegalStateException.class, () -> {service.unregister(user, loans, true); });
    }

    @Test
    public void testUnregister_ThrowsWhenUserHasLoans() {
        UserManagementService service = new UserManagementService();

        User user = new User("Yumna", "yumna@mail.com");

        List<Borrow> loans = new ArrayList<>();

     
        MediaItem book = new Book("Java", "Author", "111");
        Borrow borrow = new Borrow(book, LocalDate.now(), 7);
        loans.add(borrow);

        assertThrows(IllegalStateException.class, () -> {service.unregister(user, loans, true);});
    }

    @Test
    public void testUnregister_Success() {
        UserManagementService service = new UserManagementService();

        User user = new User("Yumna", "yumna@mail.com");
        List<Borrow> loans = new ArrayList<>();

        boolean result = service.unregister(user, loans, true); 

        assertTrue(result);
    }
}

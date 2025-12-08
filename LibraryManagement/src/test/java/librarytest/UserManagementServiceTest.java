package librarytest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import library.service.UserManagementService;
import libraryy.Admin;
import libraryy.Borrow;
import libraryy.User;
import libraryy.Book;
import java.time.LocalDate;

public class UserManagementServiceTest {

   

    @Test
    public void testUnregisterUser_AdminNotLoggedIn() {
        User u = new User("Yumna", "y@mail.com");
        Admin admin = new Admin("a","1");
        List<Borrow> loans = new ArrayList<>();

        UserManagementService s = new UserManagementService();

        assertFalse(s.unregisterUser(u, admin, loans));
    }

    @Test
    public void testUnregisterUser_UserHasFines() {
        User u = new User("Yumna", "y@mail.com");
        u.addFine(10);

        Admin admin = new Admin("a","1");
        admin.login("a","1");

        UserManagementService s = new UserManagementService();

        assertFalse(s.unregisterUser(u, admin, new ArrayList<>()));
    }

    @Test
    public void testUnregisterUser_UserHasActiveLoans() {
        User u = new User("Yumna", "y@mail.com");
        Admin admin = new Admin("a","1");
        admin.login("a","1");

       
        Book b = new Book("Java", "A", "111");
        b.borrow(LocalDate.now());

        List<Borrow> loans = new ArrayList<>();
        loans.add(new Borrow(b, LocalDate.now(), b.getBorrowPeriod()));

        UserManagementService s = new UserManagementService();

        assertFalse(s.unregisterUser(u, admin, loans));
    }

    @Test
    public void testUnregisterUser_Success() {
        User u = new User("Yumna", "y@mail.com");
        Admin admin = new Admin("a","1");
        admin.login("a","1");

        List<Borrow> loans = new ArrayList<>();

        UserManagementService s = new UserManagementService();

        assertTrue(s.unregisterUser(u, admin, loans));
    }


   
    @Test
    public void testUnregister_ThrowsIfAdminNotLoggedIn() {
        User u = new User("Yumna", "y@mail.com");
        UserManagementService s = new UserManagementService();

        assertThrows(IllegalStateException.class, () -> {
            s.unregister(u, new ArrayList<>(), false);
        });
    }

    @Test
    public void testUnregister_ThrowsIfUserHasFines() {
        User u = new User("Yumna", "y@mail.com");
        u.addFine(20);

        UserManagementService s = new UserManagementService();

        assertThrows(IllegalStateException.class, () -> {
            s.unregister(u, new ArrayList<>(), true);
        });
    }

    @Test
    public void testUnregister_ThrowsIfUserHasLoans() {
        User u = new User("Yumna", "y@mail.com");

        Book b = new Book("Java", "A", "111");
        b.borrow(LocalDate.now());

        List<Borrow> loans = new ArrayList<>();
        loans.add(new Borrow(b, LocalDate.now(), b.getBorrowPeriod()));

        UserManagementService s = new UserManagementService();

        assertThrows(IllegalStateException.class, () -> {
            s.unregister(u, loans, true);
        });
    }

    @Test
    public void testUnregister_Success() {
        User u = new User("Yumna", "y@mail.com");
        List<Borrow> loans = new ArrayList<>();

        UserManagementService s = new UserManagementService();

        assertTrue(s.unregister(u, loans, true));
    }
}

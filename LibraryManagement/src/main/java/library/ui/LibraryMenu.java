package library.ui;

import libraryy.*;
import library.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Refactoring #1: Extracted all menu and UI logic from Main.java into this class
 * to separate concerns and make Main.java clean and simple.
 *
 * Handles all menu operations and user interactions.
 */
public class LibraryMenu {

    private Scanner sc = new Scanner(System.in);

    private Admin admin;
    private Library library;
    private BorrowSerivce borrowService;
    private BorrowRulesService ruleService;
    private ReminderService reminderService;
    private OverdueReportService reportService;
    private UserManagementService ums;

    private User user;                 // currently logged user
    private List<Borrow> loans;        // active user loans

    public LibraryMenu() {
        this.admin = new Admin("admin", "1234");
        this.library = new Library();
        this.borrowService = new BorrowSerivce();
        this.ruleService = new BorrowRulesService();
        this.reminderService = new ReminderService(new EmailNotifier());
        this.reportService = new OverdueReportService();
        this.ums = new UserManagementService();

        this.user = new User("Masa", "masa@mail.com");
        this.loans = new ArrayList<>();
    }

    /**
     * Starts the library system menu.
     * Refactoring #1: Moved the loop and switch from Main.java to here
     * so Main is clean and this class handles all menu interactions.
     */
    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    loginAdmin();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    borrowItem();
                    break;
                case 5:
                    payFine();
                    break;
                case 6:
                    showReport();
                    break;
                case 7:
                    sendReminder();
                    break;
                case 8:
                    unregisterUser();
                    break;
                case 9:
                    admin.logout();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    private void printMenu() {
        System.out.println("\n-------- LIBRARY MANAGEMENT --------");
        System.out.println("1. Admin Login");
        System.out.println("2. Add Book");
        System.out.println("3. Search Book");
        System.out.println("4. Borrow Item");
        System.out.println("5. Pay Fine");
        System.out.println("6. Overdue Report");
        System.out.println("7. Send Reminder");
        System.out.println("8. Unregister User");
        System.out.println("9. Admin Logout");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private int readInt() {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Enter a valid number: ");
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private void loginAdmin() {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();
        admin.login(u, p);
    }

    private void addBook() {
        if (!admin.isLoggedIn()) {
            System.out.println("Admin must be logged in.");
            return;
        }
        System.out.print("Book title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        library.addBook(new Book(title, author, isbn));
    }

    private void searchBook() {
        System.out.print("Keyword: ");
        String keyword = sc.nextLine();
        library.searchBook(keyword);
    }

    private void borrowItem() {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        Book book = library.findBook(isbn);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!ruleService.canBorrow(user, loans)) {
            System.out.println("Cannot borrow due to unpaid fines or overdue items.");
            return;
        }

        // Refactoring #2: Centralized borrow logic using BorrowService
        Borrow borrow = borrowService.borrow(user, book, loans);
        System.out.println("Borrowed successfully. Due date: " + borrow.getDueDate());
    }

    private void payFine() {
        System.out.print("Enter amount: ");
        double amount = readInt();
        user.pay(amount);
        System.out.println("Payment done. Remaining fines: " + user.getUnpaidFines());
    }

    private void showReport() {
        OverdueReport report = reportService.generateReport(loans, LocalDate.now());
        if (report.getItems().isEmpty()) {
            System.out.println("No overdue items.");
            return;
        }
        System.out.println("Overdue items:");
        report.getItems().forEach(i -> System.out.println("- " + i.getTitle()));
        System.out.println("Total Fine: " + report.getTotalFine());
    }

    private void sendReminder() {
        reminderService.sendReminders(user, loans);
    }

    private void unregisterUser() {
        UserUnregisterStatus status = ums.unregisterUser(user, admin, loans);
        switch (status) {
            case ADMIN_NOT_LOGGED_IN:
                System.out.println("Admin must be logged in.");
                break;
            case UNPAID_FINES:
                System.out.println("User has unpaid fines.");
                break;
            case USER_HAS_ACTIVE_BORROWED_ITEM:
                System.out.println("User has active borrowed items.");
                break;
            case USER_UNREGISTERED_SUCCESSFULLY:
                System.out.println("User unregistered successfully.");
                break;
        }
    }
}

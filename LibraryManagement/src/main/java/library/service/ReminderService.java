
package library.service;


import libraryy.User;
import libraryy.Borrow;
import java.time.LocalDate;
import java.util.List;
/**
 * Sends reminder notifications to users who have overdue items.
 * The service uses an Observer (EmailNotifier) to notify users via messages.
 */

public class ReminderService {

    private Observer notifier;

    public ReminderService(Observer notifier) {
        this.notifier = notifier;
    }

    public void sendReminders(User user, List<Borrow> loans) {

        int overdueCount = 0;

        for (Borrow l : loans) {
            if (l.isOverdue(LocalDate.now())) {
                overdueCount++;
            }
        }

        if (overdueCount > 0) {
            notifier.notifyUser(
                    user,
                    "You have " + overdueCount + " overdue book(s)."
            );
        }
    }
}
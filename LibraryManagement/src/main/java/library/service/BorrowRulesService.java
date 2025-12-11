

package library.service;

import libraryy.Borrow;
import libraryy.User;

import java.time.LocalDate;
import java.util.List;
/**
 * Contains the borrowing rules that determine whether a user
 * is allowed to borrow an item.
 *
 * Rules include:
 * - User must not have unpaid fines.
 * - User must not have overdue loans.
 */

public class BorrowRulesService {

    public boolean canBorrow(User user, List<Borrow> loans) {
        if (user.getUnpaidFines() > 0) {
            return false;
        }

        for (Borrow l : loans) {
            if (l.isOverdue(LocalDate.now())) {
                return false;
            }
        }

        return true;
    }
}
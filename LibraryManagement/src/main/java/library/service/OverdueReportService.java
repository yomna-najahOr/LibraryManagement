/**
 * Generates an overdue report containing:
 * - A list of all overdue media items.
 * - The total fine calculated for these items.
 *
 * Used by the system to display overdue summaries to the admin.
 */
package library.service;

import libraryy.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OverdueReportService {

	public OverdueReport generateReport(List<Borrow> loans, LocalDate today) {

        List<MediaItem> overdue = new ArrayList<>();
        int totalFine = 0;

        for (Borrow b : loans) {
            if (b.isOverdue(today)) {
            	MediaItem item = b.getItem();

                overdue.add(item);

                totalFine += b.getOverdueDays(today) * item.getBorrowPeriod();
            }
        }

        return new OverdueReport(overdue, totalFine);
    }
}
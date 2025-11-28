package library.service;

import java.time.LocalDate;
import libraryy.MediaItem;

public class BorrowSerivce {

    public void borrow(MediaItem item) {
        if (!item.isAvailable()) {
            throw new IllegalStateException("Item unavailable");
        }
        item.borrow(LocalDate.now());
    }

    public int computeFine(MediaItem item, LocalDate today) {
        int days = item.overdueDays(today);
        return item.calculateFine(days);
    }
}
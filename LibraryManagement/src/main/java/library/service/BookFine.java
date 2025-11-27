package library.service;

public class BookFine implements FineStrategy {
	 

    @Override
    public int calculateFine(int overdueDays) {
        return overdueDays * 10;
}
    }

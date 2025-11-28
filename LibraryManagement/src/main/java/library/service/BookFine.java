package library.service;

public class BookFine implements FineStrategy {
	 

    @Override
    public int calculateFine(int overdueDays) {
        return overdueDays>0?overdueDays * 10:0;
}
    }

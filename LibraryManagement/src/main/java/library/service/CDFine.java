package library.service;

public class CDFine implements FineStrategy {
	@Override
	public int calculateFine(int overdueDays) {
        return overdueDays >0?overdueDays* 20:0;
    }
}

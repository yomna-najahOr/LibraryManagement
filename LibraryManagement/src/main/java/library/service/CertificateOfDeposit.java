package library.service;

public class CertificateOfDeposit implements FineStrategy {
	@Override
	public int calculateFine(int overdueDays) {
        return overdueDays * 20;
    }
}

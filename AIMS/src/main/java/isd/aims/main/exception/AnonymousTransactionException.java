package isd.aims.main.exception;

public class AnonymousTransactionException extends PaymentException {
    public AnonymousTransactionException() {
        super("ERROR: Giao dịch bị nghi ngờ gian lận");
    }
}

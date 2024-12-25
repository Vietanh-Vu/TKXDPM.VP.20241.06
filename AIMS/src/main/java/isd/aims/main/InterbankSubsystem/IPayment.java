package isd.aims.main.InterbankSubsystem;

import isd.aims.main.exception.PaymentException;
import isd.aims.main.exception.UnrecognizedException;

/**
 * The {@code IPayment} class is used to communicate with the
 * {@link VnPaySubsystem InterbankSubsystem} to make transaction.
 *
 * @author hieud
 */
public interface IPayment {

    /**
     * Pay order, and then return the payment transaction.
     */
    public abstract String generatePaymentURL(int amount, String contents)
            throws PaymentException, UnrecognizedException;

}

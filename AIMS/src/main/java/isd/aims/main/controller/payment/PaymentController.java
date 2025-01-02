package isd.aims.main.controller.payment;

import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.payment.PaymentType;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class PaymentController {
    private final PaymentType paymentType;
    private final Invoice invoice;

    public void payment() throws IOException {
        IPaymentMethod paymentMethod = PaymentMethodFactory.getInstance().createPaymentMethod(paymentType);
        paymentMethod.handlePaymentProcess(invoice);
    }
}

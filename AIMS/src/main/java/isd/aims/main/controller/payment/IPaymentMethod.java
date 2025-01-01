package isd.aims.main.controller.payment;


import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.payment.PaymentTransaction;
import isd.aims.main.entity.payment.RefundTransaction;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface IPaymentMethod {
    String makePaymentRequest(int amount, String content) throws UnsupportedEncodingException;
    void handlePaymentProcess(Invoice invoice) throws IOException;
    PaymentTransaction handlePaymentResponse(String paymentReturnURL) throws ParseException, URISyntaxException;
    void onTransactionCompleted(String responseUrl, Invoice invoice) throws ParseException, URISyntaxException, MessagingException, IOException;
    String getType();
    String makeRefundRequest(int amount, String content);
    RefundTransaction handleRefund();
}

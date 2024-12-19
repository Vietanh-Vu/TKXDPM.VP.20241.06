package isd.aims.main.controller.payment;

import isd.aims.main.utils.payment.PaymentTransaction;
import isd.aims.main.utils.payment.RefundTransaction;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface IPaymentMethod {
    String makePaymentRequest(int amount, String content) throws UnsupportedEncodingException;
    void handlePaymentProcess();
    PaymentTransaction handlePaymentResponse(String paymentReturnURL) throws ParseException, URISyntaxException;
    String makeRefundRequest(int amount, String content);
    RefundTransaction handleRefund();
}

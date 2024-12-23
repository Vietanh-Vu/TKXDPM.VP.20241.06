package isd.aims.main.controller.payment;


import isd.aims.main.InterbankSubsystem.vn_pay.PayResponseVnPay;
import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.payment.PaymentTransaction;
import isd.aims.main.entity.payment.RefundTransaction;
import isd.aims.main.listener.TransactionResultListener;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;

public interface IPaymentMethod {
    String makePaymentRequest(int amount, String content) throws UnsupportedEncodingException;
    void handlePaymentProcess(Invoice invoice) throws IOException;
    PaymentTransaction handlePaymentResponse(String paymentReturnURL) throws ParseException, URISyntaxException;
    void onTransactionCompleted(PaymentTransaction transaction, Invoice invoice);
    String makeRefundRequest(int amount, String content);
    RefundTransaction handleRefund();
}

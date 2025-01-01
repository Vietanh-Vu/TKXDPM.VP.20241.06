package isd.aims.main.controller.payment;


import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.payment.PaymentTransaction;
import isd.aims.main.entity.payment.RefundTransaction;
import jakarta.mail.MessagingException;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Map;

public interface IPaymentMethod {
    String makePaymentRequest(int amount, String content) throws UnsupportedEncodingException;
    void handlePaymentProcess(Invoice invoice) throws IOException;
    PaymentTransaction handlePaymentResponse(String paymentReturnURL) throws ParseException, URISyntaxException;
    void onTransactionCompleted(String responseUrl, Invoice invoice) throws ParseException, URISyntaxException, MessagingException, IOException;
    String getType();
    void handleRefundProcess(Stage satge, Order order) throws IOException;
    String makeRefundRequest(int amount, Map<String, Object> additionalParams);
    void handleRefund(int orderId);
}

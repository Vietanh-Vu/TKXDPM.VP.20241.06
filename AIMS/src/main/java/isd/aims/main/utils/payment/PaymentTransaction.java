package isd.aims.main.utils.payment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentTransaction {
    private int orderId; // transactionId trong merchant
    private String content; // transactionContent
    private Date createdAt; // date
    private String message; // errorCode (trả về 00 nếu thành công)
    private int amount; // Giá tiền

    public PaymentTransaction(String message, String orderId, String content, int amount, Date createdAt) {
    }
}

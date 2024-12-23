package isd.aims.main.entity.payment;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransaction {
	private String orderId; // transactionId trong merchant
	private String content; // transactionContent
	private Date createdAt; // date
	private String message; // errorCode (trả về 00 nếu thành công)
	private int amount; // Giá tiền
}

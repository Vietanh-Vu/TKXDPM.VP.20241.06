package isd.aims.main.entity.payment;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PaymentTransaction {
	private String id;
	private String orderId;
	private String content;
	private LocalDateTime createdAt;
	private String status;
	private int amount;
	private String paymentType;
	private String transactionNumber;

	public PaymentTransaction(String content, LocalDateTime createdAt, String status, int amount, String paymentType, String transactionNumber) {
		this.content = content;
		this.createdAt = createdAt;
		this.status = status;
		this.amount = amount;
		this.paymentType = paymentType;
		this.transactionNumber = transactionNumber;

	}
}

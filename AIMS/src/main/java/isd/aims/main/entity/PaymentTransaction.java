package isd.aims.main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction {
	private int id;
	private int orderId;
	private LocalDateTime createAt;
	private String content;
}

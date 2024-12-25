package isd.aims.main.entity.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {
    VNPay("VNPay");

    private final String paymentType;
}

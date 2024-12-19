package isd.aims.main.utils.payment;

import lombok.Getter;

@Getter
public enum PaymentType {
    VNPay("VNPay");

    private final String paymentType;

    PaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}

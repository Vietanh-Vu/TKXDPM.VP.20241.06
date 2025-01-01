package isd.aims.main.entity.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum PaymentType {
    VNPay("VNPay");

    private final String paymentType;

    public static List<PaymentType> getAllPaymentTypes() {
        return Arrays.asList(PaymentType.values());
    }
}

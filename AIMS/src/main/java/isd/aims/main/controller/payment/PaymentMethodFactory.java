package isd.aims.main.controller.payment;

import isd.aims.main.InterbankSubsystem.vn_pay.VNPayPaymentMethod;
import isd.aims.main.entity.payment.PaymentType;

import java.util.Objects;

public class PaymentMethodFactory {
    public IPaymentMethod createPaymentMethod(PaymentType paymentType) {
        if (Objects.requireNonNull(paymentType) == PaymentType.VNPay) {
            return new VNPayPaymentMethod();
        }
        throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
    }
}

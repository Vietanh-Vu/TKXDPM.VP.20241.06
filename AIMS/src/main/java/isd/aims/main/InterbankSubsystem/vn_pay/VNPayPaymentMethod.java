package isd.aims.main.InterbankSubsystem.vn_pay;

import isd.aims.main.InterbankSubsystem.vn_pay.VNPayConfig;
import isd.aims.main.controller.payment.IPaymentMethod;
import isd.aims.main.utils.payment.PaymentTransaction;
import isd.aims.main.utils.payment.RefundTransaction;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VNPayPaymentMethod implements IPaymentMethod {


    // Tạo yêu cầu thanh toán với VNPay
    /*
    URL Thanh toán có dạng
    https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?
    vnp_Amount=1806000&vnp_Command=pay
    &vnp_CreateDate=20210801153333&vnp_CurrCode=VND
    &vnp_IpAddr=127.0.0.1
    &vnp_Locale=vn&vnp_OrderInfo=Thanh+toan+don+hang+%3A5
    &vnp_OrderType=other
    &vnp_ReturnUrl=https%3A%2F%2Fdomainmerchant.vn%2FReturnUrl
    &vnp_TmnCode=DEMOV210&vnp_TxnRef=5
    &vnp_Version=2.1.0
    &vnp_SecureHash=3e0d61a0c0534b2e36680b3f7277743e8784cc4e1d68fa7d276e79c23be7d6318d338b477910a27992f5057bb1582bd44bd82ae8009ffaf6d141219218625c42
    */
    @Override
    public String makePaymentRequest(int amount, String content) throws UnsupportedEncodingException {
        PayRequestVnPay payRequest = new PayRequestVnPay();
        payRequest.setAmount(amount);
        payRequest.setContent(content);
        return payRequest.generateURL();

    }

    // Xử lý liên quan đến database hoặc gì đó
    @Override
    public void handlePaymentProcess() {

    }

    @Override
    public PaymentTransaction handlePaymentResponse(String vnpReturnURL) throws ParseException, URISyntaxException {
        PayResponseVnPay payResponse = new PayResponseVnPay();
        return payResponse.getTransaction(vnpReturnURL);
    }


    @Override
    public String makeRefundRequest(int amount, String content) {
        return "";
    }

    @Override
    public RefundTransaction handleRefund() {
        return null;
    }
}

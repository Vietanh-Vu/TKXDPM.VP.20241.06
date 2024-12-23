package isd.aims.main.InterbankSubsystem.vn_pay;

import isd.aims.main.entity.payment.PaymentTransaction;
import isd.aims.main.entity.response.Response;
import isd.aims.main.exception.*;
import lombok.Getter;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class PayResponseVnPay extends Response {
    private String vnp_BankCode;
    private String vnp_PayDate;
    private String vnp_TransactionNo;
    private String vnp_TmnCode;
    private String vnp_SecureHash;
    private String vnp_OrderInfo;
    private String vnp_TxnRef;
    private String vnp_Amount;
    private String vnp_CardType;
    private String vnp_TransactionStatus;
    private String vnp_BankTranNo;
    private String vnp_ResponseCode;
    private String vnp_TransactionDate;

    public PayResponseVnPay(String query) {
        Map<String, String> parseQuery = parseQueryString(query);
        this.vnp_ResponseCode = parseQuery.get("vnp_ResponseCode");
        handleException();

        this.vnp_BankCode = parseQuery.get("vnp_BankCode");
        this.vnp_PayDate = parseQuery.get("vnp_PayDate");
        this.vnp_TransactionNo = parseQuery.get("vnp_TransactionNo");
        this.vnp_TmnCode = parseQuery.get("vnp_TmnCode");
        this.vnp_SecureHash = parseQuery.get("vnp_SecureHash");
        this.vnp_OrderInfo = parseQuery.get("vnp_OrderInfo");
        this.vnp_TxnRef = parseQuery.get("vnp_TxnRef");
        this.vnp_Amount = parseQuery.get("vnp_Amount");
        this.vnp_CardType = parseQuery.get("vnp_CardType");
        this.vnp_TransactionStatus = parseQuery.get("vnp_TransactionStatus");
        this.vnp_BankTranNo = parseQuery.get("vnp_BankTranNo");
        this.vnp_TransactionDate = parseQuery.get("vnp_TransactionDate");
    }

    private Map<String, String> parseQueryString(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

    private void handleException() throws TransactionNotDoneException, TransactionFailedException, TransactionReverseException, UnrecognizedException {
        switch (this.vnp_ResponseCode) {
            case "00":
                break;
            case "01":
                throw new TransactionNotDoneException();
            case "02":
                throw new TransactionFailedException();
            case "04":
                throw new TransactionReverseException();
            case "05":
                throw new ProcessingException();
            case "09":
                throw new RejectedTransactionException();
            case "06":
                throw new SendToBankException();
            case "07":
                throw new AnonymousTransactionException();
            default:
                throw new UnrecognizedException();
        }
    }

    public PaymentTransaction getTransaction(String vnpReturnURL) throws ParseException, URISyntaxException {
        URI uri = new URI(vnpReturnURL);
        String query = uri.getQuery();
        PayResponseVnPay VNPayResponse = new PayResponseVnPay(query);


        // Create Payment transaction
        String errorCode = VNPayResponse.getVnp_TransactionStatus(); // mã lỗi (00 nếu thành công)
        String transactionId = VNPayResponse.getVnp_TransactionNo(); // mã transaction trong merchant
        String transactionContent = VNPayResponse.getVnp_OrderInfo(); // thông tin đơn hàng
        int amount = Integer.parseInt(VNPayResponse.getVnp_Amount()) / 100; // giá tiền
        String createdAt = VNPayResponse.getVnp_PayDate(); // ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = dateFormat.parse(createdAt);

        String message = Objects.equals(errorCode, "00") ? "SUCCESS" : "FAILED";

        return new PaymentTransaction(transactionId, transactionContent, date, message, amount);

    }
}

package isd.aims.main.InterbankSubsystem.vn_pay;

import isd.aims.main.entity.response.Response;
import isd.aims.main.utils.payment.PaymentTransaction;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PayResponseVnPay {

    public PaymentTransaction getTransaction(String vnpReturnURL) throws ParseException, URISyntaxException {
        URI uri = new URI(vnpReturnURL);
        String query = uri.getQuery();
        Response response = new Response(query);


        // Create Payment transaction
        String errorCode = response.getVnp_TransactionStatus(); // mã lỗi (00 nếu thành công)
        String transactionId = response.getVnp_TransactionNo(); // mã transaction trong merchant
        String transactionContent = response.getVnp_OrderInfo(); // thông tin đơn hàng
        int amount = Integer.parseInt(response.getVnp_Amount()) / 100; // giá tiền
        String createdAt = response.getVnp_PayDate(); // ngày
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        Date date = dateFormat.parse(createdAt);

        return new PaymentTransaction(errorCode, transactionId, transactionContent, amount, date);

    }
}

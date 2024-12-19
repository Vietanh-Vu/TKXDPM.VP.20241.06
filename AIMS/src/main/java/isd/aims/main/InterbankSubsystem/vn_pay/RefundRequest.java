package isd.aims.main.InterbankSubsystem.vn_pay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONObject; // Thư viện JSON

public class RefundRequest {
    private int amount; // amount in VND
    private String content; // content for order info
    private String txnRef; // Mã giao dịch VNPay
    private String transactionNo; // Mã giao dịch tham chiếu VNPay
    private String orderInfo; // Nội dung thanh toán

    // Constructor
    public RefundRequest(int amount, String content, String txnRef, String transactionNo, String orderInfo) {
        this.amount = amount;
        this.content = content;
        this.txnRef = txnRef;
        this.transactionNo = transactionNo;
        this.orderInfo = orderInfo;
    }

    // Method to generate the refund request
    public String generateURL() {
        try {
            // Tạo các tham số
            String vnp_RequestId = VNPayConfig.getRandomNumber(8);
            String vnp_Version = "2.1.0";
            String vnp_Command = "refund";
            String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
            String vnp_TransactionType = "02"; // Hoàn tiền
            String vnp_TxnRef = this.txnRef; // Mã giao dịch tham chiếu
            long amountInCents = this.amount * 100L; // Quy đổi ra xu (VND x 100)
            String vnp_Amount = String.valueOf(amountInCents);
            String vnp_OrderInfo = this.orderInfo; // Nội dung thanh toán
            String vnp_TransactionNo = this.transactionNo; // Mã giao dịch VNPay
            String vnp_TransactionDate = "20241219200235";
            String vnp_CreateBy = "user"; // Người thực hiện
            String vnp_CreateDate = new SimpleDateFormat("yyyyMMddHHmmss")
                    .format(Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7")).getTime());
            String vnp_IpAddr = "127.0.0.1"; // Địa chỉ IP

            // Tạo hash data theo quy tắc mới
            String hash_Data = vnp_RequestId + "|" + vnp_Version + "|" + vnp_Command + "|" + vnp_TmnCode + "|"
                    + vnp_TransactionType + "|" + vnp_TxnRef + "|" + vnp_Amount + "|" + vnp_TransactionNo + "|"
                    + vnp_TransactionDate + "|" + vnp_CreateBy + "|" + vnp_CreateDate + "|" + vnp_IpAddr + "|"
                    + vnp_OrderInfo;

            // Hash key để tạo mã checksum
            String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hash_Data);

            // Tạo JSON Object
            JSONObject json = new JSONObject();
            json.put("vnp_RequestId", vnp_RequestId);
            json.put("vnp_Version", vnp_Version);
            json.put("vnp_Command", vnp_Command);
            json.put("vnp_TmnCode", vnp_TmnCode);
            json.put("vnp_TransactionType", vnp_TransactionType);
            json.put("vnp_TxnRef", vnp_TxnRef);
            json.put("vnp_Amount", vnp_Amount);
            json.put("vnp_OrderInfo", vnp_OrderInfo);
            json.put("vnp_TransactionNo", vnp_TransactionNo);
            json.put("vnp_TransactionDate", vnp_TransactionDate);
            json.put("vnp_CreateBy", vnp_CreateBy);
            json.put("vnp_CreateDate", vnp_CreateDate);
            json.put("vnp_IpAddr", vnp_IpAddr);
            json.put("vnp_SecureHash", vnp_SecureHash);

            System.out.println("Request JSON: " + json.toString());

            // Gửi POST request
            URL url = new URL(VNPayConfig.vnp_ApiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Đọc phản hồi
            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return "Request was successful: " + response;
            } else {
                return "Request failed with response code: " + responseCode + " and message: " + response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred: " + e.getMessage();
        }
    }
}

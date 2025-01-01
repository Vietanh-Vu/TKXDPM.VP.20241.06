package isd.aims.main.views.payment;


import isd.aims.main.InterbankSubsystem.vn_pay.VNPayPaymentMethod;
import isd.aims.main.utils.Configs;
import isd.aims.main.views.BaseForm;
import isd.aims.main.views.home.HomeForm;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.json.JSONObject;  // Import thư viện JSON

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class VNPayRefund extends BaseForm {

    @FXML
    private ImageView aimsImage;

    @FXML
    private TextField TxnRef;   // Mã giao dịch tham chiếu
    @FXML
    private TextField TransactionNo; // Mã giao dịch
    @FXML
    private TextField TransactionDate; // Ngày giao dịch
    @FXML
    private TextField Amount;     // Tổng số tiền

    @FXML
    private Label responseCodeLabel; // Label để hiển thị vnp_ResponseCode
    @FXML
    private Label responseMessageLabel; // Label để hiển thị vnp_Message

    @FXML
    private Button confirmButton; // Nút xác nhận

    private int orderId; // Lưu orderId của mã được hoàn tiền
    private boolean flag = false;
    public VNPayRefund(Stage stage, String screenPath, int orderId) throws IOException {
        super(stage, screenPath);
        this.orderId = orderId;
        aimsImage.setOnMouseClicked(e -> {
            if (flag) {
                try {
                    homeScreenHandler = new HomeForm(stage, Configs.HOME_PATH);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            homeScreenHandler.show();
        });
    }

    // Event handler for confirm button click
    @FXML
    private void handleConfirmButtonClick() {
        // Get the values from the text fields
        String vnp_TxnRef = TxnRef.getText();
        String vnp_TransactionNo = TransactionNo.getText();
        String vnp_TransactionDate = TransactionDate.getText();
        int vnp_Amount = Integer.parseInt(Amount.getText());


        // Xử lý hoàn tiền
        Map<String, Object> vnpayParams = new HashMap<>();
        vnpayParams.put("vnp_TxnRef", vnp_TxnRef);
        vnpayParams.put("vnp_TransactionNo", vnp_TransactionNo);
        vnpayParams.put("vnp_TransactionDate", vnp_TransactionDate);
        VNPayPaymentMethod vnpayRefund = new VNPayPaymentMethod();

        // Gọi phương thức generateURL để tạo yêu cầu hoàn tiền
        String response = vnpayRefund.makeRefundRequest(vnp_Amount, vnpayParams);

        // Hiển thị kết quả
        handleRefundResponse(response);
    }

    // Method to handle the response and extract the needed values
    private void handleRefundResponse(String response) {
        System.out.println(orderId);
        try {
            // Parse the response string into a JSONObject
            JSONObject jsonResponse = new JSONObject(response);

            // Extract the vnp_ResponseCode and vnp_Message
            String vnp_ResponseCode = jsonResponse.getString("vnp_ResponseCode");
            String vnp_Message = jsonResponse.getString("vnp_Message");


            // Display the values in the respective labels
            responseCodeLabel.setText(vnp_ResponseCode);
            responseMessageLabel.setText(vnp_Message);

            if ("00".equals(vnp_ResponseCode)) {
                // Xử lý khi refund thành công
                VNPayPaymentMethod vnpayRefund = new VNPayPaymentMethod();
                flag = true;
                vnpayRefund.handleRefund(orderId);

                System.out.println("Refund processed successfully");
            } else {
                System.out.println("Error: " + vnp_Message);
            }

        } catch (Exception e) {
            System.out.println("Error processing response: " + e.getMessage());
        }
    }
}

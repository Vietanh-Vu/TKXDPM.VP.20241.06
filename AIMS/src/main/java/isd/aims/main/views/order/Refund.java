package isd.aims.main.views.order;

import isd.aims.main.InterbankSubsystem.vn_pay.RefundRequest;
import isd.aims.main.views.BaseForm;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.json.JSONObject;  // Import thư viện JSON

import java.io.File;
import java.io.IOException;


public class Refund extends BaseForm {

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

    public Refund(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        // Fix relative image path caused by fxml
        File file = new File("isd/aims/main/fxml/images/Logo.png");
        Image im = new Image(file.toURI().toString());
        aimsImage.setImage(im);

        // On mouse clicked, go back to home
        aimsImage.setOnMouseClicked(e -> {
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

        // Log or process the information
        System.out.println("Mã giao dịch tham chiếu: " + vnp_TxnRef);
        System.out.println("Mã giao dịch: " + vnp_TransactionNo);
        System.out.println("Ngày giao dịch: " + vnp_TransactionDate);
        System.out.println("Tổng số tiền: " + vnp_Amount);

        // Xử lý hoàn tiền
        RefundRequest refundRequest = new RefundRequest(vnp_Amount, vnp_TxnRef, vnp_TransactionNo, vnp_TransactionDate);

        // Gọi phương thức generateURL để tạo yêu cầu hoàn tiền
        String response = String.valueOf(refundRequest.generateURL());

        // Handle the response to extract vnp_ResponseCode and vnp_Message
        handleRefundResponse(response);
    }

    // Method to handle the response and extract the needed values
    private void handleRefundResponse(String response) {
        try {
            System.out.println("Response: " + response);
            // Parse the response string into a JSONObject
            JSONObject jsonResponse = new JSONObject(response);

            // Extract the vnp_ResponseCode and vnp_Message
            String vnp_ResponseCode = jsonResponse.getString("vnp_ResponseCode");
            String vnp_Message = jsonResponse.getString("vnp_Message");

            // Log the extracted values
            System.out.println("vnp_ResponseCode: " + vnp_ResponseCode);
            System.out.println("vnp_Message: " + vnp_Message);

            // Display the values in the respective labels
            responseCodeLabel.setText(vnp_ResponseCode);
            responseMessageLabel.setText(vnp_Message);

            // You can also handle these values as needed, like displaying them in the UI
            // For example, you could show a message to the user:
            if ("94".equals(vnp_ResponseCode)) {
                System.out.println("Error: " + vnp_Message);  // Handle specific error response
            } else {
                System.out.println("Refund processed successfully");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error processing response: " + e.getMessage());
        }
    }
}

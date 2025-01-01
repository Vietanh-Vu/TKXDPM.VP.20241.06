package isd.aims.main.views.order;

import isd.aims.main.controller.payment.IPaymentMethod;
import isd.aims.main.controller.payment.PaymentMethodFactory;
import isd.aims.main.entity.db.dao.order.OrderDAO;
import isd.aims.main.entity.order.Order;

import isd.aims.main.entity.payment.PaymentType;
import isd.aims.main.utils.Configs;
import isd.aims.main.views.BaseForm;
import isd.aims.main.views.payment.VNPayRefund;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class ListOrderForm extends BaseForm {



    @FXML
    private ImageView aimsImage;

    @FXML
    private TextField emailField;
    @FXML
    private Button btnSearch;

    @FXML
    private VBox ordersBox;

    public ListOrderForm(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        // on mouse clicked, we back to home
        aimsImage.setOnMouseClicked(e -> {
            homeScreenHandler.show();
        });
    }

    @FXML
    private void handleSearchClick() {
        String email = emailField.getText();
        List<Order> orders = new OrderDAO().getByEmail(email);

        displayOrders(orders);

//        if (email != null && !email.isEmpty()) {
//            OrderDAO orderDao = new OrderDAO();
//            List<Order> orders = orderDao.getAllByEmail(email);
//            displayOrders(orders); // Hiển thị danh sách đơn hàng
//        } else {
//            System.out.println("Email không hợp lệ");
//        }
    }

//    private List<Order> getOrdersByEmail(String email) {
//        return List.of(
//                new Order(1,"John Doe", email, "123 Main St", "0123456789", "Hanoi", 20.0, 200.0, "Paid", "Credit Card"),
//                new Order(2, "Jane Smith", email, "456 Another St", "0987654321", "HCMC", 15.0, 100.0, "Pending", "PayPal")
//        );
//    }

    private void displayOrders(List<Order> orders) {
        ordersBox.getChildren().clear(); // Xóa nội dung cũ

        // Thêm hàng tiêu đề nếu cần
        HBox headerRow = new HBox(10); // Spacing giữa các cột
        headerRow.setAlignment(Pos.CENTER_LEFT); // Căn trái
        headerRow.setStyle("-fx-background-color: #dcdcdc; -fx-padding: 5; -fx-font-weight: bold;");

        headerRow.getChildren().addAll(
                createText("Name", 120),
                createText("Email", 150),
                createText("Address", 150),
                createText("Phone", 100),
                createText("Province", 100),
                createText("Shipping Fee", 100),
                createText("Total Amount", 100),
                createText("Order Status", 100),
                new Text("Refund")
        );

        ordersBox.getChildren().add(headerRow);

        // Hiển thị các đơn hàng
        for (Order order : orders) {
            HBox orderRow = new HBox(10); // Spacing giữa các thành phần
            orderRow.setAlignment(Pos.CENTER_LEFT); // Căn trái
            orderRow.setStyle("-fx-padding: 5; -fx-border-color: #ddd; -fx-border-width: 0 0 1 0;"); // Thêm viền dưới

            // Tạo các thành phần cho từng dòng
            Text nameText = createText(order.getDeliveryInfo().getName(), 120);
            Text emailText = createText(order.getDeliveryInfo().getEmail(), 150);
            Text addressText = createText(order.getDeliveryInfo().getAddress(), 150);
            Text phoneText = createText(order.getDeliveryInfo().getPhoneNumber(), 100);
            Text provinceText = createText(order.getDeliveryInfo().getProvince(), 100);
            Text shippingFeeText = createText(String.valueOf(order.getShippingFees()), 100);
            Text totalAmountText = createText(String.valueOf(order.getTotalAmount() + order.getShippingFees()), 100);
            Text paymentStatusText = createText(order.getOrderStatus(), 100);

            // Tạo nút Refund
            Button refundButton = new Button("Refund");
            refundButton.setOnAction(event -> {
                try {
                    handleRefundClick(order);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            // Thêm các thành phần vào hàng
            orderRow.getChildren().addAll(
                    nameText, emailText, addressText, phoneText, provinceText,
                    shippingFeeText, totalAmountText, paymentStatusText,
                    refundButton
            );

            // Thêm hàng vào VBox
            ordersBox.getChildren().add(orderRow);
        }
    }

    // Hàm tạo Text với kích thước cố định
    private Text createText(String content, double width) {
        Text text = new Text(content);
        text.setWrappingWidth(width); // Đảm bảo kích thước cố định
        return text;
    }


    private void handleRefundClick(Order order) throws IOException {
        // Lấy đối tượng PaymentMethodFactory (theo Singleton Pattern)
        PaymentMethodFactory factory = PaymentMethodFactory.getInstance();
        System.out.println(factory);
        // Lấy phương thức thanh toán
        IPaymentMethod refundMethod = factory.createPaymentMethod(PaymentType.valueOf(order.getPaymentType()));
        // Xử lý thông tin refund
        refundMethod.handleRefundProcess(this.stage, order);

    }

}

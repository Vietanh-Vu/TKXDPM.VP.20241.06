package isd.aims.main.controller;

import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.cart.CartMedia;
import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;
import isd.aims.main.utils.Utils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

/**
 * This class controls the flow of place order usecase in our AIMS project
 *
 * @author nguyenlm
 */

// Procedural Cohesion: các phương thức đều liên quan đến việc xử lý đặt hàng
public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     *
     * @throws SQLException
     */
    public void placeOrder() throws SQLException {
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     *
     * @return Order
     * @throws SQLException
     */
    // Sequential Cohesion: createOrder() và createInvoice() có kết quả của phương thức trước là đầu vào của phương thức sau
    @SuppressWarnings("unchecked")
    public Order createOrder() throws SQLException {
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(),
                    cartMedia.getQuantity(),
                    cartMedia.getPrice());
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     *
     * @param order
     * @return Invoice
     */
    // Communicational Cohesion: Chia sẻ cùng input với calculateShippingFree
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     *
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException {
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }

    /**
     * The method validates the info
     *
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public boolean validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException {
        if (!validatePhoneNumber(info.get("phone"))) {
           return false;
        }

        if (!validateName(info.get("name"))) {
            return false;
        }

        if (!validateAddress(info.get("address"))) {
            return false;
        }

        return true;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        // Kiểm tra null hoặc chuỗi rỗng
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }

        // Kiểm tra bắt đầu bằng 0
        if (!phoneNumber.startsWith("0")) {
            return false;
        }

        // Loại bỏ các ký tự phân cách
        String cleanedPhoneNumber = phoneNumber.replaceAll("[.-/]", "");

        // Kiểm tra độ dài và chỉ chứa số
        if (cleanedPhoneNumber.length() != 10 ||
                !cleanedPhoneNumber.matches("\\d+")) {
            return false;
        }

        // Kiểm tra các ký tự phân cách
        String separators = phoneNumber.replaceAll("\\d", "");

        if (!separators.isEmpty()) {
            // Kiểm tra chỉ có một loại ký tự phân cách
            char firstSeparator = separators.charAt(0);
            for (char c : separators.toCharArray()) {
                if (c != firstSeparator) {
                    return false;
                }
            }

            // Kiểm tra vị trí ký tự phân cách
            String[] parts = phoneNumber.split("[.-/]");
            for (String part : parts) {
                if (part.isEmpty()) {
                    return false;
                }
            }

            // Kiểm tra định dạng phân cách
            // Chỉ chấp nhận các định dạng như 0-xxx-xxx-xxx, 0.xxx.xxx.xxx, 0/xxx/xxx/xxx
            if (!phoneNumber.matches("^0[.-/]\\d{3}[.-/]\\d{3}[.-/]\\d{3}$")) {
                return false;
            }
        }

        return true;
    }

    public boolean validateName(String name) {
        // Check for null
        if (name == null) {
            return false;
        }

        // Check for empty string
        if (name.isEmpty()) {
            return false;
        }

        // Check length <= 30
        if (name.length() > 30) {
            return false;
        }

        // Check if contains only letters
        return name.matches("[a-zA-Z]+");
    }

    public boolean validateAddress(String address) {
        // Check null
        if (address == null) {
            return false;
        }

        // Check length (0 < length <= 100)
        if (address.isEmpty() || address.length() > 100) {
            return false;
        }

        // Check valid characters (only letters, numbers and forward slash)
        String regex = "^[a-zA-Z0-9/]+$";
        return address.matches(regex);
    }


    /**
     * This method calculates the shipping fees of order
     *
     * @param order
     * @return shippingFee
     */
    // Control Coupling: tính phí dựa trên điều kiện của Order
    // => Tách thành 2 hàm phục vụ cho việc tính phí có rush order hoặc không có rush order
    public int calculateShippingFee(Order order) {
        Random rand = new Random();
        int fees = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}

package isd.aims.main.controller.placeorder;

import isd.aims.main.controller.BaseController;
import isd.aims.main.controller.placeorder.ordervalidator.DeliveryInfoValidator;
import isd.aims.main.controller.placeorder.ordervalidator.StandardInfoValidator;
import isd.aims.main.controller.placeorder.shippingfee.ShippingFeeStrategy;
import isd.aims.main.controller.placeorder.shippingfee.StandardShippingFee;
import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.cart.CartMedia;
import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;
import isd.aims.main.utils.Utils;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Setter
    @NoArgsConstructor
public class PlaceOrderController extends BaseController {

    private ShippingFeeStrategy shippingFeeStrategy = new StandardShippingFee();
    private DeliveryInfoValidator deliveryInfoValidator = new StandardInfoValidator() {
    };

    public PlaceOrderController(ShippingFeeStrategy shippingFeeStrategy, DeliveryInfoValidator deliveryInfoValidator) {
        this.shippingFeeStrategy = shippingFeeStrategy;
        this.deliveryInfoValidator = deliveryInfoValidator;
    }

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
    public void processDeliveryInfo(DeliveryInfo info) throws InterruptedException, IOException {
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
    public boolean validateDeliveryInfo(DeliveryInfo info) throws InterruptedException, IOException {
        return deliveryInfoValidator.validateDeliveyInfo(info);
    }

    /**
     * This method calculates the shipping fees of order
     *
     * @param order
     * @return shippingFee
     */
     public int calculateShippingFee(Order order) {
        return shippingFeeStrategy.calculateShippingFee(order);
    }
}

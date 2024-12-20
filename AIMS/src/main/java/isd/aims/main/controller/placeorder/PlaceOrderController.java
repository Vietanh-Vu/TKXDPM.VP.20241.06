package isd.aims.main.controller.placeorder;

import isd.aims.main.controller.BaseController;
import isd.aims.main.controller.placeorder.ordervalidator.DeliveryInfoValidator;
import isd.aims.main.controller.placeorder.ordervalidator.RushInfoValidator;
import isd.aims.main.controller.placeorder.ordervalidator.StandardInfoValidator;
import isd.aims.main.controller.placeorder.shippingfee.RushShippingFee;
import isd.aims.main.controller.placeorder.shippingfee.ShippingFeeStrategy;
import isd.aims.main.controller.placeorder.shippingfee.StandardShippingFee;
import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.cart.CartMedia;
import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;
import isd.aims.main.exception.InvalidDeliveryInfoException;
import isd.aims.main.utils.Utils;
import lombok.AllArgsConstructor;
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
    private DeliveryInfoValidator deliveryInfoValidator = new StandardInfoValidator();

    public PlaceOrderController(ShippingFeeStrategy shippingFeeStrategy, DeliveryInfoValidator deliveryInfoValidator) {
        this.shippingFeeStrategy = shippingFeeStrategy;
        this.deliveryInfoValidator = deliveryInfoValidator;
    }

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method takes responsibility for processing the shipping info from user
     *
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(DeliveryInfo info) {
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        // Kiểm tra kết quả validate
        if (!validateDeliveryInfo(info)) {
            throw new InvalidDeliveryInfoException("Invalid delivery information provided: " + info.toString());
        }
        // Nếu validate thành công, tiếp tục xử lý
        LOGGER.info("Delivery info validated successfully.");    }

    /**
     * The method validates the info
     *
     * @param info
     */
    public boolean validateDeliveryInfo(DeliveryInfo info) {
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

    public void setRushController(){
         this.shippingFeeStrategy = new RushShippingFee();
         this.deliveryInfoValidator = new RushInfoValidator();
    }

    public void setStandardController(){
        this.shippingFeeStrategy = new StandardShippingFee();
        this.deliveryInfoValidator = new StandardInfoValidator();
    }
}

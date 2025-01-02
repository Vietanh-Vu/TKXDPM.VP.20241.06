package isd.aims.main.controller.placeorder;

import isd.aims.main.controller.BaseController;
import isd.aims.main.controller.placeorder.ordervalidator.DeliveryInfoValidator;
import isd.aims.main.controller.placeorder.shippingfee.ShippingFeeStrategy;
import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.entity.order.Order;
import isd.aims.main.exception.InvalidDeliveryInfoException;
import isd.aims.main.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * This class controls the flow of place order usecase in our AIMS project
 *
 * @author nguyenlm
 */

// Procedural Cohesion: các phương thức đều liên quan đến việc xử lý đặt hàng
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderController extends BaseController {

    protected ShippingFeeStrategy shippingFeeStrategy;
    protected DeliveryInfoValidator deliveryInfoValidator;

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
        if (!validateDeliveryInfo(info)) {
            throw new InvalidDeliveryInfoException("Invalid delivery information provided: " + info.toString());
        }
        LOGGER.info("Delivery info validated successfully.");    }

    /**
     * The method validates the info
     *
     * @param info
     */
    public boolean validateDeliveryInfo(DeliveryInfo info) {
        return deliveryInfoValidator.validateDeliveyInfo(info);
    }

    public String notifyInvalidInfo(DeliveryInfo info) {
        return deliveryInfoValidator.notifyInvalidInfo(info);
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

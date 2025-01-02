package isd.aims.main.controller.placeorder;

import isd.aims.main.controller.placeorder.ordervalidator.StandardInfoValidator;
import isd.aims.main.controller.placeorder.shippingfee.StandardShippingFee;

public class StandardPlaceOrderController extends PlaceOrderController{
    public StandardPlaceOrderController() {
        super();
        this.deliveryInfoValidator = new StandardInfoValidator();
        this.shippingFeeStrategy = new StandardShippingFee();
    }
}

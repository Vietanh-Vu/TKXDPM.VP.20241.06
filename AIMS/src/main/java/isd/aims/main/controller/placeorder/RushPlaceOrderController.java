package isd.aims.main.controller.placeorder;

import isd.aims.main.controller.placeorder.ordervalidator.RushInfoValidator;
import isd.aims.main.controller.placeorder.shippingfee.RushShippingFee;

public class RushPlaceOrderController extends PlaceOrderController{
    public RushPlaceOrderController() {
        super();
        this.deliveryInfoValidator = new RushInfoValidator();
        this.shippingFeeStrategy = new RushShippingFee();
    }
}

package isd.aims.main.controller.placeorder.shippingfee;

import isd.aims.main.entity.order.Order;

public class RushShippingFee implements ShippingFeeStrategy{
    @Override
    public int calculateShippingFee(Order order) {
        return 150;
    };
}
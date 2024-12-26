package isd.aims.main.controller.placeorder.shippingfee;

import isd.aims.main.entity.order.Order;

public interface ShippingFeeStrategy {
    int calculateShippingFee(Order order);
}

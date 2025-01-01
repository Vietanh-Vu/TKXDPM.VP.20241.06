package isd.aims.main.controller.placeorder.shippingfee;

import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;

public class StandardShippingFee extends BaseShippingFee{
    public int calculateShippingFee(Order order) {
        double heaviestItemWeight = order.getHeaviestItemWeight();
        int baseFee = calculateBaseFee(heaviestItemWeight, order.getDeliveryInfo().getProvince());
        return applyFreeShippingCap(baseFee, order.getAmount());
    }

}

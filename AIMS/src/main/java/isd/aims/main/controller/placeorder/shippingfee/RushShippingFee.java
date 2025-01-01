package isd.aims.main.controller.placeorder.shippingfee;

import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;

public class RushShippingFee extends BaseShippingFee{
    @Override
    public int calculateShippingFee(Order order) {
        int numberOfRushItems = order.getNumberOfRushItems();
        int rushFee = numberOfRushItems * 10_000;
        double heaviestItemWeight = order.getHeaviestNoRushItemWeight();
        int baseFee = calculateBaseFee(heaviestItemWeight, order.getDeliveryInfo().getProvince());
        return rushFee + baseFee;
    }
}
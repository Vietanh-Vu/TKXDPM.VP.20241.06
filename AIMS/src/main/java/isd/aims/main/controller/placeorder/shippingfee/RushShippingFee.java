package isd.aims.main.controller.placeorder.shippingfee;

import isd.aims.main.entity.media.Media;
import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;

public class RushShippingFee implements ShippingFeeStrategy{
    @Override
    public int calculateShippingFee(Order order) {
        int numberOfRushItems = getNumberOfRushItems(order);
        return numberOfRushItems * 10_000;
    }

    private int getNumberOfRushItems(Order order) {
        int numberOfRushItems = 0;
        for (Object obj: order.getLstOrderMedia()){
            OrderMedia om = (OrderMedia) obj;
            if (om.getMedia().isRush()){
                numberOfRushItems += om.getQuantity();
            }
        }
        return numberOfRushItems;
    }
}
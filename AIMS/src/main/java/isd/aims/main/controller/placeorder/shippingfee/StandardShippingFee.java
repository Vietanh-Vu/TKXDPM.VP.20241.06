package isd.aims.main.controller.placeorder.shippingfee;

import isd.aims.main.entity.media.Media;
import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;

import java.util.Random;

import static isd.aims.main.views.home.HomeForm.LOGGER;

public class StandardShippingFee implements ShippingFeeStrategy{
    @Override
    public int calculateShippingFee(Order order) {
        int shippingFee = 0;

        if (order.getAmount() <= 100_000) {
            double baseWeightLimit = (order.getDeliveryInfo().getProvince().equals("Hà Nội")) ? 3.0 : 0.5;
            int baseFee = (order.getDeliveryInfo().getProvince().equals("Hà Nội")) ? 22_000 : 30_000;
            int extraFeePerHalfKg = 2_500;

            double heaviestItemWeight = getHeaviestItemWeight(order);

            // Calculate base shipping fee
            if (heaviestItemWeight <= baseWeightLimit) {
                shippingFee = baseFee;
            } else {
                // Add additional charges for extra weight
                double extraWeight = heaviestItemWeight - baseWeightLimit;
                int additionalUnits = (int) Math.ceil(extraWeight / 0.5);
                shippingFee = baseFee + (additionalUnits * extraFeePerHalfKg);
            }
        }

        // Cap free shipping at 25,000 VND
        if (order.getAmount() > 100_000 && shippingFee > 25_000) {
            shippingFee = 25_000;
        }

        return shippingFee;
    }

    private double getHeaviestItemWeight(Order order){
        double heaviestItemWeight = 0;
        for (Object obj: order.getLstOrderMedia()){
            // Order Media
            OrderMedia om = (OrderMedia) obj;
            if (om.getMedia().getWeight() >= heaviestItemWeight){
                heaviestItemWeight = om.getMedia().getWeight();
            }
        }
        return heaviestItemWeight;
    }
}

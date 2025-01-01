package isd.aims.main.controller.placeorder.shippingfee;

import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.order.OrderMedia;

public abstract class BaseShippingFee implements ShippingFeeStrategy {

    protected int calculateBaseFee(double weight, String province) {
        if (weight == 0) {
            System.out.println("Base Fee là: 0");
            return 0;
        }
        double baseWeightLimit = province.equals("Hà Nội") ? 3.0 : 0.5;
        int baseFee = province.equals("Hà Nội") ? 22_000 : 30_000;
        int extraFeePerHalfKg = 2_500;

        if (weight <= baseWeightLimit) {
            System.out.println("Base Fee là: " + baseFee);
            return baseFee;
        } else {
            double extraWeight = weight - baseWeightLimit;
            int additionalUnits = (int) Math.ceil(extraWeight / 0.5);
            System.out.println("Base Fee là: " + baseFee);
            return baseFee + (additionalUnits * extraFeePerHalfKg);
        }

    }

    protected int applyFreeShippingCap(int shippingFee, int orderAmount) {
        if (orderAmount > 100_000 && shippingFee > 25_000) {
            return shippingFee - 25_000;
        }
        return shippingFee;
    }
}

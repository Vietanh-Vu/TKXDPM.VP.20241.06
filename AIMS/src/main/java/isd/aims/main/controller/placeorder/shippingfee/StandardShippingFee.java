package isd.aims.main.controller.placeorder.shippingfee;

import isd.aims.main.entity.order.Order;

import java.util.Random;

import static isd.aims.main.views.home.HomeForm.LOGGER;

public class StandardShippingFee implements ShippingFeeStrategy{
    @Override
    public int calculateShippingFee(Order order) {
        Random rand = new Random();
        int fees = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
//        return 100;
    };
}

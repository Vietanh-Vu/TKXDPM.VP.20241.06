package isd.aims.main.entity.order;

import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.utils.Configs;
import isd.aims.main.entity.cart.CartMedia;
import isd.aims.main.entity.cart.Cart;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
@ToString
public class Order {

    private int shippingFees;
    private List lstOrderMedia;
    private Integer id;
    private DeliveryInfo deliveryInfo;
    private boolean isRush = false;

    public Order(){
        this.lstOrderMedia = new ArrayList<>();
    }

    public int getAmount(){
        double amount = 0;
        for (Object object : lstOrderMedia) {
            OrderMedia om = (OrderMedia) object;
            amount += om.getPrice();
        }
        return (int) (amount + (Configs.PERCENT_VAT/100)*amount);
    }

    public static Order createOrder() {
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(),
                    cartMedia.getQuantity(),
                    cartMedia.getPrice());
            order.getLstOrderMedia().add(orderMedia);
        }
        return order;
    }

}

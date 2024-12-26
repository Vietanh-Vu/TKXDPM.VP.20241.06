package isd.aims.main.entity.order;

import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.cart.CartMedia;
import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.utils.Configs;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Order {

    private int shippingFees;
    private List<OrderMedia> lstOrderMedia;
    private Integer id;
    private DeliveryInfo deliveryInfo;
    private boolean isRush = false;
    private String paymentType;
    private String orderStatus;
    private int totalAmount;

    public Order(){
        this.lstOrderMedia = new ArrayList<>();
    }

    public int getAmount(){
        int amountTmp = 0;
        for (OrderMedia object : lstOrderMedia) {
            amountTmp += object.getPrice();
        }
        this.setTotalAmount(amountTmp);

        return (int) (totalAmount + (Configs.PERCENT_VAT/100)*totalAmount);
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

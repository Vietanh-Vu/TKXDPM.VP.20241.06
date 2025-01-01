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
    private String id;
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
            amountTmp += object.getPrice() * object.getQuantity();
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

    public int getNumberOfRushItems() {
        int numberOfRushItems = 0;
        for (Object obj : this.getLstOrderMedia()) {
            OrderMedia om = (OrderMedia) obj;
            if (om.getMedia().isRush()) {
                numberOfRushItems += om.getQuantity();
            }
        }
        return numberOfRushItems;
    }

    public double getHeaviestItemWeight() {
        double heaviestItemWeight = 0;
        for (Object obj : this.getLstOrderMedia()) {
            OrderMedia om = (OrderMedia) obj;
            if (om.getMedia().getWeight() > heaviestItemWeight) {
                heaviestItemWeight = om.getMedia().getWeight();
            }
        }
        return heaviestItemWeight;
    }

    public double getHeaviestNoRushItemWeight() {
        double heaviestItemWeight = 0;
        for (Object obj : this.getLstOrderMedia()) {
            OrderMedia om = (OrderMedia) obj;
            if (om.getMedia().getWeight() > heaviestItemWeight && om.getMedia().isRush() == false) {
                heaviestItemWeight = om.getMedia().getWeight();
            }
        }
        return heaviestItemWeight;
    }
}

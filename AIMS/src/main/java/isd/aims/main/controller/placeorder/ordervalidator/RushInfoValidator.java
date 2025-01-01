package isd.aims.main.controller.placeorder.ordervalidator;

import isd.aims.main.entity.cart.Cart;
import isd.aims.main.entity.cart.CartMedia;

import java.util.List;

public class RushInfoValidator extends DeliveryInfoValidator{
    @Override
    protected boolean validateProvince(String province){
        if (province.equals("Hà Nội")){
            return true;
        }
        return false;
    }

    @Override
    protected boolean validateProducts() {
        List<CartMedia> listMedia = Cart.getCart().getListMedia();
        for (CartMedia cartMedia : listMedia) {
            if (cartMedia.getMedia().isRush()) {
                return true;
            }
        }
        System.out.println("Không có rush products trong giỏ hàng!!!");
        return false;
    }
}

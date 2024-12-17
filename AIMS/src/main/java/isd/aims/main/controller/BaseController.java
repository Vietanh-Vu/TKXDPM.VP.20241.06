package isd.aims.main.controller;

import isd.aims.main.entity.Cart;
import isd.aims.main.entity.CartMedia;
import isd.aims.main.entity.media.Media;

import java.util.List;

/**
 * This class is the base controller for our AIMS project
 * @author nguyenlm
 */
public class BaseController {

    /**
     * The method checks whether the Media in Cart, if it were in, we will return the CartMedia else return null
     * @param media
     * @return CartMedia or null
     */
    // Stamp Coupling: nhận Media làm tham số nhưng chỉ cần id của Media
    // => Nên chỉ cần truyền id của Media
    public CartMedia checkMediaInCart(Media media){
        return Cart.getCart().checkMediaInCart(media);
    }

    /**
     * This method gets the list of items in cart
     * @return List[CartMedia]
     */
    @SuppressWarnings("rawtypes")
    public List getListCartMedia(){
        return Cart.getCart().getListMedia();
    }
}

package isd.aims.main.entity.order;

import isd.aims.main.controller.placeorder.PlaceOrderController;
import isd.aims.main.controller.placeorder.RushPlaceOrderController;
import isd.aims.main.controller.placeorder.StandardPlaceOrderController;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {
    StandardOrder("Standard order", new StandardPlaceOrderController()),
    RushOrder("Rush order", new RushPlaceOrderController());

    private final String orderType;
    private final PlaceOrderController placeOrderController;

    public static PlaceOrderController getPlaceOrderController(String orderType){

        for (OrderType orderTypeEnum : OrderType.values()) {
            if (orderTypeEnum.getOrderType().equals(orderType)) {
                return orderTypeEnum.getPlaceOrderController();
            }
        }

        System.out.println("Phương thức giao hàng " + orderType + " chưa hợp lệ hoặc chưa được hệ thống hỗ trợ");
        return null;
    }
}

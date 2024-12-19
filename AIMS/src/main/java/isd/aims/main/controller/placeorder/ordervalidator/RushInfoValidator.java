package isd.aims.main.controller.placeorder.ordervalidator;

public class RushInfoValidator extends DeliveryInfoValidator{
    @Override
    protected boolean validateProvince(String province){
        if (province.equals("Ha Noi")){
            return true;
        }
        return false;
    }
}

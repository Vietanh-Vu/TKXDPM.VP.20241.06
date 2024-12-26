package isd.aims.main.controller.placeorder.ordervalidator;

public class RushInfoValidator extends DeliveryInfoValidator{
    @Override
    protected boolean validateProvince(String province){
        if (province.equals("Hà Nội")){
            return true;
        }
        return false;
    }
}

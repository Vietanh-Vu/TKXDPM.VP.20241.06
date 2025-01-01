package isd.aims.main.controller.placeorder.ordervalidator;

public class StandardInfoValidator extends DeliveryInfoValidator{
    @Override
    protected boolean validateProvince(String province) {
        if (province == null) {
            return false;
        }

        // Check for empty string
        if (province.isEmpty()) {
            return false;
        }

        return true;
    }

}

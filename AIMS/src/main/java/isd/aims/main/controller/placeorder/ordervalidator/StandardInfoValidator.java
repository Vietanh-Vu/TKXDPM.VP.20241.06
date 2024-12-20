package isd.aims.main.controller.placeorder.ordervalidator;

import isd.aims.main.entity.deliveryinfo.DeliveryInfo;

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

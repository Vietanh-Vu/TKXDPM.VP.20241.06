package isd.aims.main.controller.placeorder.ordervalidator;

import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.entity.order.Order;

import java.util.regex.Pattern;

public abstract class DeliveryInfoValidator {
    public final boolean validateDeliveyInfo(DeliveryInfo deliveryInfo) {
        return validatePhoneNumber(deliveryInfo.getPhoneNumber()) &&
                validateAddress(deliveryInfo.getAddress()) &&
                validateName(deliveryInfo.getName()) &&
                validateEmail(deliveryInfo.getEmail()) &&
                validateProvince(deliveryInfo.getProvince());
    }

    protected boolean validatePhoneNumber(String phoneNumber) {
        // Kiểm tra null hoặc chuỗi rỗng
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }

        // Kiểm tra bắt đầu bằng 0
        if (!phoneNumber.startsWith("0")) {
            return false;
        }

        // Loại bỏ các ký tự phân cách
        String cleanedPhoneNumber = phoneNumber.replaceAll("[.-/]", "");

        // Kiểm tra độ dài và chỉ chứa số
        if (cleanedPhoneNumber.length() != 10 ||
                !cleanedPhoneNumber.matches("\\d+")) {
            return false;
        }

        // Kiểm tra các ký tự phân cách
        String separators = phoneNumber.replaceAll("\\d", "");

        if (!separators.isEmpty()) {
            // Kiểm tra chỉ có một loại ký tự phân cách
            char firstSeparator = separators.charAt(0);
            for (char c : separators.toCharArray()) {
                if (c != firstSeparator) {
                    return false;
                }
            }

            // Kiểm tra vị trí ký tự phân cách
            String[] parts = phoneNumber.split("[.-/]");
            for (String part : parts) {
                if (part.isEmpty()) {
                    return false;
                }
            }

            // Kiểm tra định dạng phân cách
            // Chỉ chấp nhận các định dạng như 0-xxx-xxx-xxx, 0.xxx.xxx.xxx, 0/xxx/xxx/xxx
            if (!phoneNumber.matches("^0[.-/]\\d{3}[.-/]\\d{3}[.-/]\\d{3}$")) {
                return false;
            }
        }

        System.out.println("Phone number true");
        return true;
    };
    protected boolean validateAddress(String address) {
        // Check null
        if (address == null) {
            return false;
        }

        // Check length (0 < length <= 100)
        if (address.isEmpty() || address.length() > 100) {
            return false;
        }

        // Check valid characters (only letters, numbers and forward slash)
        String regex = "^[a-zA-Z0-9/]+$";

        System.out.println("Address : " + address.matches(regex));
        return address.matches(regex);
    };
    protected boolean validateName(String name) {
        // Check for null
        if (name == null) {
            return false;
        }

        // Check for empty string
        if (name.isEmpty()) {
            return false;
        }

        // Check length <= 30
        if (name.length() > 30) {
            return false;
        }

        System.out.println("Name : " + name.matches("[a-zA-Z]+"));
                // Check if contains only letters
        return name.matches("[a-zA-Z]+");
    }
    protected boolean validateEmail(String email) {
        String EMAIL_REGEX = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        if (email == null || email.isEmpty()) {
            return false; // Email can't be null or empty
        }
        System.out.println("Email : " + Pattern.matches(EMAIL_REGEX, email));
        return Pattern.matches(EMAIL_REGEX, email);
    }
    protected boolean validateProvince(String province) {
        return true;
    }
}
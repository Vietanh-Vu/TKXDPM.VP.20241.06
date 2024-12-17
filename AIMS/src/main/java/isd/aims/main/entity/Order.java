package isd.aims.main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    // DeliveryInfo fields
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String province;

    // Order specific fields
    private Integer shippingFee;
    private Double totalAmount;
    private String paymentStatus;
    private String paymentType;
}

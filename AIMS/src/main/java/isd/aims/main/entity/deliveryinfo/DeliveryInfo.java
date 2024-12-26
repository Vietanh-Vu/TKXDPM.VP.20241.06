package isd.aims.main.entity.deliveryinfo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DeliveryInfo {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String province;
}

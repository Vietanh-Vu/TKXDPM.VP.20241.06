package isd.aims.main.entity.deliveryinfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeliveryInfo {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private String province;
}

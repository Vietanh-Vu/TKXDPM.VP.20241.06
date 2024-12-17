package isd.aims.main.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMedia {
    private int mediaId;
    private int orderId;
    private int quantity;
}

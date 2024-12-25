package isd.aims.main.entity.invoice;


import isd.aims.main.entity.order.Order;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Functional Cohesion: Các thuộc tính và phương thức quản lý hóa đơn
@ToString
@Getter
@Setter
public class Invoice {

    private Order order;
    private int amount;

    public Invoice(Order order){
        this.order = order;
    }
}

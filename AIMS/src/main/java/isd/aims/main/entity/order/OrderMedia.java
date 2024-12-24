package isd.aims.main.entity.order;


import isd.aims.main.entity.media.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderMedia {
    
    private Media media;
    private int price;
    private int quantity;

}

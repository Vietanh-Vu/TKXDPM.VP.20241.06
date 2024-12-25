package isd.aims.main.entity.order;


import isd.aims.main.entity.media.Media;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderMedia {
    private Media media;
    private int quantity;
    private int price;
}

package isd.aims.main.utils.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefundTransaction {
    private String id;
    private String message;
    private String errorCode;
    private int amount;
    private String content;
}

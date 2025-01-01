package isd.aims.main.controller.mail;

import isd.aims.main.utils.Utils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VNPayInfo implements PaymentInfo {
    private final String txnRef;
    private final String transactionNo;
    private final String transactionDate;

    @Override
    public String getTransactionDetails() {
        return "<tr><th>Số hóa đơn</th><td>" + txnRef + "</td></tr>" +
                "<tr><th>Mã giao dịch</th><td>" + transactionNo + "</td></tr>" +
                "<tr><th>Ngày giao dịch</th><td>" + Utils.convertTime(transactionDate, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss")  + "</td></tr>";
    }

    @Override
    public boolean hasTransactionDetails() {
        return true;
    }
}

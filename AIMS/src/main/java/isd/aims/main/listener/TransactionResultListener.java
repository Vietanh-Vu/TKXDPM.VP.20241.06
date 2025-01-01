package isd.aims.main.listener;

import isd.aims.main.entity.invoice.Invoice;
import isd.aims.main.entity.payment.PaymentTransaction;

public interface TransactionResultListener {
    void onTransactionCompleted(PaymentTransaction transactionResult, Invoice invoice);
}


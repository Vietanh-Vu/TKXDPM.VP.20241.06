package isd.aims.main.listener;

import isd.aims.main.entity.PaymentTransaction;

public interface TransactionResultListener {
    void onTransactionCompleted(PaymentTransaction transactionResult);
}


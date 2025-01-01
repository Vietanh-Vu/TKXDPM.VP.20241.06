package isd.aims.main.entity.db.dao.payment_transaction;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.payment.PaymentTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;

class PaymentTransactionMapDbToClass implements MapDbToClass<PaymentTransaction> {
    @Override
    public PaymentTransaction mapRow(ResultSet resultSet) throws SQLException {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setId(resultSet.getString("id"));
        transaction.setOrderId(resultSet.getString("orderID"));
        transaction.setContent(resultSet.getString("content"));
        String timestampStr = resultSet.getString("createAt");
        transaction.setCreatedAt(LocalDateTime.parse(timestampStr));
        transaction.setStatus(resultSet.getString("status"));
        transaction.setAmount(resultSet.getInt("amount"));
        transaction.setPaymentType(resultSet.getString("paymentType"));
        transaction.setTransactionNumber(resultSet.getString("transactionNum"));

        return transaction;
    }
}
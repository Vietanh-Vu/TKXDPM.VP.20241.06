package isd.aims.main.entity.db.dao.paymentTransaction;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.payment.PaymentTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

class PaymentTransactionMapDbToClass implements MapDbToClass<PaymentTransaction> {
    @Override
    public PaymentTransaction mapRow(ResultSet resultSet) throws SQLException {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setId(resultSet.getString("id"));
        transaction.setOrderId(resultSet.getString("orderID"));
        transaction.setContent(resultSet.getString("content"));
        transaction.setCreatedAt(LocalDateTime.from(resultSet.getTimestamp("createAt").toInstant()));
        transaction.setStatus(resultSet.getString("status"));
        transaction.setAmount(resultSet.getInt("amount"));
        transaction.setPaymentType(resultSet.getString("paymentType"));
        transaction.setTransactionNumber(resultSet.getString("transactionNum"));

        return transaction;
    }
}
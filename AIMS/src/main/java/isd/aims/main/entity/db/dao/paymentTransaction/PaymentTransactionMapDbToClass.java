package isd.aims.main.entity.db.dao.paymentTransaction;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.payment.PaymentTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

class PaymentTransactionMapDbToClass implements MapDbToClass<PaymentTransaction> {
    @Override
    public PaymentTransaction mapRow(ResultSet resultSet) throws SQLException {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setOrderId(resultSet.getString("orderID"));
        transaction.setContent(resultSet.getString("content"));
        transaction.setCreatedAt(Date.from(resultSet.getTimestamp("createAt").toInstant()));
        transaction.setMessage(resultSet.getString("message"));
        transaction.setAmount(resultSet.getInt("amount"));

        return transaction;
    }
}
package isd.aims.main.entity.db.dao.transaction;

import isd.aims.main.entity.PaymentTransaction;
import isd.aims.main.entity.db.MapDbToClass;

import java.sql.ResultSet;
import java.sql.SQLException;

class PaymentTransactionMapDbToClass implements MapDbToClass<PaymentTransaction> {
    @Override
    public PaymentTransaction mapRow(ResultSet resultSet) throws SQLException {
        PaymentTransaction transaction = new PaymentTransaction();
        transaction.setId(resultSet.getInt("id"));
        transaction.setOrderId(resultSet.getInt("orderID"));
        transaction.setCreateAt(resultSet.getTimestamp("createAt").toLocalDateTime());
        transaction.setContent(resultSet.getString("content"));
        return transaction;
    }
}

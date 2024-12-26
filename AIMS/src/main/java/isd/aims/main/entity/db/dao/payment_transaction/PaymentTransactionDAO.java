package isd.aims.main.entity.db.dao.payment_transaction;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.payment.PaymentTransaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentTransactionDAO extends DAO<PaymentTransaction> {
    @Override
    public List<PaymentTransaction> getAll() {
        String query = "SELECT * FROM PaymentTransaction";
        try {
            return findAll(query, new PaymentTransactionMapDbToClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public PaymentTransaction getById(int id) {
        String query = "SELECT * FROM PaymentTransaction WHERE id = ?";
        try {
            return findOne(query, new PaymentTransactionMapDbToClass(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PaymentTransaction add(PaymentTransaction transaction) {
        String query = "INSERT INTO PaymentTransaction (orderID, content, createAt, status, amount, paymentType, transactionNum) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            executeUpdate(query,
                    transaction.getOrderId(),
                    transaction.getContent(),
                    transaction.getCreatedAt(),
                    transaction.getStatus(),
                    transaction.getAmount(),
                    transaction.getPaymentType(),
                    transaction.getTransactionNumber()
            );
            return transaction;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(PaymentTransaction transaction) {
        String query = "UPDATE PaymentTransaction SET orderID = ?, content = ?, createAt = ?, status = ?, " +
                "amount = ?, paymentType = ?, transactionNum = ? WHERE id = ?";
        try {
            return executeUpdate(query,
                    transaction.getOrderId(),
                    transaction.getContent(),
                    transaction.getCreatedAt(),
                    transaction.getStatus(),
                    transaction.getAmount(),
                    transaction.getPaymentType(),
                    transaction.getTransactionNumber(),
                    transaction.getId()
            ) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM PaymentTransaction WHERE id = ?";
        try {
            return executeUpdate(query, id) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
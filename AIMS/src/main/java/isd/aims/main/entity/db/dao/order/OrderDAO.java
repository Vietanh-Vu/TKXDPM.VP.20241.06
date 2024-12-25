package isd.aims.main.entity.db.dao.order;

import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.db.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DAO<Order> {
    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM `Order`";
        try {
            return findAll(query, new OrderMapDbToClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Order getById(int id) {
        String query = "SELECT * FROM `Order` WHERE id = ?";
        try {
            return findOne(query, new OrderMapDbToClass(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order add(Order order) {
        String query = "INSERT INTO `Order` (name, email, address, phone, province, " +
                "shipping_fee, totalAmount, paymentStatus, paymentType) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            executeUpdate(query, order.getDeliveryInfo().getName(), order.getDeliveryInfo().getEmail(), order.getDeliveryInfo().getAddress(),
                    order.getDeliveryInfo().getPhoneNumber(), order.getDeliveryInfo().getProvince(), order.getShippingFees(),
                    order.getAmount(), order.getPaymentStatus(), order.getPaymentType());
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Order order) {
        String query = "UPDATE `Order` SET name = ?, email = ?, address = ?, phone = ?, " +
                "province = ?, shipping_fee = ?, totalAmount = ?, " +
                "paymentStatus = ?, paymentType = ? WHERE id = ?";
        try {
            return executeUpdate(query, order.getDeliveryInfo().getName(), order.getDeliveryInfo().getEmail(), order.getDeliveryInfo().getAddress(),
                    order.getDeliveryInfo().getPhoneNumber(), order.getDeliveryInfo().getProvince(), order.getShippingFees(),
                    order.getAmount(), order.getPaymentStatus(), order.getPaymentType(),
                    order.getId()) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM `Order` WHERE id = ?";
        try {
            return executeUpdate(query, id) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


package isd.aims.main.entity.db.dao.order_media;

import isd.aims.main.entity.order.OrderMedia;
import isd.aims.main.entity.db.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderMediaDAO extends DAO<OrderMedia> {
    @Override
    public List<OrderMedia> getAll() {
        String query = "SELECT * FROM OrderMedia";
        try {
            return findAll(query, new OrderMediaMapDbToClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public OrderMedia getById(int id) {
        // Not applicable for composite key
        return null;
    }

    @Override
    public OrderMedia add(OrderMedia orderMedia) {
        return null;
    }

    public OrderMedia getById(int mediaId, int orderId) {
        String query = "SELECT * FROM OrderMedia WHERE mediaID = ? AND orderID = ?";
        try {
            return findOne(query, new OrderMediaMapDbToClass(), mediaId, orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OrderMedia add(OrderMedia orderMedia, int orderId) {
        String query = "INSERT INTO OrderMedia (mediaID, orderID, quantity) VALUES (?, ?, ?)";
        try {
            executeUpdate(query, orderMedia.getMedia().getId(), orderId,
                    orderMedia.getQuantity());
            return orderMedia;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(OrderMedia orderMedia) {
        String query = "UPDATE OrderMedia SET quantity = ? WHERE mediaID = ?";
        try {
            return executeUpdate(query, orderMedia.getQuantity(), orderMedia.getMedia().getId()) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        // Not applicable for composite key
        return false;
    }

    public boolean delete(int mediaId, int orderId) {
        String query = "DELETE FROM OrderMedia WHERE mediaID = ? AND orderID = ?";
        try {
            return executeUpdate(query, mediaId, orderId) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Thêm lệnh cho phần refund
    // Tìm các orderMedia theo OrderId
    public List<OrderMedia> getByOrderId(int orderId) {
        String query = "SELECT * FROM OrderMedia WHERE orderID = ?";
        try {
            return findAll(query, new OrderMediaMapDbToClass(), orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
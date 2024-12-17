package isd.aims.main.entity.db.dao.order_media;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.OrderMedia;

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

    public OrderMedia getByIds(int mediaId, int orderId) {
        String query = "SELECT * FROM OrderMedia WHERE mediaID = ? AND orderID = ?";
        try {
            return findOne(query, new OrderMediaMapDbToClass(), mediaId, orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderMedia add(OrderMedia orderMedia) {
        String query = "INSERT INTO OrderMedia (mediaID, orderID, quantity) VALUES (?, ?, ?)";
        try {
            executeUpdate(query, orderMedia.getMediaId(), orderMedia.getOrderId(),
                    orderMedia.getQuantity());
            return orderMedia;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(OrderMedia orderMedia) {
        String query = "UPDATE OrderMedia SET quantity = ? WHERE mediaID = ? AND orderID = ?";
        try {
            return executeUpdate(query, orderMedia.getQuantity(), orderMedia.getMediaId(),
                    orderMedia.getOrderId()) > 0;
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
}

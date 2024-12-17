package isd.aims.main.entity.db.dao.order;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

class OrderMapDbToClass implements MapDbToClass<Order> {
    @Override
    public Order mapRow(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setName(resultSet.getString("name"));
        order.setEmail(resultSet.getString("email"));
        order.setAddress(resultSet.getString("address"));
        order.setPhone(resultSet.getString("phone"));
        order.setProvince(resultSet.getString("province"));
        order.setShippingFee(resultSet.getInt("shipping_fee"));
        order.setTotalAmount(resultSet.getDouble("totalAmount"));
        order.setPaymentStatus(resultSet.getString("paymentStatus"));
        order.setPaymentType(resultSet.getString("paymentType"));
        return order;
    }
}

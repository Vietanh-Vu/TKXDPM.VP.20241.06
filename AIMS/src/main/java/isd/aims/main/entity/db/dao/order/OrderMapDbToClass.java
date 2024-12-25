package isd.aims.main.entity.db.dao.order;

import isd.aims.main.entity.deliveryinfo.DeliveryInfo;
import isd.aims.main.entity.order.Order;
import isd.aims.main.entity.db.MapDbToClass;

import java.sql.ResultSet;
import java.sql.SQLException;

class OrderMapDbToClass implements MapDbToClass<Order> {
    @Override
    public Order mapRow(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        DeliveryInfo tempDI = order.getDeliveryInfo();
        tempDI.setName(resultSet.getString("name"));
        tempDI.setEmail(resultSet.getString("email"));
        tempDI.setAddress(resultSet.getString("address"));
        tempDI.setPhoneNumber(resultSet.getString("phone"));
        tempDI.setProvince(resultSet.getString("province"));
        order.setId(resultSet.getInt("id"));
        order.setDeliveryInfo(tempDI);
        order.setShippingFees(resultSet.getInt("shipping_fee"));
        order.setPaymentStatus(resultSet.getString("paymentStatus"));
        order.setPaymentType(resultSet.getString("paymentType"));
        return order;
    }
}

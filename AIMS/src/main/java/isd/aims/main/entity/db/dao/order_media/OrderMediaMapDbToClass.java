package isd.aims.main.entity.db.dao.order_media;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.OrderMedia;

import java.sql.ResultSet;
import java.sql.SQLException;

class OrderMediaMapDbToClass implements MapDbToClass<OrderMedia> {
    @Override
    public OrderMedia mapRow(ResultSet resultSet) throws SQLException {
        OrderMedia orderMedia = new OrderMedia();
        orderMedia.setMediaId(resultSet.getInt("mediaID"));
        orderMedia.setOrderId(resultSet.getInt("orderID"));
        orderMedia.setQuantity(resultSet.getInt("quantity"));
        return orderMedia;
    }
}

package isd.aims.main.entity.db.dao.order_media;

import isd.aims.main.entity.db.dao.Media.MediaDAO;
import isd.aims.main.entity.media.Media;
import isd.aims.main.entity.order.OrderMedia;
import isd.aims.main.entity.db.MapDbToClass;

import java.sql.ResultSet;
import java.sql.SQLException;

class OrderMediaMapDbToClass implements MapDbToClass<OrderMedia> {
    @Override
    public OrderMedia mapRow(ResultSet resultSet) throws SQLException {
        OrderMedia orderMedia = new OrderMedia();
        Media media = new MediaDAO().getById(resultSet.getInt("mediaID"));
        orderMedia.setMedia(media);
        orderMedia.setQuantity(resultSet.getInt("quantity"));
        return orderMedia;
    }
}
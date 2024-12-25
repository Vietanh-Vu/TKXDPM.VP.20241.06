package isd.aims.main.entity.db.dao.Media;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaMapDbToClass implements MapDbToClass<Media> {

    @Override
    public Media mapRow(ResultSet resultSet) throws SQLException {
        Media media = new Media();
        media.setId(resultSet.getInt("id"));
        media.setTitle(resultSet.getString("title"));
        media.setCategory(resultSet.getString("category"));
        media.setValue(resultSet.getInt("value"));
        media.setPrice(resultSet.getInt("price"));
        media.setQuantity(resultSet.getInt("quantity"));
        media.setType(resultSet.getString("type"));
        media.setImageURL(resultSet.getString("imageURL"));
        return media;
    }
}

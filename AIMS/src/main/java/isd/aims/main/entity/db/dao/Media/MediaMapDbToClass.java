package isd.aims.main.entity.db.dao.Media;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MediaMapDbToClass implements MapDbToClass<Media> {

    @Override
    public Media mapResult(ResultSet res) throws SQLException {
        Media media = new Media();
        media.setId(res.getInt("id"));
        media.setId(res.getInt("id"));
        media.setTitle(res.getString("title"));
        media.setQuantity(res.getInt("quantity"));
        media.setCategory(res.getString("category"));
        media.setMediaURL(res.getString("imageUrl"));
        media.setPrice(res.getInt("price"));
        media.setType(res.getString("type"));

        return media;
    }
}

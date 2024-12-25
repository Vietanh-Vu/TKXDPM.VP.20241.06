package isd.aims.main.entity.db.dao.CD;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.media.CD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CDMapDbToClass implements MapDbToClass<CD> {

    @Override
    public CD mapResult(ResultSet res) throws SQLException {
        CD cd = new CD();
        cd.setId(res.getInt("id"));
        cd.setTitle("");
        cd.setCategory(res.getString("category"));
        cd.setPrice(res.getInt("price"));
        cd.setQuantity(res.getInt("quantity"));
        cd.setType(res.getString("type"));
        cd.setArtist(res.getString("artist"));
        cd.setRecordLabel(res.getString("recordLabel"));
        cd.setMusicType(res.getString("musicType"));

        return cd;
    }
}

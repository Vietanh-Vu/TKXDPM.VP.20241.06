package isd.aims.main.entity.db.dao.DVD;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.media.DVD;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DVDMapDbToClass implements MapDbToClass<DVD> {
    @Override
    public DVD mapResult(ResultSet res) throws SQLException {
        DVD dvd = new DVD();
        dvd.setId(res.getInt("id"));
        dvd.setTitle("");
        dvd.setCategory(res.getString("category"));
        dvd.setPrice(res.getInt("price"));
        dvd.setQuantity(res.getInt("quantity"));
        dvd.setType(res.getString("type"));
        dvd.setDiscType(res.getString("discType"));
        dvd.setDirector(res.getString("director"));
        dvd.setRuntime(res.getInt("runtime"));
        dvd.setStudio(res.getString("studio"));
        dvd.setSubtitles(res.getString("subtitle"));
        dvd.setFilmType(res.getString("filmType"));

        return null;
    }
}

package isd.aims.main.entity.db.dao.dvd;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.media.DVD;

import java.sql.ResultSet;
import java.sql.SQLException;

class DvdMapDbToClass implements MapDbToClass<DVD> {
    @Override
    public DVD mapRow(ResultSet resultSet) throws SQLException {
        DVD dvd = new DVD();
        dvd.setId(resultSet.getInt("id"));
        dvd.setDiscType(resultSet.getString("discType"));
        dvd.setDirector(resultSet.getString("director"));
        dvd.setRuntime(resultSet.getInt("runtime"));
        dvd.setStudio(resultSet.getString("studio"));
        dvd.setSubtitle(resultSet.getString("subtitle"));
        dvd.setReleasedDate(resultSet.getTimestamp("releasedDate"));
        dvd.setFilmType(resultSet.getString("filmType"));
        return dvd;
    }
}

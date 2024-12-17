package isd.aims.main.entity.db.dao.cd;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.media.CD;

import java.sql.ResultSet;
import java.sql.SQLException;

class CdMapDbToClass implements MapDbToClass<CD> {
    @Override
    public CD mapRow(ResultSet resultSet) throws SQLException {
        CD cd = new CD();
        cd.setId(resultSet.getInt("id"));
        cd.setArtist(resultSet.getString("artist"));
        cd.setRecordLabel(resultSet.getString("recordLabel"));
        cd.setMusicType(resultSet.getString("musicType"));
        cd.setReleasedDate(resultSet.getDate("releasedDate"));
        return cd;
    }
}

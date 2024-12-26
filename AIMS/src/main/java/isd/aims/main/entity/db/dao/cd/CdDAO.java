package isd.aims.main.entity.db.dao.cd;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.media.CD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CdDAO extends DAO<CD> {
    @Override
    public List<CD> getAll() {
        String query = "SELECT * FROM CD";
        try {
            return findAll(query, new CdMapDbToClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public CD getById(int id) {
        String query = "SELECT * FROM CD WHERE id = ?";
        try {
            return findOne(query, new CdMapDbToClass(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CD add(CD cd) {
        String query = "INSERT INTO CD (id, artist, recordLabel, musicType, releasedDate) VALUES (?, ?, ?, ?, ?)";
        try {
            executeUpdate(query, cd.getId(), cd.getArtist(), cd.getRecordLabel(),
                    cd.getMusicType(), cd.getReleasedDate());
            return cd;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(CD cd) {
        String query = "UPDATE CD SET artist = ?, recordLabel = ?, musicType = ?, releasedDate = ? WHERE id = ?";
        try {
            return executeUpdate(query, cd.getArtist(), cd.getRecordLabel(),
                    cd.getMusicType(), cd.getReleasedDate(), cd.getId()) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM CD WHERE id = ?";
        try {
            return executeUpdate(query, id) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

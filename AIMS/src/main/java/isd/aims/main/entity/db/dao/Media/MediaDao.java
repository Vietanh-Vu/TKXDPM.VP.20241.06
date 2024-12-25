package isd.aims.main.entity.db.dao.Media;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MediaDao extends DAO<Media> {

    @Override
    public List<Media> getAll() {
        String query = "SELECT * from Media";
        try {
            return findAll(query, new MediaMapDbToClass());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Media getById(int id) {
        String query = "SELECT * FROM Media";
        try {
            return findById(query, new MediaMapDbToClass(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Media add(Media media) {
        return null;
    }

    @Override
    public boolean update(Media media) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

package isd.aims.main.entity.db.dao.DVD;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.media.DVD;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.SQLException;

public class DVDDao extends DAO<DVD> {

    @Override
    public List<DVD> getAll() {
        return null;
    }

    @Override
    public DVD getById(int id) {
        String query = "SELECT * FROM "+
                "DVD " +
                "INNER JOIN Media " +
                "ON Media.id = DVD.id " +
                "where Media.id = " + id + ";";
        try {
            return findById(query, new DVDMapDbToClass(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DVD add(DVD dvd) {
        return null;
    }

    @Override
    public boolean update(DVD dvd) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

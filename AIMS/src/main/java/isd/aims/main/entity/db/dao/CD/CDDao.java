package isd.aims.main.entity.db.dao.CD;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.media.CD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CDDao extends DAO<CD> {
    @Override
    public List<CD> getAll() {
        return null;
    }

    @Override
    public CD getById(int id) {
        String query = "SELECT * FROM "+
                "CD " +
                "INNER JOIN Media " +
                "ON Media.id = CD.id " +
                "where Media.id = " + id + ";";
        try {
            return findById(query, new CDMapDbToClass(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CD add(CD cd) {
        return null;
    }

    @Override
    public boolean update(CD cd) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

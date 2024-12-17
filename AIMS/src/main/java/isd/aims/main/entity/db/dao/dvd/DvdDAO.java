package isd.aims.main.entity.db.dao.dvd;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.media.DVD;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DvdDAO extends DAO<DVD> {
    @Override
    public List<DVD> getAll() {
        String query = "SELECT * FROM DVD";
        try {
            return findAll(query, new DvdMapDbToClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public DVD getById(int id) {
        String query = "SELECT * FROM DVD WHERE id = ?";
        try {
            return findOne(query, new DvdMapDbToClass(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DVD add(DVD dvd) {
        String query = "INSERT INTO DVD (id, discType, director, runtime, studio, subtitle, releasedDate, filmType) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            executeUpdate(query, dvd.getId(), dvd.getDiscType(), dvd.getDirector(),
                    dvd.getRuntime(), dvd.getStudio(), dvd.getSubtitle(),
                    dvd.getReleasedDate(), dvd.getFilmType());
            return dvd;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(DVD dvd) {
        String query = "UPDATE DVD SET discType = ?, director = ?, runtime = ?, studio = ?, " +
                "subtitle = ?, releasedDate = ?, filmType = ? WHERE id = ?";
        try {
            return executeUpdate(query, dvd.getDiscType(), dvd.getDirector(),
                    dvd.getRuntime(), dvd.getStudio(), dvd.getSubtitle(),
                    dvd.getReleasedDate(), dvd.getFilmType(), dvd.getId()) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM DVD WHERE id = ?";
        try {
            return executeUpdate(query, id) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

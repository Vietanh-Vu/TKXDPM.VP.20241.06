package isd.aims.main.entity.db.dao;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.media.DVD;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.SQLException;

public class DVDDao implements DAO<DVD> {

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
            Statement stm = SQLiteConnection.getConnection().createStatement();
            ResultSet res = stm.executeQuery(query);
            if(res.next()) {
                // from media table
                String title = "";
                String type = res.getString("type");
                int price = res.getInt("price");
                String category = res.getString("category");
                int quantity = res.getInt("quantity");

                // from DVD table
                String discType = res.getString("discType");
                String director = res.getString("director");
                int runtime = res.getInt("runtime");
                String studio = res.getString("studio");
                String subtitles = res.getString("subtitle");
                String filmType = res.getString("filmType");

                return new DVD(id, title, category, price, quantity, type, discType, director, runtime, studio, subtitles, filmType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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

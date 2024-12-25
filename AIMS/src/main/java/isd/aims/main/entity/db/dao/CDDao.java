package isd.aims.main.entity.db.dao;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.media.CD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CDDao implements DAO<CD> {
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
            Statement stm = SQLiteConnection.getConnection().createStatement();
            ResultSet res = stm.executeQuery(query);
            if(res.next()) {
                // from media table
                String title = "";
                String type = res.getString("type");
                int price = res.getInt("price");
                String category = res.getString("category");
                int quantity = res.getInt("quantity");

                // from CD table
                String artist = res.getString("artist");
                String recordLabel = res.getString("recordLabel");
                String musicType = res.getString("musicType");

                return new CD(id, title, category, price, quantity, type,
                        artist, recordLabel, musicType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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

package isd.aims.main.entity.db.dao;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MediaDao implements DAO<Media> {

    @Override
    public List<Media> getAll() {
        String query = "SELECT * from Media";
        try {
            Statement stm = SQLiteConnection.getConnection().createStatement();
            ResultSet res = stm.executeQuery(query);
            ArrayList medium = new ArrayList<>();
            while(res.next()) {
                Media media = new Media()
                        .setId(res.getInt("id"))
                        .setTitle(res.getString("title"))
                        .setQuantity(res.getInt("quantity"))
                        .setCategory(res.getString("category"))
                        .setMediaURL(res.getString("imageUrl"))
                        .setPrice(res.getInt("price"))
                        .setType(res.getString("type"));
                medium.add(media);
            }
            return medium;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Media getById(int id) {
        String query = "SELECT * FROM Media";
        try {
            Statement stm = SQLiteConnection.getConnection().createStatement();
            ResultSet res = stm.executeQuery(query);
            if(res.next()) {
                return new Media()
                        .setId(res.getInt("id"))
                        .setTitle(res.getString("title"))
                        .setQuantity(res.getInt("quantity"))
                        .setCategory(res.getString("category"))
                        .setMediaURL(res.getString("imageUrl"))
                        .setPrice(res.getInt("price"))
                        .setType(res.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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

package isd.aims.main.entity.db.dao;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.RowMapper;
import isd.aims.main.entity.media.Media;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MediaDAO extends DAO<Media> {

    @Override
    public List<Media> getAll() {
        String query = "SELECT * FROM media";
        try {
            return findAll(query, new MediaRowMapper());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Media getById(int id) {
        String query = "SELECT * FROM media WHERE id = ?";
        try {
            return findOne(query, new MediaRowMapper(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Media add(Media media) {
        String query = "INSERT INTO media (type, category, price, quantity, title, value, imageUrl) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            executeUpdate(query, media.getType(), media.getCategory(), media.getPrice(), media.getQuantity(), media.getTitle(), media.getValue(), media.getImageURL());
            return media;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Media media) {
        String query = "UPDATE media\n" +
                "SET type = ?,\n" +
                "    category = ?,\n" +
                "    price = ?,\n" +
                "    quantity = ?,\n" +
                "    title = ?,\n" +
                "    value = ?,\n" +
                "    imageUrl = ?\n" +
                "WHERE id = ?;";
        try {
            return executeUpdate(query, media.getType(), media.getCategory(), media.getPrice(), media.getQuantity(), media.getTitle(), media.getValue(), media.getImageURL(), media.getId()) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM media WHERE id = ?";
        try {
            return executeUpdate(query, id) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

class MediaRowMapper implements RowMapper<Media> {
    @Override
    public Media mapRow(ResultSet resultSet) throws SQLException {
        Media media = new Media();
        media.setId(resultSet.getInt("id"));
        media.setTitle(resultSet.getString("title"));
        media.setCategory(resultSet.getString("category"));
        media.setValue(resultSet.getInt("value"));
        media.setPrice(resultSet.getInt("price"));
        media.setQuantity(resultSet.getInt("quantity"));
        media.setType(resultSet.getString("type"));
        media.setImageURL(resultSet.getString("imageURL"));
        return media;
    }
}
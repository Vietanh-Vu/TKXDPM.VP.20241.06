package isd.aims.main.entity.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {
    protected Connection connection = SQLiteConnection.getConnection();

//    protected PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        for (int i = 0; i < params.length; i++) {
//            preparedStatement.setObject(i + 1, params[i]);
//        }
//        return preparedStatement;
//    }

    protected T findById(String query, MapDbToClass<T> map, Object... params) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet res = preparedStatement.executeQuery()) {
                 return res.next() ? map.mapResult(res) : null;
        }
    }
    protected List<T> findAll(String query, MapDbToClass<T> map) throws SQLException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                results.add(map.mapResult(resultSet));
            }
        }
        return results;
    }

    /**
     * Lấy tất cả các đối tượng
     * @return Danh sách các đối tượng T
     */
    public abstract List<T> getAll();

    /**
     * Lấy đối tượng theo ID
     * @param id ID của đối tượng cần lấy
     * @return Đối tượng T tìm được
     */
    public abstract T getById(int id);

    /**
     * Thêm mới một đối tượng
     * @param t Đối tượng cần thêm
     * @return Đối tượng đã được thêm
     */
    public abstract T add(T t);

    /**
     * Cập nhật một đối tượng
     * @param t Đối tượng cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public abstract boolean update(T t);

    /**
     * Xóa một đối tượng theo ID
     * @param id ID của đối tượng cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public abstract boolean delete(int id);
}

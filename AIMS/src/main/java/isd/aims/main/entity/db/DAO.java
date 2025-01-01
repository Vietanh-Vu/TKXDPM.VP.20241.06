package isd.aims.main.entity.db;

import isd.aims.main.entity.cart.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {

    protected Connection connection = SQLiteConnection.getConnection();

    protected DAO() {
        // Kết nối đã được thực hiện thông qua SQLiteConnection.getConnection()
    }

    /**
     * Chuẩn bị một PreparedStatement với tham số động
     *
     * @param query  Câu lệnh SQL
     * @param params Các tham số
     * @return PreparedStatement đã được gán tham số
     * @throws SQLException Lỗi nếu câu lệnh không hợp lệ
     */
    protected PreparedStatement prepareStatement(String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        return preparedStatement;
    }

    /**
     * Lấy một đối tượng từ ResultSet
     *
     * @param query  Câu lệnh SQL
     * @param mapper Bộ ánh xạ (RowMapper)
     * @param params Các tham số
     * @return Đối tượng được ánh xạ, hoặc null nếu không tìm thấy
     * @throws SQLException Lỗi khi thực thi câu lệnh
     */
    protected T findOne(String query, MapDbToClass<T> mapper, Object... params) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultSet.next() ? mapper.mapRow(resultSet) : null;
        }
    }

    /**
     * Lấy danh sách đối tượng từ ResultSet
     *
     * @param query  Câu lệnh SQL
     * @param mapper Bộ ánh xạ (RowMapper)
     * @param params Các tham số
     * @return Danh sách các đối tượng được ánh xạ
     * @throws SQLException Lỗi khi thực thi câu lệnh
     */
    protected List<T> findAll(String query, MapDbToClass<T> mapper, Object... params) throws SQLException {
        List<T> results = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepareStatement(query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                results.add(mapper.mapRow(resultSet));
            }
        }
        return results;
    }

    /**
     * Thực hiện câu lệnh INSERT, UPDATE, DELETE
     *
     * @param query  Câu lệnh SQL
     * @param params Các tham số
     * @return Số hàng bị ảnh hưởng
     * @throws SQLException Lỗi khi thực thi câu lệnh
     */
    protected int executeUpdate(String query, Object... params) throws SQLException {
        try (PreparedStatement preparedStatement = prepareStatement(query, params)) {
            return preparedStatement.executeUpdate();
        }
    }

    /**
     * Các phương thức trừu tượng cho các lớp con
     */
    public abstract List<T> getAll();

    public abstract T getById(int id);

    public abstract T add(T t);

    public abstract boolean update(T t);

    public abstract boolean delete(int id);
}

package isd.aims.main.entity.db;

import java.util.List;

public interface DAO<T> {
    /**
     * Lấy tất cả các đối tượng
     * @return Danh sách các đối tượng T
     */
    List<T> getAll();

    /**
     * Lấy đối tượng theo ID
     * @param id ID của đối tượng cần lấy
     * @return Đối tượng T tìm được
     */
    T getById(int id);

    /**
     * Thêm mới một đối tượng
     * @param t Đối tượng cần thêm
     * @return Đối tượng đã được thêm
     */
    T add(T t);

    /**
     * Cập nhật một đối tượng
     * @param t Đối tượng cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    boolean update(T t);

    /**
     * Xóa một đối tượng theo ID
     * @param id ID của đối tượng cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    boolean delete(int id);
}

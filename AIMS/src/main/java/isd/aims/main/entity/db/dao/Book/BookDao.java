package isd.aims.main.entity.db.dao.Book;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.media.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookDao extends DAO<Book> {

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(int id) {
        String query = "SELECT * FROM "+
                "Book " +
                "INNER JOIN Media " +
                "ON Media.id = Book.id " +
                "where Media.id = " + id + ";";
        try {
            return findById(query, new BookMapDbToClass(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book add(Book book) {
        return null;
    }

    @Override
    public boolean update(Book book) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}

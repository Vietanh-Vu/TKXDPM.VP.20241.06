package isd.aims.main.entity.db.dao;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.db.SQLiteConnection;
import isd.aims.main.entity.media.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookDao implements DAO<Book> {

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
            Statement stm = SQLiteConnection.getConnection().createStatement();
            ResultSet res = stm.executeQuery(query);
            if(res.next()) {
                // from Media table
                String title = "";
                String type = res.getString("type");
                int price = res.getInt("price");
                String category = res.getString("category");
                int quantity = res.getInt("quantity");

                // from Book table
                String author = res.getString("author");
                String coverType = res.getString("coverType");
                String publisher = res.getString("publisher");
                int numOfPages = res.getInt("numOfPages");
                String language = res.getString("language");
                String bookCategory = res.getString("bookCategory");

                return new Book(id, title, category, price, quantity, type,
                        author, coverType, publisher, numOfPages, language, bookCategory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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

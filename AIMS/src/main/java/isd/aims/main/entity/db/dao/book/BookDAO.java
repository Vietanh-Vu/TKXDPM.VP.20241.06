package isd.aims.main.entity.db.dao.book;

import isd.aims.main.entity.db.DAO;
import isd.aims.main.entity.media.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DAO<Book> {

    @Override
    public List<Book> getAll() {
        String query = "SELECT * FROM Book";
        try {
            return findAll(query, new BookMapDbToClass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Book getById(int id) {
        String query = "SELECT * FROM Book WHERE id = ?";
        try {
            return findOne(query, new BookMapDbToClass(), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book add(Book book) {
        String query = "INSERT INTO Book (id, author, coverType, publisher, publishDate, numOfPages, language, bookCategory) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            executeUpdate(query, book.getId(), book.getAuthor(), book.getCoverType(),
                    book.getPublisher(), book.getPublishDate(), book.getNumOfPages(),
                    book.getLanguage(), book.getBookCategory());
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Book book) {
        String query = "UPDATE Book\n" +
                "SET author = ?,\n" +
                "    coverType = ?,\n" +
                "    publisher = ?,\n" +
                "    publishDate = ?,\n" +
                "    numOfPages = ?,\n" +
                "    language = ?,\n" +
                "    bookCategory = ?\n" +
                "WHERE id = ?;";
        try {
            return executeUpdate(query, book.getAuthor(), book.getCoverType(),
                    book.getPublisher(), book.getPublishDate(), book.getNumOfPages(),
                    book.getLanguage(), book.getBookCategory(), book.getId()) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Book WHERE id = ?";
        try {
            return executeUpdate(query, id) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
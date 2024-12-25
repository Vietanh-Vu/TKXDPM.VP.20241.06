package isd.aims.main.entity.db.dao.book;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.media.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapDbToClass implements MapDbToClass<Book> {

    @Override
    public Book mapRow(ResultSet resultSet) throws SQLException {
       Book book = new Book();

       // Map các trường của bảng Book
       book.setId(resultSet.getInt("id"));
       book.setAuthor(resultSet.getString("author"));
       book.setCoverType(resultSet.getString("coverType"));
       book.setPublisher(resultSet.getString("publisher"));
//       book.setPublishDate(resultSet.getTimestamp("publishDate"));
       book.setNumOfPages(resultSet.getInt("numOfPages"));
       book.setLanguage(resultSet.getString("language"));
       book.setBookCategory(resultSet.getString("bookCategory"));

       return book;
    }
}

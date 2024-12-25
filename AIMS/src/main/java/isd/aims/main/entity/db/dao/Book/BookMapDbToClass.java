package isd.aims.main.entity.db.dao.Book;

import isd.aims.main.entity.db.MapDbToClass;
import isd.aims.main.entity.media.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapDbToClass implements MapDbToClass<Book> {

    @Override
    public Book mapResult(ResultSet res) throws SQLException {
       Book book = new Book();
       book.setId(res.getInt("id"));
       book.setTitle("");
       book.setCategory(res.getString("category"));
       book.setPrice(res.getInt("price"));
       book.setQuantity(res.getInt("quantity"));
       book.setType(res.getString("type"));
       book.setAuthor(res.getString("author"));
       book.setCoverType(res.getString("coverType"));
       book.setPublisher(res.getString("publisher"));
       book.setNumOfPages(res.getInt("numOfPages"));
       book.setLanguage(res.getString("language"));
       book.setCategory(res.getString("bookCategory"));

       return book;
    }
}

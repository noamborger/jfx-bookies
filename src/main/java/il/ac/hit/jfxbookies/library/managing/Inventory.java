package il.ac.hit.jfxbookies.library.managing;


import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class Inventory {
    public static final String NULL_ERROR_FOR_FIELDS = "title, author, genre and location cannot be null";

    public Inventory() {

    }

    public long getBooksCount() throws SQLException {
        return JdbcDriverSetup.getDao(Book.class).countOf();
    }

    public void add(Book book) throws SQLException {
        JdbcDriverSetup.getDao(Book.class).create(book);
    }

    public void remove(int sku) throws SQLException {
        JdbcDriverSetup.<Book, Integer>getDao(Book.class).deleteById(sku);

    }

    public boolean hasBook(Book book) throws SQLException {

        return JdbcDriverSetup.getDao(Book.class).queryBuilder()
                .where()
                .eq("id", book.getSku())
                .countOf() > 0;
    }

    public List<Book> getFilteredBooks(String keyword) throws SQLException {
        String likeKeyword = "%" + keyword + "%";
        return JdbcDriverSetup.getDao(Book.class)
                .queryBuilder()
                .where()
                .like("id", likeKeyword)
                .or()
                .like("title", likeKeyword)
                .or()
                .like("author", likeKeyword)
                .or()
                .like("genre", likeKeyword)
                .or()
                .like("location", likeKeyword)
                .query();
    }

    public List<Book> getAllBooks() throws SQLException {
        return JdbcDriverSetup.getDao(Book.class).queryForAll();
    }
}

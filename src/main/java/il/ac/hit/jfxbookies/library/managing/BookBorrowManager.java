package il.ac.hit.jfxbookies.library.managing;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.person.Client;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
public class BookBorrowManager {
    public Client getActiveClientForBook(int id) throws SQLException {
        BorrowBook borrowBook = JdbcDriverSetup
                .getDao(BorrowBook.class)
                .queryBuilder()
                .where()
                .eq("book_id", id)
                .and()
                .eq("active", true)
                .queryForFirst();
        if (borrowBook != null) {
            return borrowBook.getClient();
        } else {
            return null;
        }
    }

    public BorrowBook borrowBookByClient(Book book, Client client) throws SQLException {
        var borrow = BorrowBook
                .builder()
                .book(book)
                .client(client)
                .active(true)
                .build();
        JdbcDriverSetup.getDao(BorrowBook.class).create(borrow);
        return borrow;
    }

    public void deactivateBookBorrow(BorrowBook borrowBook) throws SQLException {
        borrowBook.setActive(false);
        JdbcDriverSetup.getDao(BorrowBook.class)
                .update(borrowBook);
    }

    public void showBooksBorrowedByClient (String id){

    }
}

package il.ac.hit.jfxbookies.library.managing;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.person.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

//taking data of borrowed book from DB
@Service
@Slf4j
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

    //taking data of how much borrowed book we have from DB
    public long getActiveBookBorrowsSize() throws SQLException {
        return JdbcDriverSetup.getDao(BorrowBook.class)
                .queryBuilder()
                .where()
                .eq("active", true)
                .countOf();
    }

    //build the data of borrow book
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

    //return book
    public void deactivateBookBorrow(BorrowBook borrowBook) throws SQLException {
        borrowBook.setActive(false);
        JdbcDriverSetup.getDao(BorrowBook.class)
                .update(borrowBook);
    }

    //delete from data
    public void deleteBookBorrowByClient(int clientID) throws SQLException{
        List<BorrowBook> borrowBookList = JdbcDriverSetup.getDao(BorrowBook.class).queryBuilder()
                        .where()
                        .eq("client_id", clientID).query();
        JdbcDriverSetup.getDao(BorrowBook.class).delete(borrowBookList);
    }


    
}

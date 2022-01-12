package il.ac.hit.jfxbookies.library;


import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BookBorrowManager;
import il.ac.hit.jfxbookies.library.managing.Inventory;

import java.util.List;

public class Library {
    private Inventory availableBooks;
    private BookBorrowManager rentedBooks;

    public List<Book> searchByTitle(String title){

        return null;
    }
    public List<Book> searchByAuthor(String author){

        return null;
    }
    public List<Book> searchBySKU(String sKU){

        return null;
    }
    public boolean isBookAvailable(Book book){

        return false;
    }



}

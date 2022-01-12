package il.ac.hit.jfxbookies.library.managing;



import il.ac.hit.jfxbookies.library.book.Book;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public static final String NULL_ERROR_FOR_FIELDS = "title, author, genre and location cannot be null";
    private Map<Integer, Book> books;
    private static int numberOfBooks;

    public Inventory() {
        this.books = new HashMap<>();
    }

    public int getBooksCount() {
        return books.size();
    }

    public void add(Book book) {
        if (book.getTitle() == null || book.getAuthor() == null || book.getGenre() == null || book.getLocation() == null) { // if manager
            throw new NullPointerException(NULL_ERROR_FOR_FIELDS);
        }

        books.put(book.getSku(), book);
        //numberOfBooks++;
    }

    public void remove(Book book) {
        books.remove(book);

    }

    public boolean hasBook(Book book) {

        return books.containsKey(book.getSku());
    }

    public void showInventory() {

    }
}

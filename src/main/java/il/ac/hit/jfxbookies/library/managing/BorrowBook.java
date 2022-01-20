package il.ac.hit.jfxbookies.library.managing;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.person.Client;
import lombok.*;

import java.util.Calendar;

@DatabaseTable
@NoArgsConstructor
@Data
public class BorrowBook {
    @DatabaseField(foreign = true, columnName = "client_id", foreignColumnName = "id")
    private Client client;
    @DatabaseField
    private long date;
    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    @Setter(AccessLevel.NONE)
    private int id;
    @DatabaseField(foreign = true, columnName = "book_id", foreignColumnName = "id")
    private Book book;
    @DatabaseField(dataType = DataType.BOOLEAN_INTEGER)
    private boolean active;

    @Builder
    public BorrowBook(Client client, Book book, boolean active) {
        this.client = client;
        this.id = id;
        this.book = book;
        this.active = active;
        this.date = System.currentTimeMillis();
    }


    public void setClient(Client client) {
        this.client = client;
    }

    //get functions
    public Calendar getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar;
    }

    public Client getClient() {
        return client;
    }

    //other functions
    public void lendBook(Book book, Client client) {



    }

    public void returnBook(Book book, Client client) {

    }

}

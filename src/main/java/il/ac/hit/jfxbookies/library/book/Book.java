package il.ac.hit.jfxbookies.library.book;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import lombok.*;

import java.sql.SQLException;

@DatabaseTable(tableName = "books")
@NoArgsConstructor
@Data
public class Book {
    @DatabaseField(columnName = "id",generatedId = true, allowGeneratedIdInsert = true)
    @Setter(AccessLevel.NONE)
    private int sku;
    @DatabaseField
    private String title;
    @DatabaseField
    private String author;
    @DatabaseField
    private String genre;
    @DatabaseField
    private String location;

    //Constructors
    @Builder
    public Book(String title, String author, String genre, String location){
        this.title=title;
        this.author=author;
        this.genre=genre;
        this.location=location;

    }

    // Set functions
    //Other functions
    public Book showBookInfo(String sku){
        try {
            return JdbcDriverSetup.getDao(Book.class).queryForId(sku);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;


    }
}


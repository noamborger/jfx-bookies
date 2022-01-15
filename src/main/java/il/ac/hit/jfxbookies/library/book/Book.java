package il.ac.hit.jfxbookies.library.book;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import lombok.*;

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
    public void showBookInfo(String sku){
        //go to inventory
        //go to data


    }
}


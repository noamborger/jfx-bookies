package il.ac.hit.jfxbookies.test;

import javafx.embed.swing.JFXPanel;
import de.saxsys.javafx.test.JfxRunner;
import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
//@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        JdbcConfiguration.class, Book.class, Inventory.class
})
public class BookTest {
    @Autowired
    private Inventory inventory;

    @BeforeClass
    public static void bootstrapJavaFx(){
        // implicitly initializes JavaFX Subsystem
        // see http://stackoverflow.com/questions/14025718/javafx-toolkit-not-initialized-when-trying-to-play-an-mp3-file-through-mediap
        new JFXPanel();
    }

    @Test
    public void addBook() throws Exception
    {
        Book book = new Book();
        book.setAuthor("J.K Rolling");
        book.setGenre("Fantasy");
        book.setLocation("G6");
        book.setTitle("Harry potter");
        inventory.add(book);

        Assert.assertEquals(JdbcDriverSetup.getDao(Book.class).queryBuilder()
                .where()
                .eq("title", "Harry potter")
                .queryForFirst(), book);

    }
    @Test
    public void removeBook(){



    }

}

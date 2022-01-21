package il.ac.hit.jfxbookies.test;

import de.saxsys.javafx.test.JfxRunner;
import il.ac.hit.jfxbookies.library.managing.BookBorrowManager;
import il.ac.hit.jfxbookies.person.managing.ClientManager;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@RunWith(JfxRunner.class)
@ContextConfiguration(classes = {
        JdbcConfiguration.class, BookBorrowManager.class
})
public class BookBorrowManagerTest {
}

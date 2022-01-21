package il.ac.hit.jfxbookies.test;

import de.saxsys.javafx.test.JfxRunner;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.person.User;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@RunWith(JfxRunner.class)
@ContextConfiguration(classes = {
        JdbcConfiguration.class, User.class
})
public class UserTest {
}

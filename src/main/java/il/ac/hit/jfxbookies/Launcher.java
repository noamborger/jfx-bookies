package il.ac.hit.jfxbookies;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import il.ac.hit.jfxbookies.person.Client;
import javafx.application.Application;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@SpringBootApplication(scanBasePackages = "il.ac.hit.jfxbookies")
public class Launcher {

    public static void main(String[] args) {
        Application.launch(HelloApplication.class, args);
    }

    @Bean
    public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        // Would also work with javafx-weaver-core only:
        // return new FxWeaver(applicationContext::getBean, applicationContext::close);
        return new SpringFxWeaver(applicationContext); //(2)
    }

    @Bean
    public Dao<Client, Integer> getClientDao(ConnectionSource connectionSource) throws SQLException {
        return DaoManager.createDao(connectionSource, Client.class);
    }

    @Bean
    public Dao<Book, Integer> getBookDao(ConnectionSource connectionSource) throws SQLException {
        return DaoManager.createDao(connectionSource, Book.class);
    }

    @Bean
    public Dao<BorrowBook, Integer> getBorrowBookDao(ConnectionSource connectionSource) throws SQLException {
        return DaoManager.createDao(connectionSource, BorrowBook.class);
    }

    @Bean
    public ConnectionSource getConnection() throws SQLException {
        return new JdbcConnectionSource("jdbc:sqlite:src/main/resources/bookies.db");
    }
}

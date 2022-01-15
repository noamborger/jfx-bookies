package il.ac.hit.jfxbookies;

import com.j256.ormlite.table.TableUtils;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import il.ac.hit.jfxbookies.person.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            TableUtils.createTableIfNotExists(JdbcDriverSetup.getConnection(), Client.class);
            TableUtils.createTableIfNotExists(JdbcDriverSetup.getConnection(), Book.class);
            TableUtils.createTableIfNotExists(JdbcDriverSetup.getConnection(), BorrowBook.class);
            JdbcDriverSetup.createDao(Book.class);
            JdbcDriverSetup.createDao(Client.class);
            JdbcDriverSetup.createDao(BorrowBook.class);
        } catch (SQLException e) {
            throw new IOException("could not create tables", e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
package il.ac.hit.jfxbookies;

import com.j256.ormlite.dao.DaoManager;
import il.ac.hit.jfxbookies.library.book.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        try {
            DaoManager.createDao(JdbcDriverSetup.getConnection(), Book.class).create(Book.builder()
                    .author("Noam")
                    .genre("Comedy")
                    .location("on my desk")
                    .title("Lior")
                    .build());
        } catch (SQLException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }
}
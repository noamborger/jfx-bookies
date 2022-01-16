package il.ac.hit.jfxbookies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.table.TableUtils;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.person.User;
import il.ac.hit.jfxbookies.startup.Setup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
            JdbcDriverSetup.createDao(User.class);
        } catch (SQLException e) {
            throw new IOException("could not create tables", e);
        }
        try {
            boolean tableAlreadyCreated = true;
            try {
                TableUtils.createTable(JdbcDriverSetup.getConnection(), User.class);
                tableAlreadyCreated = false;
            } catch (SQLException e) {
                tableAlreadyCreated = JdbcDriverSetup.getLookup(User.class).queryForFirst() != null;
             }
            if (!tableAlreadyCreated) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Setup JSON File", "*.json"));
                fileChooser.setInitialDirectory(new File("."));
                File file = fileChooser.showOpenDialog(stage);
                ObjectMapper objectMapper = new ObjectMapper();
                setupUserTable(file, objectMapper);
            }
        } catch (Exception e) {
            try {
                TableUtils.dropTable(JdbcDriverSetup.getConnection(), User.class, true);
            } catch (SQLException ignore) {
            }
            throw new RuntimeException(e);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void setupUserTable(File file, ObjectMapper objectMapper) throws IOException, SQLException {
        Setup setup = objectMapper.readValue(file, Setup.class);
        JdbcDriverSetup
                .getLookup(User.class)
                .create(User.buildUser(setup.getManagerUser(), setup.getManagerPassword()));
        JdbcDriverSetup
                .getLookup(User.class)
                .create(User.buildUser(setup.getLibrarianUser(), setup.getLibrarianPassword()));
        JdbcDriverSetup
                .getLookup(User.class)
                .create(User.buildUser(setup.getLibrarian2Password(), setup.getLibrarian2Password()));
    }

    public static void main(String[] args) {
        launch();
    }
}
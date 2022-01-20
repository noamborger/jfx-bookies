package il.ac.hit.jfxbookies;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.table.TableUtils;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.person.User;
import il.ac.hit.jfxbookies.startup.Setup;
import il.ac.hit.jfxbookies.view.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


//@SpringBootApplication(scanBasePackages = "il.ac.hit.jfxbookies.*")
public class HelloApplication extends Application {
    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.springContext = new SpringApplicationBuilder()
                .sources(Launcher.class)
                .run(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        springContext.close();
    }

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
            /*
            if the table was not created, it will change the tableAlreadyCreated to false
            which then it will enter the if and will create the table.
            this makes sure that the data of the user's username and password will be saved.
            the application will open a file explorer the user will need to go to the setup.json file.


            */
            try {
                TableUtils.createTable(JdbcDriverSetup.getConnection(), User.class);
                tableAlreadyCreated = false;
            } catch (SQLException e) {
                tableAlreadyCreated = JdbcDriverSetup.getDao(User.class).queryForFirst() != null;
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
        FxWeaver fxWeaver = springContext.getBean(FxWeaver.class);
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginPage.fxml"));*/
        Parent root = fxWeaver.loadView(LoginController.class);
        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    //puts the users in the table
    private void setupUserTable(File file, ObjectMapper objectMapper) throws IOException, SQLException {
        Setup setup = objectMapper.readValue(file, Setup.class);
        JdbcDriverSetup
                .getDao(User.class)
                .create(User.buildUser(setup.getManagerUser(), setup.getManagerPassword(), User.UserType.MANAGER));
        JdbcDriverSetup
                .getDao(User.class)
                .create(User.buildUser(setup.getLibrarianUser(), setup.getLibrarianPassword(), User.UserType.LIBRARIAN));
        JdbcDriverSetup
                .getDao(User.class)
                .create(User.buildUser(setup.getLibrarian2User(), setup.getLibrarian2Password(), User.UserType.LIBRARIAN));
    }


    public static void main(String[] args) {
        launch();
    }
}
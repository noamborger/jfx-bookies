package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.person.User;
import il.ac.hit.jfxbookies.session.SessionContext;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LoginController {
    @FXML
    private Label responseText;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;
/*
    public void initialize() {
        // if user.getUserType() != MANAGER

        loginButton.setVisible(false);
    }
*/

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        try { //checks if the username and password that match what we have in the DB
            //using SQL question
            List<User> result = JdbcDriverSetup.getLookup(User.class).queryBuilder()
                    .where()
                    .eq("username", username.getText())
                    .and()
                    .eq("password", DigestUtils.sha512Hex(password.getText()))
                    .query();
            if (result.size() == 0) { //if we did not get a result then the username or password are incorrect
                responseText.setText("Username/password is incorrect");
            } else { //if we did get a result then it will go to the next page
                SessionContext.getInstance().setCurrentUser(result.get(0));
                onSuccessfulLogin(event);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // todo: workout how to handle exception with DB
        }
        //welcomeText.setText("Welcome to JavaFX Application!");
        /*
        responseText.setText("Username/Password incorrect");

        try {
            Calendar id = JdbcDriverSetup.getLookup(BorrowBook.class).queryForEq("id", 1).get(0).getDate();

            System.out.println(id.get(Calendar.DAY_OF_MONTH) + "/" + (id.get(Calendar.MONTH) + 1) + "/" + id.get(Calendar.YEAR));

            Book book = JdbcDriverSetup.getLookup(Book.class).queryForEq("title", "Lior").get(0);
            String id = "3333333";
            Client client = Client.builder()
                    .id(id)
                    .address("safsa ")
                    .email("a@a.c")
                    .name("lior")
                    .phone("22222")
                    .build();
            JdbcDriverSetup.getCreate(Client.class).create(client);
            BorrowBook borrowbook = BorrowBook
                    .builder()
                    .client(client)
                    .active(true)
                    .book(book)
                    .build();
            JdbcDriverSetup.getCreate(BorrowBook.class).create(borrowbook);
             }catch (SQLException e) {
            System.err.println("error");
            e.printStackTrace();
            */

    }

    private void onSuccessfulLogin(ActionEvent event) {
        try {
    /*Parent root = FXMLLoader.load(getClass().getResource("booksListPage.fxml"));
    Stage window = (Stage) Login.getScene().getWindow();
    window.setScene(new Scene(root));*/
            //change the scene to the bookListPage
            Parent root = FXMLLoader.load(BooksListController.class.getResource("booksListPage.fxml"));
            Scene booksListScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(booksListScene);
            window.show();


        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }


}
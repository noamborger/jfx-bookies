package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;

public class LoginController {
    @FXML
    private Label responseText;
    @FXML
    Button Login;
    @FXML
    protected void onHelloButtonClick(ActionEvent event) {
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
        try{
            /*Parent root = FXMLLoader.load(getClass().getResource("booksListPage.fxml"));
            Stage window = (Stage) Login.getScene().getWindow();
            window.setScene(new Scene(root));*/
            Parent root = FXMLLoader.load(getClass().getResource("booksListPage.fxml"));
            Scene booksListScene = new Scene(root);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(booksListScene);
            window.show();


        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }


    }


}
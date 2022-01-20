package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;

public class AddBookController {

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField locationTextField;


    public void onBackButtonClick(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(BooksListController.class.getResource("booksListPage.fxml"));
            Scene bookListScene= new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(bookListScene);
            window.show();

        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }

    public void onAddBookButtonClick(ActionEvent event) {
        Inventory inventory = new Inventory();
        if(titleTextField.getText().isBlank() | authorTextField.getText().isBlank() | genreTextField.getText().isBlank() | locationTextField.getText().isBlank()){

        }
        else {
            Book book = new Book(titleTextField.getText(), authorTextField.getText(), genreTextField.getText(), locationTextField.getText());
            //JdbcDriverSetup.getCreate(Book.class).create(book); //Inventory.add(book);
            try {
                inventory.add(book);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

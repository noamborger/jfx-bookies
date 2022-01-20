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
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.io.IOException;

@Component
@FxmlView("addBookPage.fxml")
public class AddBookController {

    @FXML
    private TextField titleTextField;
    @FXML
    private TextField authorTextField;
    @FXML
    private TextField genreTextField;
    @FXML
    private TextField locationTextField;

    @Autowired
    private FxWeaver fxWeaver;

    @Autowired
    private Inventory inventory;


    public void onBackButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(BooksListController.class);
        Scene bookListScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(bookListScene);
        window.show();
    }

    public void onAddBookButtonClick(ActionEvent event) {
        if (titleTextField.getText().isBlank() || authorTextField.getText().isBlank() || genreTextField.getText().isBlank() || locationTextField.getText().isBlank()) {

        } else {
            Book book = new Book(titleTextField.getText(), authorTextField.getText(), genreTextField.getText(), locationTextField.getText());

            //JdbcDriverSetup.getCreate(Book.class).create(book); //Inventory.add(book);
        }

    }
}

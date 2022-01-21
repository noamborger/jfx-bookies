package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


import java.io.IOException;
import java.sql.SQLException;

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
    @FXML
    private Label errorLabel;


    @Autowired
    private Inventory inventory;

    //Move between pages
    public void onBackButtonClick(ActionEvent event) {
        GraphicsUtils.openWindow(event, BooksListController.class);
    }

    public void onAddBookButtonClick(ActionEvent event) {
        if(titleTextField.getText().isBlank() || authorTextField.getText().isBlank() || genreTextField.getText().isBlank() || locationTextField.getText().isBlank()){
            errorLabel.setText("Please check that you filled the information correctly");
            //if the field empty
        } else {
            Book book = new Book(titleTextField.getText(), authorTextField.getText(), genreTextField.getText(), locationTextField.getText());
            //add the information book to data

            try {
                inventory.add(book);
                onBackButtonClick(event); //Move between pages
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

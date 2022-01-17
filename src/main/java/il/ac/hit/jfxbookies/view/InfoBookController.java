package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.person.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import static il.ac.hit.jfxbookies.session.SessionContext.getInstance;


public class InfoBookController {

    private Button removeBookButton;

    public void initialize(){
        if (User.UserType.LIBRARIAN == getInstance().getCurrentUser().getUserType()) {
            removeBookButton.setVisible(false);
        }
        else{
            removeBookButton.setVisible(true);
        }
    }

    public void onRemoveBookButtonClick(ActionEvent event) {
    }

    public void onBackButtonClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(BooksListController.class.getResource("booksListPage.fxml"));
            Scene booksListScene= new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(booksListScene);
            window.show();

        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }
}

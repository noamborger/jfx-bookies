package il.ac.hit.jfxbookies.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddBookController {
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
    }
}

package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.person.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static il.ac.hit.jfxbookies.session.SessionContext.getInstance;


@Component
@FxmlView("infoBookPage.fxml")
public class InfoBookController {

    private Button removeBookButton;

    @Autowired
    private FxWeaver fxWeaver;

    public void initialize() {
        if (User.UserType.LIBRARIAN == getInstance().getCurrentUser().getUserType()) {
            removeBookButton.setVisible(false);
        } else {
            removeBookButton.setVisible(true);
        }
    }

    public void onRemoveBookButtonClick(ActionEvent event) {
    }

    public void onBackButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(BooksListController.class);
        Scene booksListScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(booksListScene);
        window.show();
    }
}

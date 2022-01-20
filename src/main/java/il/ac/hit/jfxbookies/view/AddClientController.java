package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

@Component
@FxmlView("addClientPage.fxml")
public class AddClientController {

    private Button backButton;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;

    @Autowired
    private FxWeaver fxWeaver;

    public void onBackButtonClick(ActionEvent event) {
        GraphicsUtils.openWindow(event, ClientListController.class);
    }


    public void onAddClientButtonClick(ActionEvent event) {

        if (nameTextField.getText().isBlank() | emailTextField.getText().isBlank() | phoneTextField.getText().isBlank() | addressTextField.getText().isBlank()) {

        } else {
            Client client = Client
                    .builder()
                    .name(nameTextField.getText())
                    .email(emailTextField.getText())
                    .phone(phoneTextField.getText())
                    .address(addressTextField.getText())
                    .build();
            client.addClient();
        }
        onBackButtonClick(event);


    }
}

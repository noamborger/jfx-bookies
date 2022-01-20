package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import il.ac.hit.jfxbookies.person.Client;
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
        Parent root = fxWeaver.loadView(ClientListController.class);

        Scene clientListScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(clientListScene);
        window.show();
    }


    public void onAddClientButtonClick(ActionEvent event) {

        if (nameTextField.getText().isBlank() | emailTextField.getText().isBlank() | phoneTextField.getText().isBlank() | addressTextField.getText().isBlank()) {

        } else {
            Client client = Client
                    .builder()
                    .id("")
                    .name(nameTextField.getText())
                    .email(emailTextField.getText())
                    .phone(phoneTextField.getText())
                    .address(addressTextField.getText())
                    .build();
        }


    }
}

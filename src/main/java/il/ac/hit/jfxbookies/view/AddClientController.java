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

import java.io.IOException;

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

    public void onBackButtonClick(ActionEvent event) {


        try {
            Parent root = FXMLLoader.load(ClientListController.class.getResource("clientListPage.fxml"));
            Scene clientListScene= new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(clientListScene);
            window.show();

        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }


    public void onAddClientButtonClick(ActionEvent event) {

        if(nameTextField.getText().isBlank() | emailTextField.getText().isBlank() | phoneTextField.getText().isBlank() | addressTextField.getText().isBlank()){

        }
        else {
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

package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.person.managing.ClientManager;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.regex.*;

@Component
@FxmlView("addClientPage.fxml")
public class AddClientController {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private Label responseTextFalseInformation;

    @Autowired
    private FxWeaver fxWeaver;

    @Autowired
    private ClientManager clientManager;
    //Move between pages
    public void onBackButtonClick(ActionEvent event) {
        GraphicsUtils.openWindow(event, ClientListController.class);
    }


    public void onAddClientButtonClick(ActionEvent event) {

        Pattern phonePtrn = Pattern.compile("(05)[0-9]");  //phone number starting with 05
        //if the field empty or phone number starting with 05
        if (nameTextField.getText().isBlank() || emailTextField.getText().isBlank() || phoneTextField.getText().isBlank() ||
                addressTextField.getText().isBlank() || phoneTextField.getText().matches(phonePtrn.pattern())) {
            responseTextFalseInformation.setText("Please check that you filled the information correctly");

        } else {
            Client client = Client
                    .builder()
                    .name(nameTextField.getText())
                    .email(emailTextField.getText())
                    .phone(phoneTextField.getText())
                    .address(addressTextField.getText())
                    .build();
            clientManager.addClient(client);
            onBackButtonClick(event); //Move between pages
        }


    }
}

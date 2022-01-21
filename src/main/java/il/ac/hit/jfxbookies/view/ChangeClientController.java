package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.library.managing.BookBorrowManager;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.person.managing.ClientManager;
import il.ac.hit.jfxbookies.session.SessionContext;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@FxmlView ("changeClientPage.fxml")
public class ChangeClientController {
    @FXML
    private Label idLabel;
    @FXML
    private TextField changeNameTextField;
    @FXML
    private TextField changeEmailTextField;
    @FXML
    private TextField changeAddressTextField;
    @FXML
    private TextField changePhoneTextField;

    @Autowired
    private ClientManager clientManager;
    @Autowired
    private BookBorrowManager bookBorrow;

    public void initialize(){
        Client client= SessionContext.getInstance().getCurrentClient();
        idLabel.setText(String.valueOf(client.getId()));
        changeNameTextField.setText(client.getName());
        changeEmailTextField.setText(client.getEmail());
        changeAddressTextField.setText(client.getAddress());
        changePhoneTextField.setText(client.getPhone());
    }





    public void onBackButtonClick(ActionEvent event) {
        GraphicsUtils.openWindow(event, ClientListController.class);
    }

    public void onRemoveClientButtonClick(ActionEvent event) {

        try {
            clientManager.deleteClient(String.valueOf(Integer.parseInt(idLabel.getText())));
            bookBorrow.deleteBookBorrowByClient(Integer.parseInt(idLabel.getText()));
            GraphicsUtils.openWindow(event, ClientListController.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void onSaveChangesButtonClick (ActionEvent event){

        Client client= SessionContext.getInstance().getCurrentClient();
        client.setName(changeNameTextField.getText());
        client.setAddress(changeAddressTextField.getText());
        client.setEmail(changeEmailTextField.getText());
        client.setPhone(changePhoneTextField.getText());
        try {
            clientManager.updateClient(client);
            GraphicsUtils.openWindow(event, ClientListController.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}




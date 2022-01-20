package il.ac.hit.jfxbookies.view;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.session.SessionContext;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.sql.SQLException;

@Component
@FxmlView ("changeClientPage.fxml")

public class ChangeClientController {
    @FXML
    private Label idLabel;

    public void initialize(){
        Client client= SessionContext.getInstance().getCurrentClient();
        idLabel.setText(String.valueOf(client.getId()));
    }





    public void onBackButtonClick(ActionEvent event) {
        GraphicsUtils.openWindow(event, ClientListController.class);
    }

    public void onRemoveClientButtonClick(ActionEvent event) {
        Client client = new Client();

        /*try {
            client.deleteClient(Integer.parseInt(idLabel.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        GraphicsUtils.openWindow(event, ClientListController.class);
    }


    public void onSaveChangesButtonClick (ActionEvent event){


        GraphicsUtils.openWindow(event, ClientListController.class);
    }


}




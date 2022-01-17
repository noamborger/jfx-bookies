package il.ac.hit.jfxbookies.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddClientController {

    private Button backButton;

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
    }
}

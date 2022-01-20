package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.person.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class ClientListController {

    @FXML
    private TextField searchClientField;

    @FXML
    private TableView<Client> dataTable;
    @FXML
    private TableColumn<Client, Integer> idCTableColumn;
    @FXML
    private TableColumn<Client, String> nameCTableColumn;
    @FXML
    private TableColumn<Client, String> emailCTableColumn;
    @FXML
    private TableColumn<Client, String> addressCTableColumn;
    @FXML
    private TableColumn<Client, String> phoneCTableColumn;

    private final ObservableList<Client> clientObservableList =FXCollections.observableArrayList();

    public void initialize(){
        try{
            System.out.println("test....");
            List<Client> c= JdbcDriverSetup.getDao(Client.class).queryForAll();
            idCTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameCTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            emailCTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            addressCTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            phoneCTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            clientObservableList.addAll(c);
            dataTable.setItems(clientObservableList);

            FilteredList<Client> filteredData= new FilteredList<>(clientObservableList, client -> true);

            searchClientField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(client -> {
                    if (newValue.isBlank() || newValue.isEmpty() || newValue==null){
                        return true;
                    }
                    String searchKeyWord = newValue.toLowerCase();
                    return client.getName().toLowerCase().contains(searchKeyWord) ||
                            client.getEmail().toLowerCase().contains(searchKeyWord) ||
                            client.getPhone().toLowerCase().contains(searchKeyWord) ||
                            client.getAddress().toLowerCase().contains(searchKeyWord);
                            //Integer.toString(client.getId()).contains(searchKeyWord);

                });
            });

            SortedList<Client> sortedData = new SortedList<>(filteredData);

            //Bind sorted result with table view
            sortedData.comparatorProperty().bind(dataTable.comparatorProperty());

            //Apply filtered and sorted data to the table view
            dataTable.setItems(sortedData);

        } catch (SQLException e) {
            //todo: handle error
            e.printStackTrace();
        }
    }

    public void onAddClientButtonClient(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(AddClientController.class.getResource("addClientPage.fxml"));
            Scene addClientScene= new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(addClientScene);
            window.show();

        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }
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

    public void onChangeClientButtonClick(ActionEvent event) {
    }
}

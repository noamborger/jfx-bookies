package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.person.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class ClientListController {

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
            List<Client> c= JdbcDriverSetup.getLookup(Client.class).queryForAll();
            idCTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            nameCTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            emailCTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            addressCTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            phoneCTableColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            clientObservableList.addAll(c);
            dataTable.setItems(clientObservableList);
        } catch (SQLException e) {
            //todo: handle error
            e.printStackTrace();
        }
    }

}

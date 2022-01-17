package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.person.User;
import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.session.SessionContext;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static il.ac.hit.jfxbookies.session.SessionContext.getInstance;


public class BooksListController {

    @FXML
    private Button clientButton;
    @FXML
    private Button newBookButton;

    @FXML
    private TableView<Book> dataTable;
    @FXML
    private TableColumn<Book, Integer> idTableColumn;
    @FXML
    private TableColumn<Book, String> titleTableColumn;
    @FXML
    private TableColumn<Book, String> authorTableColumn;
    @FXML
    private TableColumn<Book, String> genreTableColumn;
    @FXML
    private TableColumn<Book,String> locationTableColumn;

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    // enter the books data to the list
    public void initialize() {

        if (User.UserType.LIBRARIAN == getInstance().getCurrentUser().getUserType()) {
            newBookButton.setVisible(false);
        }
        else{
            newBookButton.setVisible(true);
        }


        try {
            System.out.println("test....");
            List<Book> c = JdbcDriverSetup.getLookup(Book.class).queryForAll();
            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
            titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
            locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            bookObservableList.addAll(c);
            dataTable.setItems(bookObservableList);
        } catch (SQLException e) {
            //todo: handle error
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientButtonClick (ActionEvent event){

        Parent root = null;
        try {
            root = FXMLLoader.load(ClientListController.class.getResource("clientListPage.fxml"));
            Scene clientListScene= new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(clientListScene);
            window.show();

        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }



    }

    public void onNewBookButtonClick(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(AddBookController.class.getResource("addBookPage.fxml"));
            Scene addBookScene= new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(addBookScene);
            window.show();

        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }
}

package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReportController {
    @FXML
    private Button backButton;
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
    private TableColumn<Book, String> locationTableColumn;

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    public void initialize(){
        System.out.println("test....");
        try {
            List<Book> b = JdbcDriverSetup.getDao(Book.class).queryForAll();//inventory.showInventory();

            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
            titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
            locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            bookObservableList.addAll(b);
            dataTable.setItems(bookObservableList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onBackButtonClick(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(BooksListController.class.getResource("booksListPage.fxml"));
            Scene booksListScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(booksListScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

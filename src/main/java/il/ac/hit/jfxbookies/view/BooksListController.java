package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import il.ac.hit.jfxbookies.person.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static il.ac.hit.jfxbookies.session.SessionContext.getInstance;

@Component
@FxmlView("booksListPage.fxml")
public class BooksListController {
    @FXML
    private Button newBookButton;
    @FXML
    private TextField searchBookField;
    @FXML
    private Button reportButton;

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

    @Autowired
    private Inventory inventory;

    @Autowired
    private FxWeaver fxWeaver;

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    public BooksListController() {

    }

    // enter the books data to the list
    public void initialize() {

        newBookButton.setVisible(User.UserType.LIBRARIAN != getInstance().getCurrentUser().getUserType());
        reportButton.setVisible(User.UserType.LIBRARIAN != getInstance().getCurrentUser().getUserType());


        try {
            List<Book> c = inventory.getAllBooks();
            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
            titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
            locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            bookObservableList.addAll(c);
            dataTable.setItems(bookObservableList);

            FilteredList<Book> filteredData = new FilteredList<>(bookObservableList, book -> true);

            searchBookField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                filteredData.setPredicate(book -> {

                    //if no search value then display all records or what ever records it current have. no change
                    if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyWord = newValue.toLowerCase();
                    return book.getTitle().toLowerCase().contains(searchKeyWord) ||
                            book.getAuthor().toLowerCase().contains(searchKeyWord) ||
                            book.getGenre().toLowerCase().contains(searchKeyWord) ||
                            book.getLocation().toLowerCase().contains(searchKeyWord) ||
                            Integer.toString(book.getSku()).contains(searchKeyWord);
                });
            }));

            SortedList<Book> sortedData = new SortedList<>(filteredData);

            //Bind sorted result with table view
            sortedData.comparatorProperty().bind(dataTable.comparatorProperty());

            //Apply filtered and sorted data to the table view
            dataTable.setItems(sortedData);


        } catch (SQLException e) {
            //todo: handle error
            e.printStackTrace();
        }
    }

    @FXML
    private void onClientButtonClick(ActionEvent event) {
        Parent root = fxWeaver.loadView(ClientListController.class);
        Scene clientListScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(clientListScene);
        window.show();
    }

    public void onNewBookButtonClick(ActionEvent event) {
            Parent root = fxWeaver.loadView(AddBookController.class);
            Scene addBookScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(addBookScene);
            window.show();
            e.printStackTrace();
        }
    }

    public void onReportButtonClick(ActionEvent event) {
        try {
            Parent root= FXMLLoader.load(ReportController.class.getResource("reportPage.fxml"));
            Scene reportScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(reportScene);
            window.show();

        } catch (IOException e) {
    }
}

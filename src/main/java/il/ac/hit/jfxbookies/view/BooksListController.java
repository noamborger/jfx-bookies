package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BookBorrowManager;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.session.SessionContext;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @FXML
    private TableColumn<Book, String> borrowedByColumn;

    @Autowired
    private Inventory inventory;

    @Autowired
    private BookBorrowManager bookBorrowManager;

    @Autowired
    private FxWeaver fxWeaver;

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    public BooksListController() {

    }

    // enter the books data to the list
    public void initialize() {

        newBookButton.setVisible(getInstance().isCurrentUserManager());
        reportButton.setVisible(getInstance().isCurrentUserManager());
        dataTable.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    var book = row.getItem();
                    getInstance().setCurrentBook(book);
                    bookObservableList.clear();
                    GraphicsUtils.openWindow(event, InfoBookController.class);
                }
            });
            return row;
        });


        try {
            List<Book> c = inventory.getAllBooks();

            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
            titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
            locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            borrowedByColumn.setCellValueFactory(cellData -> {
                var book = cellData.getValue();
                try {
                    Client activeClientForBook = bookBorrowManager.getActiveClientForBook(book.getSku());
                    if (activeClientForBook != null) {
                        return new ReadOnlyStringWrapper(Integer.toString(activeClientForBook.getId()));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });

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
        bookObservableList.clear();
        GraphicsUtils.openWindow(event, ClientListController.class);
    }

    public void onNewBookButtonClick(ActionEvent event) {
        bookObservableList.clear();
        GraphicsUtils.openWindow(event, AddBookController.class);
    }

    public void onReportButtonClick(ActionEvent event) {
        bookObservableList.clear();
        GraphicsUtils.openWindow(event, ReportController.class);
    }
}

package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BookBorrowManager;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@FxmlView("reportPage.fxml")
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
    @FXML
    private TableColumn<Book, String> borrowByTableColumn;
    @FXML
    private Label numberOfBorrowedBooksLabel;
    @FXML
    private Label numberOfBooksLabel;

    @Autowired
    private FxWeaver fxWeaver;
    @Autowired
    private BookBorrowManager bookBorrowManager;
    @Autowired
    private Inventory inventory;

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    public void initialize() {
        try {
            List<Book> books = JdbcDriverSetup.getDao(Book.class).queryForAll();//inventory.showInventory();

            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
            titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
            locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            borrowByTableColumn.setCellValueFactory(cellData ->{
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
            bookObservableList.addAll(books);
            dataTable.setItems(bookObservableList);

            numberOfBooksLabel.setText(String.valueOf(inventory.getBooksCount()));
            numberOfBorrowedBooksLabel.setText(String.valueOf(bookBorrowManager.getActiveBookBorrowsSize()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onBackButtonClick(ActionEvent event) {
        bookObservableList.clear();
        GraphicsUtils.openWindow(event, BooksListController.class);
    }


}

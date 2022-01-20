package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    private Label numberOfBorrowedBooksLabel;
    @FXML
    private Label numberOfBooksLabel;

    @Autowired
    private FxWeaver fxWeaver;

    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    public void initialize() {
        try {
            List<Book> books = JdbcDriverSetup.getDao(Book.class).queryForAll();//inventory.showInventory();

            idTableColumn.setCellValueFactory(new PropertyValueFactory<>("sku"));
            titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
            genreTableColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
            locationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
            bookObservableList.addAll(books);
            dataTable.setItems(bookObservableList);

            numberOfBooksLabel.setText(String.valueOf(JdbcDriverSetup.getDao(BorrowBook.class).queryBuilder()
                            .where()
                            .eq("active", true)
                            .query()
                            .size()));
            numberOfBorrowedBooksLabel.setText(String.valueOf(JdbcDriverSetup.getDao(BorrowBook.class).queryForAll().size()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onBackButtonClick(ActionEvent event) {
        bookObservableList.clear();
        GraphicsUtils.openWindow(event, BooksListController.class);
    }


}

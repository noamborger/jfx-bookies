package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;


public class BooksListController {
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

    //public void initialize()

    public void initialize() {
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

/*
    try{

        Book book=JdbcDriverSetup.getLookup(Book.class);

    }catch (SQLException e){

    }
*/
}

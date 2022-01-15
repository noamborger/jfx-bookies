package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.library.book.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.application.Application;
import javafx.scene.control.TableColumn;
import il.ac.hit.jfxbookies.JdbcDriverSetup;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;




public class BooksListController {
    @FXML
    private TableView<Book> dataTable;
    @FXML
    private TableColumn<Book, Integer> IDTableColumn;
    @FXML
    private TableColumn<Book, String> TitleTableColumn;
    @FXML
    private TableColumn<Book, String> AuthorTableColumn;
    @FXML
    private TableColumn<Book, String> GenreTableColumn;
    @FXML
    private TableColumn<Book,String> LocationTableColumn;

    ObservableList<Book> BookObservableList= FXCollections.observableArrayList();

    //public void initialize()


/*
    try{

        Book book=JdbcDriverSetup.getLookup(Book.class);

    }catch (SQLException e){

    }
*/
}

package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.BookBorrowManager;
import il.ac.hit.jfxbookies.library.managing.BorrowBook;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.person.User;
import il.ac.hit.jfxbookies.session.SessionContext;
import il.ac.hit.jfxbookies.util.GraphicsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

import static il.ac.hit.jfxbookies.session.SessionContext.getInstance;


@Component
@FxmlView("infoBookPage.fxml")
public class InfoBookController {

    @FXML
    private Button removeBookButton;
    @FXML
    private Label skuLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label genreLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label isBorrowedLabel;
    @FXML
    private Label clientLabel;
    @FXML
    private TextField clientIdPhoneField;
    @FXML
    private Button borrowButton;

    @Autowired
    private BookBorrowManager bookBorrowManager;



    @Autowired
    private FxWeaver fxWeaver;

    public void initialize() {
        removeBookButton.setVisible(User.UserType.LIBRARIAN != getInstance().getCurrentUser().getUserType());

        Book book = SessionContext.getInstance().getCurrentBook();
        BorrowBook borrowBook = null;
        try {
            borrowBook = JdbcDriverSetup.getDao(BorrowBook.class).queryBuilder()
                    .where()
                    .eq("book_id", book.getSku())
                    .and()
                    .eq("active", 1)
                    .queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        skuLabel.setText(String.valueOf(book.getSku()));
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        genreLabel.setText(book.getGenre());
        locationLabel.setText(book.getLocation());
        if(borrowBook==null){
            isBorrowedLabel.setText("In library");
        }
        else {
            isBorrowedLabel.setText("Borrowed");
            clientLabel.setText(String.valueOf(borrowBook.getClient().getId()));
        }


    }

    public void onRemoveBookButtonClick(ActionEvent event) {
        Inventory inventory = new Inventory();
        try {
            inventory.remove(Integer.parseInt(skuLabel.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onBackButtonClick(ActionEvent event) {
        SessionContext.getInstance().setCurrentBook(null);
        GraphicsUtils.openWindow(event, BooksListController.class);
    }

    public void onBorrowButtonClick(ActionEvent event) {

        String idPhone = clientIdPhoneField.getText();
        try {
            Client client = JdbcDriverSetup.getDao(Client.class).queryForId(idPhone);

            bookBorrowManager.borrowBookByClient(SessionContext.getInstance().getCurrentBook(), client);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void onReturnButtonClick (ActionEvent actionEvent) {
        try {
            BorrowBook borrowBook = JdbcDriverSetup.getDao(BorrowBook.class).queryBuilder()
                    .where()
                    .eq("book_id", SessionContext.getInstance().getCurrentBook().getSku())
                    .and()
                    .eq("client_id", clientLabel.getText())
                    .queryForFirst();
            bookBorrowManager.deactivateBookBorrow(borrowBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

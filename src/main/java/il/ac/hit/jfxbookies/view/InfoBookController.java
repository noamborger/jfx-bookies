package il.ac.hit.jfxbookies.view;

import il.ac.hit.jfxbookies.JdbcDriverSetup;
import il.ac.hit.jfxbookies.library.book.Book;
import il.ac.hit.jfxbookies.library.managing.Inventory;
import il.ac.hit.jfxbookies.person.Client;
import il.ac.hit.jfxbookies.person.User;
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

import java.io.IOException;
import java.sql.SQLException;

import static il.ac.hit.jfxbookies.session.SessionContext.getInstance;


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



    public void initialize(){
        removeBookButton.setVisible(User.UserType.LIBRARIAN != getInstance().getCurrentUser().getUserType());

        Book book = new Book().showBookInfo("1");
        skuLabel.setText(String.valueOf(book.getSku()));
        titleLabel.setText(book.getTitle());
        authorLabel.setText(book.getAuthor());
        genreLabel.setText(book.getGenre());
        locationLabel.setText(book.getLocation());


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
        try {
            Parent root = FXMLLoader.load(BooksListController.class.getResource("booksListPage.fxml"));
            Scene booksListScene= new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(booksListScene);
            window.show();

        } catch (IOException e) {
            System.err.println("error");
            e.printStackTrace();
        }
    }

    public void onBorrowButtonClick(ActionEvent event) {

        String idPhone = clientIdPhoneField.getText();
        try {
            Client client = JdbcDriverSetup.getDao(Client.class).queryForId(idPhone);  //ask ben



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

package sample.Controllers.AdminControllers;

import edu.duke.FileResource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.DataUpdater;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;
import sample.NeededClasses.Interfaces.GetTheObject;
import sample.NeededClasses.Interfaces.UpdateTheData;

import java.io.IOException;

public class AdminRegistersBookController {

    @FXML private Label registrationLabel;
    @FXML private Label bookNameLabel;
    @FXML private Label authorLabel;
    @FXML private TextField bookNameTextField;
    @FXML private TextField authorTextField;
    @FXML private Button addBookButton;
    @FXML private Label numOfPagesLabel;
    @FXML private TextField numOfPagesTextField;
    @FXML private Button goBackButton;
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void addBookButtonPressed(ActionEvent event) throws IOException {
        String name = bookNameTextField.getText();
        String author = authorTextField.getText();
        String noOfPages = numOfPagesTextField.getText();

        Book book = new Book(name,author,noOfPages);
        boolean bookRegistered = isBookRegistered(book);
        if (!bookRegistered) {
            UpdateTheData dataUpdater = new DataUpdater();
            dataUpdater.addLinetoSpecificData(book.toString(),
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");

            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Book is added to library system.", ButtonType.OK);
            alert.setTitle("Book Added");
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/BookConfigQuestion.fxml", event);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Book is already in the library.", ButtonType.OK);
            alert.setTitle("Book Already Registered");
            alert.setHeaderText(null);
            alert.showAndWait();

            bookNameTextField.setText(" ");
            authorTextField.setText("");
            numOfPagesTextField.setText("");
            bookNameTextField.selectAll();
            bookNameTextField.requestFocus();
        }
    }

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/BookConfigQuestion.fxml",event);
    }

    private boolean isBookRegistered(Book book) {
        FileResource fr = new FileResource("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
        GetTheObject objectReturner = new ObjectFormReturner();

        for (String line : fr.lines()) {
            Book currBook = objectReturner.getBookFromString(line);
            System.out.println(currBook);
            if (currBook.toString().equals(book.toString())) {
                return true;
            }
        }
        return false;
    }
}

package sample.Controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.ImplOfInterfaces.Student.SaveBooksToStudent;
import sample.NeededClasses.Interfaces.*;
import sample.NeededClasses.Interfaces.Student.GetTheBooksBackToStudent;
import sample.NeededClasses.Student;

import java.io.IOException;
import java.util.Optional;

public class BookBorrowedByStudentReviewPaneController {

    @FXML private Pane mainPane;
    @FXML private Button backToLoginButton;
    @FXML private Label bookTitleLabel;
    @FXML private Label bookNameLabel;
    @FXML private Label authorLabel;
    @FXML private Text bookNameText;
    @FXML private Text authorText;
    @FXML private Label noOfPagesLabel;
    @FXML private Text noOfPagesText;
    @FXML private Button getTheBookToLibraryButton;
    ChangeThePage pageChanger = new PageChange();
    GetTheLastLine lastLine = new LastLineReturner();
    GetTheObject objectReturner = new ObjectFormReturner();
    private Book bookReviewed;

    @FXML
    void backToLoginButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/StudentSettings.fxml",event);
    }

    @FXML
    void getTheBookToLibraryButtonPressed(ActionEvent event) throws IOException {

        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Get Book To Library");
        alert1.setHeaderText("Are you sure...");
        alert1.setContentText("To get the book back to library back without informing student?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK){
            UpdateTheData dataUpdater = new DataUpdater();
            dataUpdater.addLinetoSpecificData(bookReviewed.toString(),
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
            RemovingTheLine lineRemover = new LineRemover();
            lineRemover.removeTheLine(bookReviewed.toString(),
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
            Student std = objectReturner.getStudentFromString(
                    lastLine.getLastLine(
                            "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt"));
            GetTheBooksBackToStudent getBooksBack = new SaveBooksToStudent();
            getBooksBack.addBooksBackToStudent();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "This book is added back to library.", ButtonType.OK);
            alert.setTitle("Book Returned");
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/StudentSettings.fxml",event);
            }
        }

    }

    public void initialize() throws IOException {
        String lastBook = lastLine.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        bookReviewed = objectReturner.getBookFromString(lastBook);
    }
}

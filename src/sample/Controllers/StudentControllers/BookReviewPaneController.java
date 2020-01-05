package sample.Controllers.StudentControllers;

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
import sample.NeededClasses.Interfaces.*;
import sample.NeededClasses.Student;

import java.io.*;

public class BookReviewPaneController {

    @FXML private Pane mainPane;
    @FXML private Button backToLoginButton;
    @FXML private Label bookTitleLabel;
    @FXML private Label bookNameLabel;
    @FXML private Label authorLabel;
    @FXML private Text bookNameText;
    @FXML private Text authorText;
    @FXML private Label noOfPagesLabel;
    @FXML private Text noOfPagesText;
    @FXML private Button getTheBookButton;
    private Student student;
    private Book bookReviewed;
    ChangeThePage pageChanger = new PageChange();
    GetTheLastLine lastLineGetter = new LastLineReturner();

    @FXML
    void backToLoginButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml",event);
    }

    @FXML
    void getTheBookPressed(ActionEvent event) throws IOException {
        UpdateTheData dataUpdater = new DataUpdater();
        dataUpdater.addLinetoSpecificData(bookReviewed.toString(),
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        RemovingTheLine lineRemover = new LineRemover();
        lineRemover.removeTheLine(bookReviewed.toString(),
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "This book is added to your personal list.",ButtonType.OK);
        alert.setTitle("Book Borrowed");
        alert.setHeaderText(null);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentGetsBook.fxml",event);
        }
    }

    public void initialize() throws IOException {
        GetTheObject objectReturner = new ObjectFormReturner();

        String bookInProgress = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");

        bookReviewed = objectReturner.getBookFromString(bookInProgress);

        bookNameText.setText(bookReviewed.getName());
        authorText.setText(bookReviewed.getAuthor());
        noOfPagesText.setText(bookReviewed.getNoOfPages());

        String studentLoggedIn = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        student = objectReturner.getStudentFromString(studentLoggedIn);
    }
}
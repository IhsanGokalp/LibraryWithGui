package sample.Controllers.StudentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import jdk.jfr.DataAmount;
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.Interfaces.*;
import sample.NeededClasses.Student;

import java.io.IOException;

public class ListBookPaneController {

    @FXML private Pane mainPane;
    @FXML private Label bookTitleLabel;
    @FXML private Label bookNameLabel;
    @FXML private Label authorLabel;
    @FXML private Text bookNameText;
    @FXML private Text authorText;
    @FXML private Label noOfPagesLabel;
    @FXML private Text noOfPagesText;
    @FXML private Button returnTheBookButton;
    @FXML private Button backToMenuButton;
    private Student student;
    private Book bookInProgress;
    GetTheLastLine lastLineGetter = new LastLineReturner();
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void backToMenuButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml",event);
    }

    @FXML
    void returnTheBookPressed(ActionEvent event) throws IOException {
        student.removeBook(bookInProgress);
        String lastLine = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        RemovingTheLine lineRemover = new LineRemover();
        lineRemover.removeTheLine(lastLine,
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        UpdateTheData dataUpdater = new DataUpdater();
        dataUpdater.addLinetoSpecificData(lastLine,
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "This book is removed from your personal list.", ButtonType.OK);
        alert.setTitle("Book Returned");
        alert.setHeaderText(null);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml",event);
        }
    }

    @FXML
    public void initialize() throws IOException {
        GetTheObject objectReturner = new ObjectFormReturner();
        String bookToBeProcessed = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");

        bookInProgress = objectReturner.getBookFromString(bookToBeProcessed);

        bookNameText.setText(bookInProgress.getName());
        authorText.setText(bookInProgress.getAuthor());
        noOfPagesText.setText(bookInProgress.getNoOfPages());

        String studentLoggedIn = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        student = objectReturner.getStudentFromString(studentLoggedIn);
    }
}

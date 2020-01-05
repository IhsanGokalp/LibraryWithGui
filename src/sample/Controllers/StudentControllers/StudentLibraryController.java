package sample.Controllers.StudentControllers;

import edu.duke.FileResource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.ImplOfInterfaces.Student.SaveBooksToStudent;
import sample.NeededClasses.Interfaces.*;
import sample.NeededClasses.Interfaces.Student.GetTheBooksBackToStudent;
import sample.NeededClasses.Student;

import java.io.*;
public class StudentLibraryController {

    public Student student;
    @FXML private Pane mainPane;
    @FXML private Label questionLabel;
    @FXML private Button getBookButton;
    @FXML private Button listBooksButton;
    @FXML private Button backToLoginButton;
    ChangeThePage pageChanger = new PageChange();
    GetTheLastLine lastLineGetter = new LastLineReturner();
    RemovingTheLine lineRemover = new LineRemover();

    @FXML
    void backToLoginButtonPressed(ActionEvent event) throws IOException {
        GetTheBooksBackToStudent studentSaverAtTheEnd = new SaveBooksToStudent();
        studentSaverAtTheEnd.addBooksBackToStudent();
        FileResource fr = new FileResource(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        for (String line : fr.lines()) {
            lineRemover.removeTheLine(line,
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        }
        pageChanger.changeThePageTo("/sample/FXMLFiles/LoginSign.fxml", event);


    }

    @FXML
    void getBookButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentGetsBook.fxml", event);
    }

    @FXML
    void listBooksButtonPressed(ActionEvent event) throws IOException {
        String booksOfLoggedStudent = lastLineGetter.getLastLine("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        if (booksOfLoggedStudent.equals("") || booksOfLoggedStudent.equals(" ")) {
            pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentBookList.fxml", event);
        }
        else {
            pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/ListBooksStudentHas.fxml",event);
        }
    }

    public void initialize() throws IOException {

        String lastLine = lastLineGetter.getLastLine("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        lastLine = lastLine.substring(2);

        GetTheObject objectGetter = new ObjectFormReturner();

        student = objectGetter.getStudentFromString(lastLine);
    }
}

package sample.Controllers.AdminControllers;

import edu.duke.FileResource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.NeededClasses.Admin;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.ImplOfInterfaces.Student.BookSaver;
import sample.NeededClasses.Interfaces.*;
import sample.NeededClasses.Interfaces.Student.SaveBooksOfStudent;
import sample.NeededClasses.Student;
import java.io.IOException;
import java.util.Optional;

public class StudentInfoPaneController {

    @FXML private Pane mainPane;
    @FXML private Label titleLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label phoneNumLabel;
    @FXML private Text nameText;
    @FXML private Text surnameText;
    @FXML private Text phoneNumText;
    @FXML private Label numOfBooksLabel;
    @FXML private Text numOfBooksText;
    @FXML private Button deleteStudentButton;
    @FXML private Button listBookButton;
    @FXML private Button goBackButton;
    ChangeThePage pageChanger = new PageChange();
    private Student student;
    private int count = 0;
    private Admin admin;
    private String booksOfLoggedStudent;


    @FXML
    void deleteStudentButtonPressed(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Password Confirmation");
        dialog.setContentText("Enter the password of " + admin.getAdminName() + ":");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(admin.getPassword())){
            RemovingTheLine lineRemover = new LineRemover();
            if (count>0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Book Deletion Confirmation");
                alert.setHeaderText("This guy has some books from library.");
                alert.setContentText("Should we delete them with him or get them back to library?");

                ButtonType buttonTypeSaveBooks = new ButtonType("Get Books Back To Library");
                ButtonType buttonTypeDeleteBooks = new ButtonType("Delete Books With The Student");
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeSaveBooks, buttonTypeDeleteBooks, buttonTypeCancel);
                FileResource fr = new FileResource(
                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
                Optional<ButtonType> result1 = alert.showAndWait();
                if (result1.get() == buttonTypeSaveBooks){
                    UpdateTheData dataUpdater = new DataUpdater();

                    for (String line: fr.lines()){
                        dataUpdater.addLinetoSpecificData(line,
                                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
                        lineRemover.removeTheLine(line,
                                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
                    }
                    lineRemover.removeTheLine(booksOfLoggedStudent,
                            "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
                    pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/StudentSettings.fxml",event);

                } else if (result1.get() == buttonTypeDeleteBooks) {
                    lineRemover.removeTheLine(booksOfLoggedStudent,
                            "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
                    for (String line: fr.lines()){
                        lineRemover.removeTheLine(line,
                                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
                    }
                    lineRemover.removeTheLine(booksOfLoggedStudent,
                            "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
                    pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/StudentSettings.fxml",event);
                }

            }
            else {
                lineRemover.removeTheLine(booksOfLoggedStudent,
                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
            }
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION,
                    "Successfully deleted Student.", ButtonType.OK);
            alert1.setTitle("Delete Successful");
            alert1.setHeaderText(null);
            alert1.showAndWait();
            if (alert1.getResult() == ButtonType.OK) {
                pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
            }
        }
    }

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
    }

    @FXML
    void listBookButtonPressed(ActionEvent event) throws IOException {
        GetTheLastLine lastLineGetter = new LastLineReturner();
        String booksOfLoggedStudent = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");

        if (booksOfLoggedStudent.equals("") || booksOfLoggedStudent.equals(" ")) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION,
                    "This Student did not borrow any book from library.", ButtonType.OK);
            alert1.setTitle("No Book Borrowed");
            alert1.setHeaderText(null);
            alert1.showAndWait();
        }
        else {
            pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/BooksWithStudent.fxml",event);
        }
    }

    public void initialize() throws IOException {
        GetTheObject objectReturner = new ObjectFormReturner();
        GetTheLastLine lastLineGetter = new LastLineReturner();
        String adminStr = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
        booksOfLoggedStudent = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        student = objectReturner.getStudentFromString(booksOfLoggedStudent);
        admin  = objectReturner.getAdminFromString(adminStr);
        SaveBooksOfStudent bookSaver = new BookSaver();
        bookSaver.autoSaverOfBooks(booksOfLoggedStudent.substring(2));
        nameText.setText(student.getName());
        surnameText.setText(student.getSurname());
        phoneNumText.setText(student.getPhoneNumber());
        FileResource fr = new FileResource(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        for (String line : fr.lines()) {
            if (!line.equals("") && !line.equals(" "))
                count++;
        }
        numOfBooksText.setText("" + count);
    }
}

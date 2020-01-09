package sample.Controllers.AdminControllers;

import edu.duke.FileResource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.ImplOfInterfaces.Admin.StudentRemoverToEnd;
import sample.NeededClasses.Interfaces.*;
import sample.NeededClasses.Interfaces.Admin.GetStudentToLastLine;
import sample.NeededClasses.Student;

import java.io.IOException;

public class StudentSettingsController {

    @FXML private BorderPane mainBorderPane;
    @FXML private ListView<Student> studentsListView;
    @FXML private Pane mainPane;
    @FXML private Button goBackButton;
    private final ObservableList<Student> students =
            FXCollections.observableArrayList();

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        ChangeThePage pageChanger = new PageChange();
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
    }
    public void initialize() throws IOException {
        GetTheLastLine lastLineGetter = new LastLineReturner();
        String booksOfLoggedStudent = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");

        if (booksOfLoggedStudent.equals("") || booksOfLoggedStudent.equals(" ")) {
            Parent customerSignPage = FXMLLoader.load(getClass().getResource(
                    "/sample/FXMLFiles/AdminOnes/NoStudentRegisteredController.fxml"));
            mainBorderPane.setCenter(customerSignPage);
        }
        else {
            FileResource fr = new FileResource(
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
            GetTheObject objectFormReturner = new ObjectFormReturner();

            for (String line : fr.lines()) {
                Student currStudent = objectFormReturner.getStudentWithBooks(line);
                students.add(currStudent);
            }
            studentsListView.setItems(students);

            studentsListView.getSelectionModel().selectedItemProperty().addListener(
                    new ChangeListener<Student>() {
                        @Override
                        public void changed(ObservableValue<? extends Student> observableValue,
                                            Student oldStudent, Student newStudent) {
                            try {
                                RemovingTheLine lineRemover = new LineRemover();
                                GetStudentToLastLine stdGetter = new StudentRemoverToEnd();
                                stdGetter.getTheStudentToEnd(newStudent.toString());
                                FileResource fr = new FileResource(
                                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");

                                for (String line : fr.lines()) {
                                    lineRemover.removeTheLine(line,
                                            "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
                                }
                                Parent customerSignPage = FXMLLoader.load(getClass().getResource(
                                        "/sample/FXMLFiles/AdminOnes/StudentInfoPaneOnAdmin.fxml"));
                                mainBorderPane.setCenter(customerSignPage);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
        }
    }
}

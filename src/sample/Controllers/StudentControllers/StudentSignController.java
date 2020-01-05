package sample.Controllers.StudentControllers;

import edu.duke.FileResource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.ImplOfInterfaces.Student.BookSaver;
import sample.NeededClasses.Interfaces.*;
import sample.NeededClasses.Interfaces.Student.SaveBooksOfStudent;
import sample.NeededClasses.Student;

import java.io.*;

public class StudentSignController {

    private int studentID;
    @FXML private Label studentLabel;
    @FXML private Label nameLabel;
    @FXML private Label surnameLabel;
    @FXML private Label phoneNoLabel;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField phoneNoTextField;
    @FXML private Button nextButton;
    @FXML private Button backButton;
    ChangeThePage pageChanger = new PageChange();
    GetTheObject objectReturner = new ObjectFormReturner();

    public void initialize() {
        FileResource fr = new FileResource("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        int idCount = 0;
        for(String line : fr.lines()) {
            idCount++;
        }
        studentID = idCount;
    }

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/LoginSign.fxml",event);
    }

    @FXML
    void nextButtonPressed(ActionEvent event) throws IOException {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String phoneNo = phoneNoTextField.getText();
        Student student = new Student(name,surname,phoneNo);

        updateStudentData(student);
        pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml",event);
    }

    private void updateStudentData(Student student) throws IOException {
        UpdateTheData dataUpdater = new DataUpdater();

        boolean studentExist = false;
        FileResource fr = new FileResource("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");

        for (String line : fr.lines()) {
            if (line.equals(" ") || line.equals("")) {
                break;
            }
            else if (!line.equals(" ") || !line.equals("")){
                String studentsStr = line.substring(2);
                studentExist = isStudentRegistered(studentsStr, student);
                if (studentExist == true)
                    break;
            }
        }
        if(!studentExist) {
            studentID++;
            dataUpdater.addLinetoSpecificData(studentID + "-" + student.toString(),
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");

            System.out.println("Successfully added info to the StudentsData.");
        }
        else{
            for (String line : fr.lines()) {
                String id = line.substring(0,2);
                String stdInfo = line.substring(2);

                Student tempStd = objectReturner.getStudentFromString(line);

                if (student.toString().equals(tempStd.toString())) {

                    RemovingTheLine lineRemover = new LineRemover();
                    lineRemover.removeTheLine(line,
                            "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");

                    SaveBooksOfStudent bookSaver = new BookSaver();
                    bookSaver.autoSaverOfBooks(stdInfo);
                    studentID++;
                    dataUpdater.addLinetoSpecificData(studentID + "-" + student.toString(),
                            "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");

                    break;
                }
            }
        }
    }

    public boolean isStudentRegistered(String line, Student student) {

        Student std = objectReturner.getStudentFromString(line);
        if (std.toString().equals(student.toString()))
            return true;
        else
            return false;
    }
}

package sample.Controllers.StudentControllers;

import edu.duke.FileResource;
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
import sample.NeededClasses.Student;

import java.io.*;
import java.util.Scanner;

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
        Parent customerSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/LoginSign.fxml"));
        Scene driverSignScene = new Scene(customerSignPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(driverSignScene);
        appStage.show();
    }

    @FXML
    void nextButtonPressed(ActionEvent event) throws IOException {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String phoneNo = phoneNoTextField.getText();
        Student student = new Student(name,surname,phoneNo);

        updateStudentData(student);
        goToStudentPage(event);
    }

    private void updateStudentData(Student student) throws IOException {

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
            addStudent(student);
            System.out.println("Successfully added info to the StudentsData.");
        }
        else{
            for (String line : fr.lines()) {
                String id = line.substring(0,2);
                String stdInfo = line.substring(2);
                if (stdInfo.equals(student.toString())) {
                    removeLine(line);
                    addStudent(student);
                    break;
                }
            }
        }
    }

    public void removeLine(String lineContent) throws IOException {
        File inputFile = new File("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        File tempFile = new File("myTempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String lineToRemove = lineContent;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        boolean successful = tempFile.renameTo(inputFile);
        System.out.println(successful);
    }

    private void addStudent(Student student) throws IOException {
        FileWriter myWriter = new FileWriter("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt", true);
        BufferedWriter bfWriter = new BufferedWriter(myWriter);
        bfWriter.write(studentID + "-" + student.toString());
        bfWriter.newLine();
        studentID++;
        bfWriter.close();
    }

    public boolean isStudentRegistered(String line, Student student) {
        if (line.equals(student.toString()))
            return true;
        else
            return false;
    }

    private void goToStudentPage(ActionEvent event) throws IOException {
        Parent customerSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml"));
        Scene driverSignScene = new Scene(customerSignPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(driverSignScene);
        appStage.show();
    }
}

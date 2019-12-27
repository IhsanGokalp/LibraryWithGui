package sample.Controllers.StudentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.NeededClasses.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentLibraryController {

    protected Student student;
    @FXML private Pane mainPane;
    @FXML private Label questionLabel;
    @FXML private Button getBookButton;
    @FXML private Button listBooksButton;
    @FXML private Button babckToLoginButton;

    @FXML
    void backToLoginButtonPressed(ActionEvent event) throws IOException {
        Parent customerSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/LoginSign.fxml"));
        Scene driverSignScene = new Scene(customerSignPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(driverSignScene);
        appStage.show();
    }

    @FXML
    void getBookButtonPressed(ActionEvent event) throws IOException {
        Parent customerSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/StudentOnes/StudentBookList.fxml"));
        Scene driverSignScene = new Scene(customerSignPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(driverSignScene);
        appStage.show();
    }

    @FXML
    void listBooksButtonPressed(ActionEvent event) {

    }

    public void initialize() throws IOException {
        String sCurrentLine;

        BufferedReader br = new BufferedReader(new FileReader("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt"));
        String lastLine = "";

        while ((sCurrentLine = br.readLine()) != null)
        {
            System.out.println(sCurrentLine);
            lastLine = sCurrentLine;
        }
        lastLine = lastLine.substring(2);
        int nameBeginIdx = lastLine.indexOf('\'')+1;
        int nameEndIdx = lastLine.indexOf('\'',nameBeginIdx);
        String name = lastLine.substring(nameBeginIdx,nameEndIdx);

        int surnameBeginIdx = lastLine.indexOf('\'',nameEndIdx+1)+1;
        int surnameEndIdx = lastLine.indexOf('\'',surnameBeginIdx);
        String surname = lastLine.substring(surnameBeginIdx,surnameEndIdx);

        int phoneNoBeginIdx = lastLine.indexOf('\'',surnameEndIdx+1)+1;
        int phoneNoEndIdx = lastLine.indexOf('\'',phoneNoBeginIdx);
        String phoneNo = lastLine.substring(phoneNoBeginIdx,phoneNoEndIdx);

        
    }
}

package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private Label appTitle;
    @FXML private Label questionLabel;
    @FXML private RadioButton StudentRadioButton;
    @FXML private ToggleGroup userSelection;
    @FXML private Label orLabel;
    @FXML private RadioButton AdminRadioButton;
    @FXML private Button toSignFormButton;

    @FXML
    void open_signForm(ActionEvent event) throws IOException {
        if(StudentRadioButton.isSelected()) {
            Parent driverSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/StudentOnes/StudentSign.fxml"));
            Scene driverSignScene = new Scene(driverSignPage);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(driverSignScene);
            appStage.show();
        }
        else if (AdminRadioButton.isSelected()) {
            Parent customerSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/AdminOnes/AdminSign.fxml"));
            Scene driverSignScene = new Scene(customerSignPage);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(driverSignScene);
            appStage.show();
        }
        else{
        }
    }

}

package sample.Controllers.AdminControllers;

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

import java.io.IOException;

public class AdminSignController {

    @FXML private Label adminLabel;
    @FXML private Label usernameLabel;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Button nextButton;
    @FXML private Label passwordLabel;
    @FXML private Button backButton;

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        Parent customerSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/LoginSign.fxml"));
        Scene driverSignScene = new Scene(customerSignPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(driverSignScene);
        appStage.show();
    }
    @FXML
    void nextButtonPressed(ActionEvent event) {

    }

}

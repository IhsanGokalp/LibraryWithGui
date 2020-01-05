package sample.Controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;

import java.io.IOException;

public class AdminSettingsQuestionController {

    @FXML private Label adminLabel;
    @FXML private Button addAnotherAdminButton;
    @FXML private Button deleteAdminAdminButton;
    @FXML private Button goBackButton;
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void addAdminButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminRegistration.fxml",event);
    }

    @FXML
    void deleteAdminButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminDelete.fxml",event);
    }

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
    }

}

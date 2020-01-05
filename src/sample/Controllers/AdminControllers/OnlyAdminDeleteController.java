package sample.Controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;

import java.io.IOException;

public class OnlyAdminDeleteController {

    @FXML private Pane mainPane;
    @FXML private Label mainLabel;
    @FXML private Button goBackButton;
    @FXML private Button addAnotherAdminButton;
    ChangeThePage pageChanger = new PageChange();


    @FXML
    void addAdminButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminRegistration.fxml",event);
    }

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminSettingsQuestion.fxml",event);
    }
}

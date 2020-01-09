package sample.Controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;

import java.io.IOException;

public class AdminPanelController {

    @FXML private Label questionLabel;
    @FXML private Button configureStudentsButton;
    @FXML private Button arrangeBooksButton;
    @FXML private Button addAdminButton;
    @FXML private Button backToLoginButton;
    ChangeThePage pageChanger = new PageChange();


    @FXML
    void backToLoginButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/LoginSign.fxml",event);
    }

    @FXML
    void addAdminButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminSettingsQuestion.fxml",event);
    }

    @FXML
    void arrangeBooksButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/BookConfigQuestion.fxml",event);
    }

    @FXML
    void configureStudentsButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/StudentSettings.fxml",event);
    }
}

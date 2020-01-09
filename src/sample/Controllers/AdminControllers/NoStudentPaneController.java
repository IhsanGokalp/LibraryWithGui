package sample.Controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;

import java.io.IOException;

public class NoStudentPaneController {

    @FXML private Pane mainPane;
    @FXML private Label noStudentLabel;
    @FXML private Button goBackButton;

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        ChangeThePage pageChanger = new PageChange();
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
    }

}

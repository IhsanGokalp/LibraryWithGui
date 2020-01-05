package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;

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
        ChangeThePage pageChanger = new PageChange();
        if(StudentRadioButton.isSelected()) {
            pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentSign.fxml", event);
        }
        else if (AdminRadioButton.isSelected()) {
            pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminSign.fxml",event);
        }
        else{
        }
    }

}

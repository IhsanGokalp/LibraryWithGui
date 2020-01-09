package sample.Controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;

import java.io.IOException;

public class BookConfigController {

    @FXML private Label titleLabel;
    @FXML private Button addBookButton;
    @FXML private Button deleteBookPressed;
    @FXML private Button goBackButton;
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void addBookButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminAddsBookToLibrary.fxml",event);
    }

    @FXML
    void deleteBookPressedPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminBookDelete.fxml",event);
    }

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
    }

}

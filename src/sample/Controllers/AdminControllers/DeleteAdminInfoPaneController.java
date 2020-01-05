package sample.Controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.NeededClasses.Admin;
import sample.NeededClasses.ImplOfInterfaces.LastLineReturner;
import sample.NeededClasses.ImplOfInterfaces.LineRemover;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;
import sample.NeededClasses.Interfaces.GetTheLastLine;
import sample.NeededClasses.Interfaces.GetTheObject;
import sample.NeededClasses.Interfaces.RemovingTheLine;

import java.io.IOException;
import java.util.Optional;

public class DeleteAdminInfoPaneController {

    @FXML private Pane adminInfoPane;
    @FXML private Button goBackButton;
    @FXML private Label adminInfoLabel;
    @FXML private Label adminNameLabel;
    @FXML private Button deleteAdminButton;
    @FXML private Text adminNameText;
    private Admin admin;
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void deleteAdminButtonPressed(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Password Confirmation");
        dialog.setContentText("Enter the password of " + admin.getAdminName() + ":");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(admin.getPassword())){
            RemovingTheLine lineRemover = new LineRemover();
            lineRemover.removeTheLine(admin.toString(),
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully deleted Admin.", ButtonType.OK);
            alert.setTitle("Delete Successful");
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
            }
        }
    }

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminSettingsQuestion.fxml",event);
    }

    public void initialize() throws IOException {
        GetTheObject objectFormGetter = new ObjectFormReturner();
        GetTheLastLine lastLine = new LastLineReturner();
        admin = objectFormGetter.getAdminFromString(lastLine.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt"));
        adminNameText.setText(admin.getAdminName());
    }
}

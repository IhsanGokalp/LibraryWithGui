package sample.Controllers.AdminControllers;

import edu.duke.FileResource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.NeededClasses.Admin;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.Interfaces.*;

import java.io.IOException;

public class AdminSignController {

    @FXML private Label adminLabel;
    @FXML private Label usernameLabel;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Button nextButton;
    @FXML private Label passwordLabel;
    @FXML private Button backButton;
    ChangeThePage pageChanger = new PageChange();
    GetTheObject objectGetter = new ObjectFormReturner();

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/LoginSign.fxml", event);
    }
    @FXML
    void nextButtonPressed(ActionEvent event) throws IOException {
        String adminName = usernameTextField.getText();
        String pass = passwordTextField.getText();

        boolean valid = isAdminValid(adminName,pass);
        if (valid){
            RemovingTheLine lineRemover = new LineRemover();
            UpdateTheData dataUpdater = new DataUpdater();
            lineRemover.removeTheLine((new Admin(adminName,pass)).toString(),
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
            dataUpdater.addLinetoSpecificData((new Admin(adminName,pass).toString()),
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
            pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "The data you provided is not valid.", ButtonType.OK);
            alert.setTitle("Wrong Username or Password");
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                usernameTextField.setText("");
                passwordTextField.setText("");
                usernameTextField.selectAll();
                usernameTextField.requestFocus();
            }
        }
    }

    private boolean isAdminValid(String name, String pass) {
        FileResource fr = new FileResource("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");

        for (String line : fr.lines()) {
            Admin currAdmin = objectGetter.getAdminFromString(line);
            if (currAdmin.getAdminName().equals(name) && currAdmin.getPassword().equals(pass))
                return true;
        }
        return false;
    }
}

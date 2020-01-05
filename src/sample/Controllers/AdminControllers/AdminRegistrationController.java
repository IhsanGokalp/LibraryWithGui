package sample.Controllers.AdminControllers;

import edu.duke.FileResource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.NeededClasses.Admin;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.Interfaces.*;

import java.io.IOException;

public class AdminRegistrationController {

    @FXML private Label registrationLabel;
    @FXML private Label usernameLabel;
    @FXML private Label passwordLabel;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Button registerButton;
    @FXML private Label retypedPasswordLabel;
    @FXML private TextField retypedPasswordTextField;
    @FXML private Button goBackButton;
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminSettingsQuestion.fxml",event);
    }

    @FXML
    void registerButtonPressed(ActionEvent event) throws IOException {
        UpdateTheData dataUpdater = new DataUpdater();
        GetTheLastLine lastLineGetter = new LastLineReturner();

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String reTypedPassword = retypedPasswordTextField.getText();

        boolean notRegistered = isAdminRegistered(username);
        System.out.println(notRegistered);

        if (notRegistered && password.equals(reTypedPassword)) {
            RemovingTheLine lineRemover = new LineRemover();

            String currAdmin = lastLineGetter.getLastLine(
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
            lineRemover.removeTheLine(currAdmin,
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
            dataUpdater.addLinetoSpecificData(currAdmin,
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
            dataUpdater.addLinetoSpecificData((new Admin(username,password)).toString(),
                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully initialized new admin user.", ButtonType.OK);
            alert.setTitle("Registration Completed");
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml",event);
            }
        }
        else if (notRegistered && !password.equals(reTypedPassword)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Passwords do not match.", ButtonType.OK);
            alert.setTitle("Passwords Do Not Match");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
        else if (!notRegistered && password.equals(reTypedPassword)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "This username already registered.", ButtonType.OK);
            alert.setTitle("Existing Username");
            alert.setHeaderText(null);
            alert.showAndWait();
            usernameTextField.setText("");
            passwordTextField.setText("");
            retypedPasswordTextField.setText("");
            usernameTextField.selectAll();
            usernameTextField.requestFocus();
        }
    }

    private boolean isAdminRegistered(String username) {
        FileResource fr = new FileResource("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
        GetTheObject objectReturner = new ObjectFormReturner();
        for (String line: fr.lines()) {
            if (!line.equals("") && !line.equals(" ")){
                Admin currAdmin = objectReturner.getAdminFromString(line);
                if (currAdmin.getAdminName().equals(username))
                    return false;
            }
        }
        return true;
    }
}


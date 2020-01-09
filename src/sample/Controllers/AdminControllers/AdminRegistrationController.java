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
    RemovingTheLine lineRemover = new LineRemover();
    GetTheObject objectReturner = new ObjectFormReturner();
    UpdateTheData dataUpdater = new DataUpdater();
    GetTheLastLine lastLineGetter = new LastLineReturner();

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminSettingsQuestion.fxml",event);
    }

    @FXML
    void registerButtonPressed(ActionEvent event) throws IOException {

        String username = usernameTextField.getText().trim();
        String password = passwordTextField.getText().trim();
        String reTypedPassword = retypedPasswordTextField.getText().trim();

        boolean notRegistered = isAdminRegistered(username);
        System.out.println(notRegistered);


        if (notRegistered && password.equals(reTypedPassword)) {
            if (!username.equals("") || !password.equals("") || !reTypedPassword.equals("")) {


                String curAdmin = lastLineGetter.getLastLine(
                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
                lineRemover.removeTheLine(curAdmin,
                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
                dataUpdater.addLinetoSpecificData((new Admin(username, password)).toString(),
                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
                dataUpdater.addLinetoSpecificData(curAdmin,
                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully initialized new admin user.", ButtonType.OK);
                alert.setTitle("Registration Completed");
                alert.setHeaderText(null);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminPanel.fxml", event);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Your data can be empty.", ButtonType.OK);
                alert.setTitle("Wrong Data");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        }
        else if (notRegistered && !password.equals(reTypedPassword)){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Passwords do not match.", ButtonType.OK);
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


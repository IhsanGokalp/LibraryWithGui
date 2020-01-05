package sample.NeededClasses.ImplOfInterfaces;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.NeededClasses.Interfaces.ChangeThePage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class PageChange implements ChangeThePage {
    @Override
    public void changeThePageTo(String path, ActionEvent event) throws IOException {
        Parent driverSignPage = FXMLLoader.load(getClass().getResource(path));
        Scene driverSignScene = new Scene(driverSignPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(driverSignScene);
        appStage.show();
    }
}


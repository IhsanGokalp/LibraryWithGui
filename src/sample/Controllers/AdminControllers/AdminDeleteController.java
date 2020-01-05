package sample.Controllers.AdminControllers;

import edu.duke.FileResource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.NeededClasses.Admin;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.Interfaces.*;

import java.io.IOException;

public class AdminDeleteController {

    @FXML private BorderPane mainBorderPane;
    @FXML private ListView<Admin> adminsListView;
    @FXML private Pane adminInfoPane;
    @FXML private Button goBackButton;
    private final ObservableList<Admin> admins =
            FXCollections.observableArrayList();
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/AdminSettingsQuestion.fxml",event);
    }

    public void initialize() throws IOException {
        FileResource fr = new FileResource(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
        GetTheObject objectReturner = new ObjectFormReturner();
        GetTheLastLine lastLineGetter = new LastLineReturner();
        String lastLine = lastLineGetter.getLastLine("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
        for (String line : fr.lines()) {
            Admin currAdmin = objectReturner.getAdminFromString(line);
            if (!lastLine.equals(line))
                admins.add(currAdmin);
        }
        adminsListView.setItems(admins);

        if (admins.isEmpty()) {
            Parent customerSignPage = FXMLLoader.load(getClass().getResource(
                    "/sample/FXMLFiles/AdminOnes/OnlyAdminDeletePane.fxml"));
            mainBorderPane.setCenter(customerSignPage);
        }

        adminsListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Admin>() {
                    @Override
                    public void changed(ObservableValue<? extends Admin> observableValue,
                                        Admin oldAdmin, Admin newAdmin) {
                        try {
                            RemovingTheLine lineRemover = new LineRemover();
                            lineRemover.removeTheLine(newAdmin.toString(),
                                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
                            UpdateTheData dataUpdater = new DataUpdater();
                            dataUpdater.addLinetoSpecificData(newAdmin.toString(),
                                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt");
                            Parent customerSignPage = FXMLLoader.load(getClass().getResource(
                                    "/sample/FXMLFiles/AdminOnes/AdminInfoPane.fxml"));
                            mainBorderPane.setCenter(customerSignPage);
                        } catch (LoadException e) {
                            System.out.println(e.getCause());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

}


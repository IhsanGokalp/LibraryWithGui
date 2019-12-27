package sample.Controllers.StudentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.NeededClasses.Book;

import java.io.IOException;

public class StudentEmptyBookList {


    @FXML private ListView<Book> bookListView;
    @FXML private Pane mainPane;
    @FXML private Button backToMenuButton;


    @FXML
    void backToMenuButtonPressed(ActionEvent event) throws IOException {
        Parent customerSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml"));
        Scene driverSignScene = new Scene(customerSignPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(driverSignScene);
        appStage.show();
    }

    public void initialize() {

    }
}
package sample.Controllers.StudentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;

import java.io.IOException;

public class StudentEmptyBookList {


    @FXML private ListView<Book> bookListView;
    @FXML private Pane mainPane;
    @FXML private Button backToMenuButton;


    @FXML
    void backToMenuButtonPressed(ActionEvent event) throws IOException {
        ChangeThePage pageChanger = new PageChange();
        pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml", event);
    }

    public void initialize() {

    }
}
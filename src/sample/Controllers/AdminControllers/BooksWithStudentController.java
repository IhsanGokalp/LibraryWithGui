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
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.DataUpdater;
import sample.NeededClasses.ImplOfInterfaces.LineRemover;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;
import sample.NeededClasses.Interfaces.GetTheObject;
import sample.NeededClasses.Interfaces.RemovingTheLine;
import sample.NeededClasses.Interfaces.UpdateTheData;

import java.io.IOException;

public class BooksWithStudentController {

    @FXML private BorderPane mainBorderPane;
    @FXML private ListView<Book> bookListView;
    @FXML private Pane bookInfoPane;
    @FXML private Button backButton;
    private final ObservableList<Book> books =
            FXCollections.observableArrayList();
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/StudentSettings.fxml",event);
    }

    public void initialize() throws IOException {
        FileResource fr = new FileResource(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        GetTheObject getTheObject = new ObjectFormReturner();
        for (String line: fr.lines()) {
            Book currBook = getTheObject.getBookFromString(line);
            books.add(currBook);
        }
        bookListView.setItems(books);

        bookListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Book>() {
                    @Override
                    public void changed(ObservableValue<? extends Book> observableValue,
                                        Book oldBook, Book newBook) {
                        try {
                            RemovingTheLine lineRemover = new LineRemover();
                            lineRemover.removeTheLine(newBook.toString(),
                                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
                            UpdateTheData dataUpdater = new DataUpdater();
                            dataUpdater.addLinetoSpecificData(newBook.toString(),
                                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
                            Parent customerSignPage = FXMLLoader.load(getClass().getResource(
                                    "/sample/FXMLFiles/AdminOnes/AdminCheckBookWithStudentPane.fxml"));
                            mainBorderPane.setCenter(customerSignPage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

    }

}

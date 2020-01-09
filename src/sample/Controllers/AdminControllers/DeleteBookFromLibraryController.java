package sample.Controllers.AdminControllers;

import edu.duke.FileResource;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.*;
import sample.NeededClasses.Interfaces.*;

import java.io.IOException;

public class DeleteBookFromLibraryController {

    @FXML private BorderPane mainBorderPane;
    @FXML private ListView<Book> bookListView;
    @FXML private Pane mainPane;
    @FXML private Button goBackButton;
    private final ObservableList<Book> books =
            FXCollections.observableArrayList();

    @FXML
    void goBackButtonPressed(ActionEvent event) throws IOException {
        ChangeThePage pageChanger = new PageChange();
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/BookConfigQuestion.fxml",event);
    }

    public void initialize() throws IOException {
        FileResource fr = new FileResource(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
        GetTheObject objectReturner = new ObjectFormReturner();
        for (String line : fr.lines()) {
            Book currBook = objectReturner.getBookFromString(line);
            books.add(currBook);
        }
        bookListView.setItems(books);
        bookListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Book>() {
                    @Override
                    public void changed(ObservableValue<? extends Book> observableValue, Book oldBook, Book newBook) {
                        try {
                            RemovingTheLine lineRemover = new LineRemover();
                            lineRemover.removeTheLine(newBook.toString(),
                                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
                            UpdateTheData dataUpdater = new DataUpdater();
                            dataUpdater.addLinetoSpecificData(newBook.toString(),
                                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
                            Parent customerSignPage = FXMLLoader.load(getClass().getResource(
                                        "/sample/FXMLFiles/AdminOnes/DeleteBookInfoPane.fxml"));
                            mainBorderPane.setCenter(customerSignPage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}

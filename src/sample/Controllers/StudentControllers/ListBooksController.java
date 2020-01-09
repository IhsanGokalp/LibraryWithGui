package sample.Controllers.StudentControllers;

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
import sample.NeededClasses.ImplOfInterfaces.DataUpdater;
import sample.NeededClasses.ImplOfInterfaces.LineRemover;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;
import sample.NeededClasses.Interfaces.GetTheObject;
import sample.NeededClasses.Interfaces.RemovingTheLine;
import sample.NeededClasses.Interfaces.UpdateTheData;

import java.io.IOException;

public class ListBooksController {

    @FXML private BorderPane mainBorderPane;
    @FXML private ListView<Book> booksListView;
    @FXML private Pane bookInfoPane;
    @FXML private Button backToMenuButton;
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    void backToMenuButtonPressed(ActionEvent event) throws IOException {
        ChangeThePage pageChanger = new PageChange();
        pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml",event);
    }
    public void initialize() {
        FileResource fr = new FileResource(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        GetTheObject objectReturner = new ObjectFormReturner();

        for (String line : fr.lines()) {
            System.out.println("Book:" + line + "/");
            if (!line.equals("") && !line.equals(" ")){
                Book currBook = objectReturner.getBookFromString(line);
                books.add(currBook);
            }
        }
        booksListView.setItems(books);
        booksListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Book>() {
                    @Override
                    public void changed(ObservableValue<? extends Book> observableValue, Book oldBook, Book newBook) {

                        try {
                            RemovingTheLine lineRemover = new LineRemover();
                            lineRemover.removeTheLine(newBook.toString(),
                                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
                            UpdateTheData dataUpdater = new DataUpdater();
                            dataUpdater.addLinetoSpecificData(newBook.toString(),
                                    "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
                            Parent customerSignPage = FXMLLoader.load(getClass().getResource(
                                    "/sample/FXMLFiles/StudentOnes/StudentViewsOwnBooks.fxml"));
                            mainBorderPane.setCenter(customerSignPage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}

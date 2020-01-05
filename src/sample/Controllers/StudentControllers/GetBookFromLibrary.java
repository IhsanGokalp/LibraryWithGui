package sample.Controllers.StudentControllers;

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

import java.io.*;

public class GetBookFromLibrary {

    @FXML private ListView<Book> bookListView;
    @FXML private Pane bookInfoPane;
    @FXML private Button backButton;
    @FXML private BorderPane mainBorderPane;
    private ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        ChangeThePage pageChanger = new PageChange();
        pageChanger.changeThePageTo("/sample/FXMLFiles/StudentOnes/StudentLibrary.fxml",event);
    }

    public void initialize() {
        FileResource fr = new FileResource("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
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
                            lineRemover.removeTheLine(newBook.toString(), "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
                            UpdateTheData dataUpdater = new DataUpdater();
                            dataUpdater.addLinetoSpecificData(newBook.toString(),"/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
                            Parent customerSignPage = FXMLLoader.load(getClass().getResource("/sample/FXMLFiles/StudentOnes/BookInfoOnPane.fxml"));
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

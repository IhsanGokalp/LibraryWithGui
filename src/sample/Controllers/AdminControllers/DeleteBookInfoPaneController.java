package sample.Controllers.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.NeededClasses.Admin;
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.LastLineReturner;
import sample.NeededClasses.ImplOfInterfaces.LineRemover;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.ImplOfInterfaces.PageChange;
import sample.NeededClasses.Interfaces.ChangeThePage;
import sample.NeededClasses.Interfaces.GetTheLastLine;
import sample.NeededClasses.Interfaces.GetTheObject;
import sample.NeededClasses.Interfaces.RemovingTheLine;

import java.io.IOException;
import java.util.Optional;

public class DeleteBookInfoPaneController {

    @FXML private Pane mainPane;
    @FXML private Button backButton;
    @FXML private Label bookTitleLabel;
    @FXML private Label bookNameLabel;
    @FXML private Label authorLabel;
    @FXML private Text bookNameText;
    @FXML private Text authorText;
    @FXML private Label noOfPagesLabel;
    @FXML private Text noOfPagesText;
    @FXML private Button deleteThisBookButton;
    private Admin admin;
    private Book book;
    ChangeThePage pageChanger = new PageChange();

    @FXML
    void backButtonPressed(ActionEvent event) throws IOException {
        pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/BookConfigQuestion.fxml",event);
    }

    @FXML
    void deleteThisBookPressed(ActionEvent event) throws IOException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Confirmation");
        dialog.setContentText("Enter the password of " + admin.getAdminName() + ":");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(admin.getPassword())){
            RemovingTheLine lineRemover = new LineRemover();
            lineRemover.removeTheLine(book.toString(),"/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Successfully deleted "+book.getName()+".", ButtonType.OK);
            alert.setTitle("Delete Successful");
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                pageChanger.changeThePageTo("/sample/FXMLFiles/AdminOnes/BookConfigQuestion.fxml",event);
            }
        }
    }
    public void initialize() throws IOException {
        GetTheObject objectFormGetter = new ObjectFormReturner();
        GetTheLastLine lastLine = new LastLineReturner();
        admin = objectFormGetter.getAdminFromString(lastLine.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/AdminsData.txt"));
        book = objectFormGetter.getBookFromString(lastLine.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LibraryBooks.txt"));
        bookNameText.setText(book.getName());
        authorText.setText(book.getAuthor());
        noOfPagesText.setText(book.getNoOfPages());
    }
}

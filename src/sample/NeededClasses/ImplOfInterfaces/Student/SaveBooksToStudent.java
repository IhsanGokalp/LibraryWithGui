package sample.NeededClasses.ImplOfInterfaces.Student;

import edu.duke.FileResource;
import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.DataUpdater;
import sample.NeededClasses.ImplOfInterfaces.LastLineReturner;
import sample.NeededClasses.ImplOfInterfaces.LineRemover;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.Interfaces.*;
import sample.NeededClasses.Interfaces.Student.GetTheBooksBackToStudent;
import sample.NeededClasses.Student;

import java.io.IOException;

public class SaveBooksToStudent implements GetTheBooksBackToStudent {

    @Override
    public void addBooksBackToStudent() throws IOException {
        GetTheLastLine lastLineGetter = new LastLineReturner();
        String studentToBeAdded = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");

        GetTheObject objectReturner = new ObjectFormReturner();
        Student student = objectReturner.getStudentFromString(studentToBeAdded);

        FileResource fr = new FileResource(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        for (String line : fr.lines()) {
            if (!line.equals("")||!line.equals(" ")){
                Book book = objectReturner.getBookFromString(line);
                student.addBook(book);
            }
        }

        RemovingTheLine lineRemover = new LineRemover();
        lineRemover.removeTheLine(studentToBeAdded,
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        UpdateTheData dataUpdater = new DataUpdater();
        dataUpdater.addLinetoSpecificData(studentToBeAdded.substring(0,2) + student.toString(),
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
    }
}

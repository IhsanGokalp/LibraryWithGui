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

    GetTheLastLine lastLineGetter = new LastLineReturner();
    GetTheObject objectReturner = new ObjectFormReturner();
    RemovingTheLine lineRemover = new LineRemover();
    UpdateTheData dataUpdater = new DataUpdater();

    private String lastStudentOfFile;

    @Override
    public void addBooksBackToStudent() throws IOException {
        lastStudentOfFile = lastLineGetter.getLastLine(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");

        Student student = objectReturner.getStudentFromString(lastStudentOfFile);

        FileResource fr = new FileResource(
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt");
        for (String line : fr.lines()) {
            if (!line.equals("") && !line.equals(" ")){
                System.out.println("Book:"+line+"/");
                Book book = objectReturner.getBookFromString(line);
                student.addBook(book);
            }
        }
        lineRemover.removeTheLine(lastStudentOfFile,
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        dataUpdater.addLinetoSpecificData(lastStudentOfFile.substring(0,2) + student.toString(),
                "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
    }
}

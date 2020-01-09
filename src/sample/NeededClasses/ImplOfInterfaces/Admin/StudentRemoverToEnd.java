package sample.NeededClasses.ImplOfInterfaces.Admin;

import edu.duke.FileResource;
import sample.NeededClasses.ImplOfInterfaces.DataUpdater;
import sample.NeededClasses.ImplOfInterfaces.LineRemover;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.Interfaces.Admin.GetStudentToLastLine;
import sample.NeededClasses.Interfaces.GetTheObject;
import sample.NeededClasses.Interfaces.RemovingTheLine;
import sample.NeededClasses.Interfaces.UpdateTheData;
import sample.NeededClasses.Student;

import java.io.IOException;

public class StudentRemoverToEnd implements GetStudentToLastLine {
    @Override
    public void getTheStudentToEnd(String line) throws IOException {
        FileResource fr = new FileResource("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
        GetTheObject objectGetter = new ObjectFormReturner();

        for (String currStudent : fr.lines()) {
            Student std = objectGetter.getStudentWithBooks(currStudent);
            if (std.toString().equals(line)) {
                RemovingTheLine lineRemover = new LineRemover();
                lineRemover.removeTheLine(currStudent,
                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
                UpdateTheData dataUpdater = new DataUpdater();
                dataUpdater.addLinetoSpecificData(currStudent,
                        "/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/StudentsData.txt");
            }
        }
    }
}

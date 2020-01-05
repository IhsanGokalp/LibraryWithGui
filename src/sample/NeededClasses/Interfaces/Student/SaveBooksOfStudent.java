package sample.NeededClasses.Interfaces.Student;

import sample.NeededClasses.Book;

import java.io.IOException;
import java.util.ArrayList;

public interface SaveBooksOfStudent {
    void saveBooksOfStudent(ArrayList<Book> books) throws IOException;
    ArrayList<Book> getBooksFromStr(String booksInStr);
    String getBooksOfStudentAsStr(String lastLine);
    void autoSaverOfBooks(String lastLine) throws IOException;
}

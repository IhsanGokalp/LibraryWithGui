package sample.NeededClasses.ImplOfInterfaces.Student;

import sample.NeededClasses.Book;
import sample.NeededClasses.ImplOfInterfaces.ObjectFormReturner;
import sample.NeededClasses.Interfaces.GetTheObject;
import sample.NeededClasses.Interfaces.Student.SaveBooksOfStudent;
import sample.NeededClasses.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BookSaver implements SaveBooksOfStudent {

    @Override
    public void saveBooksOfStudent(ArrayList<Book> books) throws IOException {
        FileWriter myWriter = new FileWriter("/home/ihsang/Documents/Cs Kg/OOP/FinalLibrary/src/sample/Data/LoggedStudent.txt", true);
        BufferedWriter bfWriter = new BufferedWriter(myWriter);
        for(Book book : books) {
            bfWriter.write(book.toString());
            bfWriter.newLine();
        }
        bfWriter.close();
    }

    @Override
    public ArrayList<Book> getBooksFromStr(String booksInStr) {
        GetTheObject objectGetter = new ObjectFormReturner();
        ArrayList<Book> bookList = new ArrayList<Book>();
        int bookIdx = booksInStr.indexOf("Book");
        while (bookIdx>=0) {
            System.out.println("Book In Str:" + booksInStr + "//");
            Book book = objectGetter.getBookFromString(booksInStr);
            System.out.println(book);
            bookList.add(book);
            int pageEndIdx = objectGetter.getPageEndIdx();
            bookIdx = booksInStr.indexOf("Book",pageEndIdx);
            System.out.println("BOOK IDX:" + bookIdx);
            if (bookIdx != -1)
                booksInStr = booksInStr.substring(bookIdx);
            System.out.println(booksInStr+"\n");
        }
        return bookList;
    }

    @Override
    public String getBooksOfStudentAsStr(String lastLine) {
        GetTheObject objectGetter = new ObjectFormReturner();
        int phoneNoEndIdx = objectGetter.getPhoneEndIdx();
        int booksBeginIdx = lastLine.indexOf('[',phoneNoEndIdx+1)+1;
        int booksEndIdx = lastLine.indexOf(']',booksBeginIdx);
        String books = lastLine.substring(booksBeginIdx,booksEndIdx);
        return books;
    }

    @Override
    public void autoSaverOfBooks(String lastLine) throws IOException {
        String ans = getBooksOfStudentAsStr(lastLine);
        ArrayList<Book> yoSAyHi = getBooksFromStr(ans);
        saveBooksOfStudent(yoSAyHi);
    }

}
